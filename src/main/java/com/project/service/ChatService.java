package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Chat;
import com.project.repository.ChatRepository;





@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	

	
	public void saveImage(Chat chat) {
		chatRepository.save(chat);	
	}

	public List<Chat> getAllActiveImages() {
		return chatRepository.findAll();
	}

	public Optional<Chat> getImageById(Long id) {
		return chatRepository.findById(id);
	}
}

