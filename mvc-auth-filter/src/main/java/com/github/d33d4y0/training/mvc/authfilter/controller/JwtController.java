package com.github.d33d4y0.training.mvc.authfilter.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.mvc.authfilter.service.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {

	@Autowired
	private JwtService service;
	
	@PostMapping("/generate")
	public String generateJwt(@RequestBody String role) throws UnsupportedEncodingException {
		return service.generateJwt(role);
	}
	
}