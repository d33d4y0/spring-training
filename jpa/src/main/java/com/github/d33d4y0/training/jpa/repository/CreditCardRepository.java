package com.github.d33d4y0.training.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.d33d4y0.training.jpa.entity.CreditCardEntity;

public interface CreditCardRepository extends PagingAndSortingRepository<CreditCardEntity, Long> {

	CreditCardEntity findByCardNumber(String cardNumber);
}