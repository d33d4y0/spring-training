package com.github.d33d4y0.training.jpa.dto;

import com.github.d33d4y0.training.jpa.entity.PhoneEntity;

public class PhoneDto {

	private String phoneNumber;

	public PhoneDto() {

	}

	public PhoneDto(PhoneEntity entity) {
		super();
		this.phoneNumber = entity.getPhoneNumber();
	}

	public PhoneDto(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
