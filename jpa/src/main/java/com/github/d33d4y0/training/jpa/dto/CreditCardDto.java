package com.github.d33d4y0.training.jpa.dto;

import com.github.d33d4y0.training.jpa.entity.CreditCardEntity;

public class CreditCardDto {

	private String name;
	private String cardNumber;
	private String cvv;

	public CreditCardDto() {

	}

	public CreditCardDto(CreditCardEntity entity) {
		this.name = entity.getName();
		this.cardNumber = entity.getCardNumber();
		this.cvv = entity.getCvv();
	}
	
	public CreditCardDto(String name, String cardNumber, String cvv) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}
