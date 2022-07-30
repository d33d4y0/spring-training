package com.github.d33d4y0.training.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.d33d4y0.training.jpa.entity.PromotionCodeEntity;

public interface PromotionCodeRepository extends PagingAndSortingRepository<PromotionCodeEntity, Long> {

}
