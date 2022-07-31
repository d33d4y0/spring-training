package com.github.d33d4y0.training.mongodb.repository;

import java.util.List;

import com.github.d33d4y0.training.mongodb.dto.CustomerSearchDto;
import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

public interface CustomerRepositoryCustom {

	public void updateByCitizenId(CustomerEntity customer, String citizenId);

	public List<CustomerEntity> findCustomerNullHandled(CustomerSearchDto search);
}
