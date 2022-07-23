package com.github.d33d4y0.training.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.mongodb.domain.CustomerDomain;
import com.github.d33d4y0.training.mongodb.dto.CustomerSearchDTO;
import com.github.d33d4y0.training.mongodb.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/find")
	public List<CustomerDomain> findCustomer(@RequestBody CustomerSearchDTO search) {
		return service.findCustomer(search);
	}

	@PostMapping("/add")
	public ResponseEntity<CustomerDomain> addCustomer(@RequestBody CustomerDomain customer) {
		return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.CREATED);
	}

	@GetMapping("")
	public List<CustomerDomain> findCustomerByNameEndingWith(
			@RequestParam(value = "lastName", required = false) String lastName) {
		return service.findCustomerByNameEndingWith(lastName);
	}
}
