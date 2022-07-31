package com.github.d33d4y0.training.mongodb.dto;

import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

public class CustomerSearchDto {

	private String name;
	private Integer age;
	private String citizenID;

	public CustomerSearchDto() {

	}

	public CustomerSearchDto(CustomerEntity entity) {
		this.name = entity.getName();
		this.age = entity.getAge();
		this.citizenID = entity.getCitizenId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
