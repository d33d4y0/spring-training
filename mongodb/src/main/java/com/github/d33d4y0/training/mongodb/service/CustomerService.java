package com.github.d33d4y0.training.mongodb.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.mongodb.dto.CustomerDTO;
import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;
import com.github.d33d4y0.training.mongodb.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public List<CustomerDTO> findCustomer(CustomerDTO customer) {
		List<CustomerEntity> docs = customerRepo.findByCitizenIdOrFirstNameOrLastName(customer.getCitizenID(),
				customer.getFirstName(), customer.getLastName());
		List<CustomerDTO> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDTO(doc));
		}
		return results;
	}

	public CustomerDTO addCustomer(CustomerDTO customer) {
		customerRepo.save(new CustomerEntity(customer));
		return customer;
	}
}
