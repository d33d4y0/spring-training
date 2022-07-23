package com.github.d33d4y0.training.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

	public List<CustomerEntity> findByFirstName(String firstName);

	public CustomerEntity findByCitizenId(String citizenId);

	public List<CustomerEntity> findByCitizenIdOrFirstNameOrLastName(String citizenId, String firstName, String lastName);
}