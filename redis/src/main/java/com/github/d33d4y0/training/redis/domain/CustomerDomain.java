package com.github.d33d4y0.training.redis.domain;

import com.github.d33d4y0.training.redis.entity.CustomerEntity;

public class CustomerDomain {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String citizenID;
	private AddressDomain address;

	public CustomerDomain() {

	}

	public CustomerDomain(CustomerEntity entity) {
		setAddress(entity.getAddress());
		setAge(entity.getAge());
		setCitizenID(entity.getCitizenId());
		setFirstName(entity.getFirstName());
		setLastName(entity.getLastName());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}

	public AddressDomain getAddress() {
		return address;
	}

	public void setAddress(AddressDomain address) {
		this.address = address;
	}
	
	public CustomerEntity toEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setAddress(address);
		entity.setAge(age);
		entity.setCitizenId(citizenID);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		return entity;
	}
}
