package com.github.d33d4y0.training.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.d33d4y0.training.jpa.dto.PhoneDto;

@Entity
@Table(name = "phone")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String phoneNumber;
	@JsonIgnore
	@ManyToOne
	private CustomerEntity customer;

	public PhoneEntity() {

	}

	public PhoneEntity(PhoneDto dto) {
		super();
		this.phoneNumber = dto.getPhoneNumber();
	}

	public PhoneEntity(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}
	
	public PhoneEntity(String phoneNumber, CustomerEntity customer) {
		super();
		this.phoneNumber = phoneNumber;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}
