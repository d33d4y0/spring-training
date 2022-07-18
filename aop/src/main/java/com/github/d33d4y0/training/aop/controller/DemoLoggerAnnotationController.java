package com.github.d33d4y0.training.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.aop.service.DemoAopService;

@RestController
public class DemoLoggerAnnotationController {

	@Autowired
	private DemoAopService service;

	@GetMapping("/around")
	public String logAnnotation() {
		return service.logAroundAnnotation();
	}

	@GetMapping("/around/with-args")
	public String logAnnotationWithArgs() {
		return service.logAroundAnnotationWithArgs("args1", 1);
	}
	
	@GetMapping("/before")
	public String logBeforeAnnotation() {
		return service.logBeforeAnnotation();
	}

	@GetMapping("/before/with-args")
	public String logBeforeAnnotationWithArgs() {
		return service.logBeforeAnnotationWithArgs("args1", 1);
	}
	
	@GetMapping("/after-return")
	public String logAfterReturnAnnotation() {
		return service.logAfterReturnAnnotation();
	}

	@GetMapping("/after-return/with-args")
	public String logAfterReturnAnnotationWithArgs() {
		return service.logAfterReturnAnnotationWithArgs("args1", 1);
	}
	
	@GetMapping("/throw")
	public void throwException() {
		service.throwException();
	}
	
	@GetMapping("/after")
	public String logAfter() {
		return service.logAfter();
	}
}
