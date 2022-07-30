package com.github.d33d4y0.training.jpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.d33d4y0.training.jpa.entity.Address;
import com.github.d33d4y0.training.jpa.entity.CustomerEntity;

@JsonInclude(Include.NON_NULL)
public class CustomerDto {

	private String firstName;
	private String lastName;
	private Address address;

	public CustomerDto() {

	}

	public CustomerDto(CustomerEntity entity) {
		super();
		if (entity != null) {
			this.firstName = entity.getFirstName();
			this.lastName = entity.getLastName();
			this.address = entity.getAddress();
		}
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
