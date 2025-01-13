package com.project.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.model.Chat;
import com.project.service.ChatService;



@Controller
public class ChatController {
	
	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ChatService chatService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());



	
	@PostMapping("/chat/saveChat")
	public @ResponseBody ResponseEntity<?> createChat(@RequestParam("name") String name,
			@RequestParam("text") String text, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			Date createDate = new Date();
			log.info("Name: " + names[0]+" "+filePath);
			log.info("text : "+text);

			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Chat chat = new Chat();
			chat.setName(names[0]);
			chat.setImage(imageData);
			chat.setText(text);
			chat.setCreateDate(createDate);
			chatService.saveImage(chat);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/chat/display/{id}")
	@ResponseBody
	void showChat(@PathVariable("id") Long id, HttpServletResponse response, Optional<Chat> imageGallery)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		imageGallery = chatService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(imageGallery.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/chat/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Chat> chats, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				chats = chatService.getImageById(id);
			
				log.info("chats :: " + chats);
				if (chats.isPresent()) {
					model.addAttribute("id", chats.get().getId());
					model.addAttribute("name", chats.get().getName());
					model.addAttribute("text", chats.get().getText());

					return "imagedetails";
				}
				return "redirect:/index";
			}
		return "redirect:/chat";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/chat";
		}	
	}

	@GetMapping({"/chat"})
//	@GetMapping("/image/show")
	String show(Model map) {
		List<Chat> chat = chatService.getAllActiveImages();
		map.addAttribute("chat", chat);
		return "chat";
	}
}	

