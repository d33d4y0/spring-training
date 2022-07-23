package com.github.d33d4y0.training.mongodb.domain;

import java.time.LocalDateTime;

import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

public class CustomerDomain {

	private String name;
	private Integer age;
	private String citizenId;
	private AddressDomain address;
	private boolean active;
	private LocalDateTime registeredDateTime;

	public CustomerDomain() {

	}

	public CustomerDomain(CustomerEntity entity) {
		this.name = entity.getName();
		this.age = entity.getAge();
		this.citizenId = entity.getCitizenId();
		this.address = entity.getAddress();
		this.active = entity.isActive();
		this.registeredDateTime = entity.getRegisteredDateTime();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDomain getAddress() {
		return address;
	}

	public void setAddress(AddressDomain address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getRegisteredDateTime() {
		return registeredDateTime;
	}

	public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
		this.registeredDateTime = registeredDateTime;
	}

	public CustomerEntity toEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setAddress(address);
		entity.setAge(age);
		entity.setCitizenId(citizenId);
		entity.setName(name);
		entity.setActive(active);
		entity.setRegisteredDateTime(registeredDateTime);
		return entity;
	}
}
