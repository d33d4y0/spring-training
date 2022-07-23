package com.github.d33d4y0.training.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.mongodb.dto.CustomerDTO;
import com.github.d33d4y0.training.mongodb.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@PostMapping("/find")
	public List<CustomerDTO> findCustomer(@RequestBody CustomerDTO customer) {
		return service.findCustomer(customer);
	}
	
	@PostMapping("/add")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customer) {
		return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.CREATED);
	}
}
