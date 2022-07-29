package com.github.d33d4y0.training.unittest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.unittest.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@GetMapping("/add")
	public int add(@RequestParam int a, @RequestParam int b) {
		System.out.println("a: " + a + " b: " + b + " result: " + (a+b));
		System.out.println(service.add(a, b));
		return service.add(a, b);
	}

	@GetMapping("/subtract")
	public int subtract(@RequestParam int a, @RequestParam int b) {
		return service.subtract(a, b);
	}

	@GetMapping("/json")
	public Map<String, String> reflectAsJson(@RequestParam(value = "n", required = false) String name) {
		return service.reflectAsJson(name);
	}

	@GetMapping("/string")
	public String reflected(@RequestParam(value = "s", required = false) String str) {
		return service.reflectAsString(str);
	}

}
