package com.github.d33d4y0.training.aop.service;

import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.aop.annotation.LoggerAfterReturning;
import com.github.d33d4y0.training.aop.annotation.LoggerAround;
import com.github.d33d4y0.training.aop.annotation.LoggerBefore;

@Service
public class DemoAopService {

	@LoggerAround
	public String logAroundAnnotation() {
		return "around 1";
	}
	
	@LoggerAround
	public String logAroundAnnotationWithArgs(String one, Integer two) {
		return "around 2";
	}
	
	@LoggerBefore
	public String logBeforeAnnotation() {
		return "before 1";
	}
	
	@LoggerBefore
	public String logBeforeAnnotationWithArgs(String one, Integer two) {
		return "before 2";
	}
	
	@LoggerAfterReturning
	public String logAfterReturnAnnotation() {
		return "after return 1";
	}
	
	@LoggerAfterReturning
	public String logAfterReturnAnnotationWithArgs(String one, Integer two) {
		return "after return 2";
	}
	
	public void throwException() {
		throw new IllegalArgumentException("some error");
	}
	
	public String logAfter() {
		return "after 1";
	}
}
