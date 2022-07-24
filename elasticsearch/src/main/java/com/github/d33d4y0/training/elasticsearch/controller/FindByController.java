package com.github.d33d4y0.training.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.elasticsearch.entity.StudentEntity;
import com.github.d33d4y0.training.elasticsearch.service.FindByService;

@RestController
@RequestMapping(value = "/find-by")
public class FindByController {

	@Autowired
	private FindByService service;
	
	@GetMapping("/exactly")
	public StudentEntity findByCitizenId() {
		return service.findByCitizenId();
	}
	
	@GetMapping("/or")
	public List<StudentEntity> findByNameOrCitizenId() {
		return service.findByNameOrCitizenId();
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
}
