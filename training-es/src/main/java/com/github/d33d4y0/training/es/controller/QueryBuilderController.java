package com.github.d33d4y0.training.es.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.es.entity.StudentEntity;
import com.github.d33d4y0.training.es.service.QueryBuilderService;

@RestController
@RequestMapping(value = "/query-builder")
public class QueryBuilderController {

	@Autowired
	private QueryBuilderService service;
	
	@GetMapping("/exactly")
	public StudentEntity findByCitizenId() throws IOException {
		return service.findByCitizenId();
	}
	
	@GetMapping("/is-not")
	public List<StudentEntity> findByCitizenIdIsNot() {
		return service.findByCitizenIdIsNot();
	}
	
	@GetMapping("/graduated-false")
	public List<StudentEntity> findByIsGraduatedFalse() {
		return service.findByIsGraduatedFalse();
	}
	
	@GetMapping("/starting-with")
	public List<StudentEntity> findByNameStartingWith() {
		return service.findByNameStartingWith();
	}
	
	@GetMapping("/less-than")
	public List<StudentEntity> findByAgeLessThan() {
		return service.findByAgeLessThan();
	}
	
	@GetMapping("/between")
	public List<StudentEntity> findByAgeBetween() {
		return service.findByAgeBetween();
	}
	
	@GetMapping("/after")
	public List<StudentEntity> findByRegisteredDateTimeAfter() {
		return service.findByRegisteredDateTimeAfter();
	}
	
	@GetMapping("/order-by-desc")
	public List<StudentEntity> findByAgeOrderByRegisteredDateTimeDesc() {
		return service.findByAgeOrderByRegisteredDateTimeDesc();
	}
	
	@GetMapping("/nested")
	public List<StudentEntity> findByAddressDistrict() {
		return service.findByAddressDistrict();
	}
	
	@GetMapping("/scrolling")
	public List<StudentEntity> scrolling() {
		return service.scrolling();
	}
}
