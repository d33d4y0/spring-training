package com.github.d33d4y0.training.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.redis.domain.CustomerDomain;
import com.github.d33d4y0.training.redis.dto.CustomerSearchDTO;
import com.github.d33d4y0.training.redis.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/find")
	public CustomerDomain findCustomer(@RequestBody CustomerSearchDTO search) {
		return service.findCustomer(search);
	}

	@PostMapping("/add")
	public ResponseEntity<CustomerDomain> addCustomer(@RequestBody CustomerDomain customer) {
		return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.CREATED);
	}
}
