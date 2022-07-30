package com.github.d33d4y0.training.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.d33d4y0.training.jpa.dto.CreditCardDto;

@Entity
@Table(name = "credit_card")
public class CreditCardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(unique=true)
	private String cardNumber;
	private String cvv;
	@OneToOne(mappedBy = "creditCard")
	@JsonIgnore
	private CustomerEntity customer;

	public CreditCardEntity() {
	}
	
	public CreditCardEntity(CreditCardDto dto) {
		this.name = dto.getName();
		this.cardNumber = dto.getCardNumber();
		this.cvv = dto.getCvv();
	}
	
	public CreditCardEntity(String name, String cardNumber, String cvv) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}
