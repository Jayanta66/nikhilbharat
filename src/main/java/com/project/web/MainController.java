package com.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	

	
	@RequestMapping("/index")
	public String homee() {	
		return "index";
}
	
	@RequestMapping("/videolist")
	public String videolist() {
		return "videolist";
	}
	
	@RequestMapping("/photos")
	public String photos() {
		return "photos";
	}
	
	@RequestMapping("/inn")
	public String in() {
		return "inn";
	}
	
	@RequestMapping("/photo_gallary")
	public String photogallary() {
		return "photo_gallary";
	}

	@RequestMapping("/rajasthan")
	public String rajasthan() {
		return "rajasthan";
	}
	

	@RequestMapping("/bihar")
	public String bihar() {
		return "bihar";
	}
	
	
}
