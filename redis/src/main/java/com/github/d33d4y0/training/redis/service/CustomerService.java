package com.github.d33d4y0.training.redis.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.domain.CustomerDomain;
import com.github.d33d4y0.training.redis.dto.CustomerSearchDTO;
import com.github.d33d4y0.training.redis.entity.CustomerEntity;
import com.github.d33d4y0.training.redis.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Integer> intOps;

//	using CustomerSearchDTO for further update
	public List<CustomerDomain> findCustomers(CustomerSearchDTO search){
		String citizenId = search.getCitizenId() == null ? "" : search.getCitizenId();
		String firstName = search.getFirstName() == null ? "" : search.getFirstName();
		String lastName = search.getLastName() == null ? "" : search.getLastName();
		List<CustomerEntity> entities = customerRepo.findByCitizenIdOrFirstNameOrLastName(citizenId, firstName, lastName);
		List<CustomerDomain> domains = new LinkedList<>();
		if (entities != null) {
			for (CustomerEntity entity : entities) {
				domains.add(new CustomerDomain(entity));
			}
		}
		return domains;
	}

	public CustomerDomain addCustomer(CustomerDomain customer) {
		CustomerEntity entity = customer.toEntity();
		entity.setId(generateCustomerId());
		entity = customerRepo.save(entity);
		return new CustomerDomain(entity);
	}

	private String generateCustomerId() {
		return "c" + getAndIncrement();
	}

	private long getAndIncrement() {
//		can use redisTemplate directly as well
//		redisTemplate.opsForValue().increment("customerId");
		return intOps.increment("customerId");
	}
}
