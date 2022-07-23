package com.github.d33d4y0.training.mongodb.dto;

import com.github.d33d4y0.training.mongodb.entity.CustomerEntity;

public class CustomerDTO {

	private String firstName;
	private String lastName;
	private Integer age;
	private String citizenID;

	public CustomerDTO() {

	}

	public CustomerDTO(CustomerEntity entity) {
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.age = entity.getAge();
		this.citizenID = entity.getCitizenId();
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

}
