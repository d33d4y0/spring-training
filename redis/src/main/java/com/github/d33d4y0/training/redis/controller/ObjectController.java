package com.github.d33d4y0.training.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.redis.service.interfaces.ObjectService;

@RestController
@RequestMapping("/obj")
public class ObjectController {

	@Autowired
	private ObjectService service;
	
	@GetMapping
	public Object get(@RequestParam String key) {
		return service.get(key);
	}
	
	@PostMapping
	public void put(@RequestParam String key, @RequestBody Object value) {
		service.put(key, value);
	}
}
