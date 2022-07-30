package com.github.d33d4y0.training.jpa.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.jpa.dto.CustomerDto;
import com.github.d33d4y0.training.jpa.entity.Address;
import com.github.d33d4y0.training.jpa.entity.CustomerEntity;
import com.github.d33d4y0.training.jpa.repository.CustomerRepository;
import com.github.d33d4y0.training.jpa.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@PostConstruct
	private void initCustomer() {
		CustomerEntity customer1 = new CustomerEntity();
		customer1.setFirstName("D33d4y");
		customer1.setLastName("Jpa");
		customer1.setAddress(new Address("Thailand", "Bangkok", "Rama9", "10280"));

		CustomerEntity customer2 = new CustomerEntity();
		customer2.setFirstName("Srping");
		customer2.setLastName("Jpa");
		customer2.setAddress(new Address("Thailand", "Bangkok", "Thapra", "10160"));

		CustomerEntity customer3 = new CustomerEntity();
		customer3.setFirstName("Day");
		customer3.setLastName("Test");
		customer3.setAddress(new Address("Thailand", "Bangkok", "Thapra", "10160"));

		customerRepo.saveAll(Arrays.asList(customer1, customer2, customer3));
	}

	@Override
	public List<CustomerDto> findByLastName(String lastName) {
		List<CustomerEntity> entities = customerRepo.findByLastName(lastName);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public CustomerDto findById(long id) {
		CustomerEntity entity = customerRepo.findById(id).orElse(new CustomerEntity());
		CustomerDto customer;
		if (entity != null) {
			customer = new CustomerDto(entity);
		} else {
			customer = new CustomerDto();
		}
		return customer;
	}

	@Override
	public List<CustomerDto> findByPostalCode(String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByAddress_PostalCode(postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public CustomerDto addCustomer(CustomerDto customer) {
		CustomerEntity entity = customerRepo.save(new CustomerEntity(customer));
		if (entity != null) {
			customer = new CustomerDto(entity);
		} else {
			customer = new CustomerDto();
		}
		return customer;
	}

	@Override
	public List<CustomerDto> findByLastNameAndPostalCode(String lastName, String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByLastNameAndAddress_PostalCode(lastName, postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findByLastNameOrPostalCode(String lastName, String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByLastNameOrAddress_PostalCode(lastName, postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findPagableByLastNameOrPostalCode(String lastName, String postalCode, int page, int size) {
		return customerRepo.findPagableByLastNameOrAddress_PostalCode(lastName, postalCode, PageRequest.of(page, size))
				.map(each -> new CustomerDto(each)).toList();
	}

	@Override
	public List<CustomerDto> findAll(Integer page, Integer size, String sort) {
		Sort sortBy = sort == null ? Sort.unsorted() : Sort.by(sort);
		Pageable pageRequest = (page == null) || (size == null) ? Pageable.unpaged()
				: PageRequest.of(page, size, sortBy);
		return customerRepo.findAll(pageRequest).map(each -> new CustomerDto(each)).toList();
	}

	@Override
	public CustomerDto findFirstByLastName(String lastName) {
		return new CustomerDto(customerRepo.findFirstByLastName(lastName));
	}

	public List<CustomerDto> findByFirstNameEndWith(String firstName) {
		List<CustomerEntity> entities = customerRepo.findByFirstNameEndWith(firstName == null ? "" : firstName);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findByProvince(String province) {
		List<CustomerEntity> entities = customerRepo.findByAddress_Province(province);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}
}
