package com.github.d33d4y0.training.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.kafka.domain.EmailNotificationDomain;
import com.github.d33d4y0.training.kafka.service.ProducerService;

@RestController
public class ProducerController {

	@Autowired
	private ProducerService service;
	
	@PostMapping("/notify/email")
	public void emailNotify(@RequestBody EmailNotificationDomain noti) {
		service.emailNotify(noti);
	}
}
