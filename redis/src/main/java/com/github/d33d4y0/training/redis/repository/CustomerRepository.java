package com.github.d33d4y0.training.redis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.d33d4y0.training.redis.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
	
	public List<CustomerEntity> findByFirstName(String firstName);
	public CustomerEntity findByCitizenId(String citizenId);
	public List<CustomerEntity> findByCitizenIdOrFirstNameOrLastName(String citizenId, String firstName, String lastName);
}
