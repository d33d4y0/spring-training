package com.github.d33d4y0.training.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String>, CustomerRepositoryCustom {

	/**
	 * For more named query example
	 * {@see #com.github.d33d4y0.training.elasticsearch.repository.StudentRepository}
	 */
	public List<CustomerEntity> findByName(String name);

	public CustomerEntity findByCitizenId(String citizenId);

	public List<CustomerEntity> findByCitizenIdOrNameOrAge(String citizenId, String name, int age);

	public List<CustomerEntity> findByAgeLessThan(Integer age);

	public List<CustomerEntity> findByAddressDistrict(String district);

	public List<CustomerEntity> findByNameEndingWith(String prefix);

	@Query("{'name' : ?0}")
	public List<CustomerEntity> findByNameWithCustomQuery(String name);
}
