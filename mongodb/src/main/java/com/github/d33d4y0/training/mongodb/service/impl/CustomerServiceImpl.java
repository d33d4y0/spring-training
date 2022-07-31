package com.github.d33d4y0.training.mongodb.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.d33d4y0.training.mongodb.domain.AddressDomain;
import com.github.d33d4y0.training.mongodb.domain.CustomerDomain;
import com.github.d33d4y0.training.mongodb.dto.CustomerSearchDto;
import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;
import com.github.d33d4y0.training.mongodb.repository.CustomerRepository;
import com.github.d33d4y0.training.mongodb.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;

	@PostConstruct
	public void init() {
		customerRepo.deleteAll();

		CustomerEntity customer1 = new CustomerEntity();
		customer1.setId("c01");
		customer1.setAddress(new AddressDomain("Thailand", "Bangkok", "Pak Khlong Phasi Charoen"));
		customer1.setAge(24);
		customer1.setCitizenId("1111111111111");
		customer1.setName("D33d4y Mongo");
		customer1.setRegisteredDateTime(LocalDateTime.now());

		CustomerEntity customer2 = new CustomerEntity();
		customer2.setId("c02");
		customer2.setAddress(new AddressDomain("Thailand", "Bangkok", "Phaya Thai"));
		customer2.setAge(24);
		customer2.setCitizenId("2222222222222");
		customer2.setName("Spring Training");
		customer2.setRegisteredDateTime(LocalDateTime.now());

		customerRepo.saveAll(Arrays.asList(customer1, customer2));
	}

	public List<CustomerDomain> findCustomer(CustomerSearchDto search) {
		List<CustomerEntity> docs = customerRepo.findCustomerNullHandled(search);
		List<CustomerDomain> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDomain(doc));
		}
		return results;
	}

	public List<CustomerDomain> findCustomerByNameEndingWith(String lastName) {
		List<CustomerEntity> docs = customerRepo.findByNameEndingWith(lastName);
		List<CustomerDomain> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDomain(doc));
		}
		return results;
	}

	public List<CustomerDomain> findCustomerByAgeLessThan(Integer age) {
		List<CustomerEntity> docs = customerRepo.findByAgeLessThan(age);
		List<CustomerDomain> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDomain(doc));
		}
		return results;
	}

	public List<CustomerDomain> findByAddressDistrict(String district) {
		List<CustomerEntity> docs = customerRepo.findByAddressDistrict(district);
		List<CustomerDomain> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDomain(doc));
		}
		return results;
	}

	public CustomerDomain addCustomer(CustomerDomain customer) {
		try {
			customerRepo.save(customer.toEntity());
		} catch (DuplicateKeyException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deplicate citizen id");
		}
		return customer;
	}

	public void updateCustomer(CustomerDomain customer) {
		customerRepo.updateByCitizenId(customer.toEntity(), customer.getCitizenId());
	}

	public List<CustomerDomain> findByNameWithCustomQuery(String name) {
		List<CustomerEntity> docs = customerRepo.findByNameWithCustomQuery(name);
		List<CustomerDomain> results = new LinkedList<>();
		for (CustomerEntity doc : docs) {
			results.add(new CustomerDomain(doc));
		}
		return results;
	}
}