package com.github.d33d4y0.training.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.training.jpa.dto.CustomerDto;
import com.github.d33d4y0.training.jpa.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping()
	public List<CustomerDto> findByLastName(@RequestParam(value = "l", required = false) String lastName,
			@RequestParam(value = "postal", required = false) String postalCode) {
		if (lastName != null && postalCode != null) {
			return service.findByLastNameAndPostalCode(lastName, postalCode);
		}
		return service.findByLastNameOrPostalCode(lastName, postalCode);
	}

	@GetMapping("/all")
	public List<CustomerDto> findAll(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "sort", required = false) String sortBy) {
		return service.findAll(page, size, sortBy);
	}

	@GetMapping("/first")
	public CustomerDto findFirstByLastName(@RequestParam(value = "l", required = false) String lastName) {
		return service.findFirstByLastName(lastName);
	}

	@GetMapping("/end-with")
	public List<CustomerDto> findByFirstNameEndWith(@RequestParam(value = "f", required = false) String firstName) {
		return service.findByFirstNameEndWith(firstName);
	}

	@GetMapping("{id}")
	public CustomerDto findByLastName(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@PostMapping()
	public CustomerDto addCustomer(@RequestBody CustomerDto customer) {
		return service.addCustomer(customer);
	}
}
