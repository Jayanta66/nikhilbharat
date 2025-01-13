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

import com.project.model.ImageGallery;
import com.project.service.ImageGalleryService;



@Controller
public class ImageGalleryController {
	
	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ImageGalleryService imageGalleryService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/icard")
	public String addProductPage() {
		return "icard";
	}


	
	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("state") String state,
			@RequestParam("fathername") String fathername,
			@RequestParam("dob") String dob,
			@RequestParam("occupation") String occupation,
			@RequestParam("village") String village,
			@RequestParam("post") String post,
			@RequestParam("dist") String dist,
			@RequestParam("city") String city,
			@RequestParam("pin") String pin,
			@RequestParam("contact") String contact,
			@RequestParam("gender") String gender,


			
			Model model, HttpServletRequest request
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
/*			String[] descriptions = description.split(","); */
			Date createDate = new Date();
			log.info("Name: " + names[0]+" "+filePath);
	/*		log.info("description: " + descriptions[0]); */
			
			
			log.info("city : "+city);
			log.info("state : "+state);
			log.info("fathername : "+fathername);
			log.info("dob : "+dob);
			log.info("occupation : "+occupation);
			log.info("village : "+village);
			log.info("post : "+post);
			log.info("dist : "+dist);
			log.info("city : "+city);
			log.info("pin : "+pin);
			log.info("gender : "+gender);
			log.info("contact : "+contact);


			
			
			
			
			
			
			
			
	/*		log.info("price: " + price); */
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
			ImageGallery imageGallery = new ImageGallery();
			imageGallery.setName(names[0]);
			imageGallery.setFathername(fathername);
			imageGallery.setDob(dob);
			imageGallery.setOccupation(occupation);
			imageGallery.setVillage(village);
			imageGallery.setPost(post);
			imageGallery.setDist(dist);
			imageGallery.setCity(city);
			imageGallery.setPin(pin);
			imageGallery.setState(state);
			imageGallery.setContact(contact);
			imageGallery.setGender(gender);
			
			imageGallery.setImage(imageData);

			imageGallery.setCreateDate(createDate);
			imageGalleryService.saveImage(imageGallery);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ImageGallery> imageGallery)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		imageGallery = imageGalleryService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(imageGallery.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<ImageGallery> imageGallery, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				imageGallery = imageGalleryService.getImageById(id);
			
				log.info("products :: " + imageGallery);
				if (imageGallery.isPresent()) {
					model.addAttribute("id", imageGallery.get().getId());
					model.addAttribute("name", imageGallery.get().getName());
					model.addAttribute("fathername", imageGallery.get().getFathername());

					
					
					model.addAttribute("dob", imageGallery.get().getDob());
					model.addAttribute("occupation", imageGallery.get().getOccupation());
					model.addAttribute("village", imageGallery.get().getVillage());
					model.addAttribute("post", imageGallery.get().getPost());
					model.addAttribute("dist", imageGallery.get().getDist());
					model.addAttribute("contact", imageGallery.get().getContact());
					model.addAttribute("pin", imageGallery.get().getPin());
					model.addAttribute("gender", imageGallery.get().getGender());


					model.addAttribute("city", imageGallery.get().getCity());
		/*			model.addAttribute("price", imageGallery.get().getPrice()); */
					model.addAttribute("state", imageGallery.get().getState());

					return "imagedetails";
				}
				return "redirect:/icard";
			}
		return "redirect:/icard";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/icard";
		}	
	}

	@GetMapping("/image/show")
	String show(Model map) {
		List<ImageGallery> images = imageGalleryService.getAllActiveImages();
		map.addAttribute("images", images);
		return "images";
	}
}	

