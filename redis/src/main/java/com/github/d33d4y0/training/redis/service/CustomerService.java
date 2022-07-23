package com.github.d33d4y0.training.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.domain.CustomerDomain;
import com.github.d33d4y0.training.redis.dto.CustomerSearchDTO;
import com.github.d33d4y0.training.redis.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

//	using CustomerSearchDTO for further update
	public CustomerDomain findCustomer(CustomerSearchDTO search) {
		return new CustomerDomain(customerRepo.findByCitizenId(search.getCitizenId()));
	}

	public CustomerDomain addCustomer(CustomerDomain customer) {
		return new CustomerDomain(customerRepo.save(customer.toEntity()));
	}
}
