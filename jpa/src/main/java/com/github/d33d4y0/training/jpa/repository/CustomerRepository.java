package com.github.d33d4y0.training.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.d33d4y0.training.jpa.entity.CustomerEntity;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

	List<CustomerEntity> findByLastName(String lastName);

	List<CustomerEntity> findByAddress_PostalCode(String Address_PostalCode);

	List<CustomerEntity> findByLastNameAndAddress_PostalCode(String lastName, String Address_PostalCode);

	List<CustomerEntity> findByLastNameOrAddress_PostalCode(String lastName, String Address_PostalCode);

	Slice<CustomerEntity> findPagableByLastNameOrAddress_PostalCode(String lastName, String Address_PostalCode,
			Pageable pageable);

	CustomerEntity findFirstByLastName(String lastName);

	List<CustomerEntity> findByAddress_Province(String province);

	@Query("select c from CustomerEntity c where c.firstName like %?1")
	List<CustomerEntity> findByFirstNameEndWith(String firstName);
}
