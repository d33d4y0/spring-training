package com.github.d33d4y0.training.jpa.service;

import java.util.List;

import com.github.d33d4y0.training.jpa.dto.CustomerDto;
import com.github.d33d4y0.training.jpa.entity.CreditCardEntity;

public interface CustomerService {

	public List<CustomerDto> findByLastName(String lastName);

	public CustomerDto findById(long id);

	public List<CustomerDto> findByPostalCode(String postalCode);

	public List<CustomerDto> findByLastNameAndPostalCode(String lastName, String postalCode);

	public List<CustomerDto> findByLastNameOrPostalCode(String lastName, String postalCode);

	public List<CustomerDto> findPagableByLastNameOrPostalCode(String lastName, String postalCode, int page, int size);

	public CustomerDto addCustomer(CustomerDto customer);

	public List<CustomerDto> findAll(Integer page, Integer size, String sortBy);

	public CustomerDto findFirstByLastName(String lastName);

	public List<CustomerDto> findByFirstNameEndWith(String firstName);

	public List<CustomerDto> findByProvince(String province);

	public CreditCardEntity findByCardNumber(String card);
}
