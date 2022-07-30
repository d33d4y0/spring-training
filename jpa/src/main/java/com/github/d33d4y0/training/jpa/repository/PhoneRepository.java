package com.github.d33d4y0.training.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.d33d4y0.training.jpa.entity.PhoneEntity;

public interface PhoneRepository extends PagingAndSortingRepository<PhoneEntity, Long> {

}
