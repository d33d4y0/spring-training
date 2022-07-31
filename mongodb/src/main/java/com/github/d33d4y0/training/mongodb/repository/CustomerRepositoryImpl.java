package com.github.d33d4y0.training.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.github.d33d4y0.training.mongodb.dto.CustomerSearchDto;
import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "customer-index";

	public void updateByCitizenId(CustomerEntity customer, String citizenId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("citizenId").is(citizenId));
		Update update = new Update();
		if (customer.getName() != null) {
			update.set("name", customer.getName());
		}
		if (customer.getAge() != null) {
			update.set("age", customer.getAge());
		}
		if (customer.getAddress() != null) {
			update.set("address", customer.getAddress());
		}
		mongoTemplate.updateFirst(query, update, CustomerEntity.class, COLLECTION_NAME);
	}

	public List<CustomerEntity> findCustomerNullHandled(CustomerSearchDto search) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if (search.getCitizenID() != null) {
			criteria.and("citizenId").is(search.getCitizenID());
		}
		if (search.getAge() != null) {
			criteria.and("age").is(search.getAge());
		}
		if (search.getName() != null) {
			criteria.and("name").is(search.getName());
		}
		query.addCriteria(criteria);
		return mongoTemplate.find(query, CustomerEntity.class, COLLECTION_NAME);
	}
}
