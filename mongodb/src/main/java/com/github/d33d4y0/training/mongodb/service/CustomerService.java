package com.github.d33d4y0.training.mongodb.service;

import java.util.List;

import com.github.d33d4y0.training.mongodb.domain.CustomerDomain;
import com.github.d33d4y0.training.mongodb.dto.CustomerSearchDto;

public interface CustomerService {

	public List<CustomerDomain> findCustomer(CustomerSearchDto search);

	public List<CustomerDomain> findCustomerByNameEndingWith(String lastName);

	public List<CustomerDomain> findCustomerByAgeLessThan(Integer age);

	public List<CustomerDomain> findByAddressDistrict(String district);

	public CustomerDomain addCustomer(CustomerDomain customer);

	public void updateCustomer(CustomerDomain customer);

	public List<CustomerDomain> findByNameWithCustomQuery(String name);
}