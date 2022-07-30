package com.github.d33d4y0.training.jpa.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.d33d4y0.training.jpa.entity.Address;
import com.github.d33d4y0.training.jpa.entity.CustomerEntity;
import com.github.d33d4y0.training.jpa.entity.PhoneEntity;
import com.github.d33d4y0.training.jpa.entity.PromotionCodeEntity;

@JsonInclude(Include.NON_NULL)
public class CustomerDto {

	private String firstName;
	private String lastName;
	private Address address;
	private CreditCardDto creditCard;
	private List<PhoneDto> phones;
	private List<PromotionCodeDto> promos;

	public CustomerDto() {

	}

	public CustomerDto(CustomerEntity entity) {
		super();
		if (entity != null) {
			this.firstName = entity.getFirstName();
			this.lastName = entity.getLastName();
			this.address = entity.getAddress();
			this.creditCard = entity.getCreditCard() == null ? null : new CreditCardDto(entity.getCreditCard());
			if (entity.getPhones() != null) {
				this.phones = new LinkedList<>();
				for (PhoneEntity phone : entity.getPhones()) {
					phones.add(new PhoneDto(phone));
				}
			}
			if (entity.getPromoCodes() != null) {
				this.promos = new LinkedList<>();
				for (PromotionCodeEntity promo : entity.getPromoCodes()) {
					promos.add(new PromotionCodeDto(promo));
				}
			}
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

	public CreditCardDto getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardDto creditCard) {
		this.creditCard = creditCard;
	}

	public List<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}

	public List<PromotionCodeDto> getPromos() {
		return promos;
	}

	public void setPromos(List<PromotionCodeDto> promos) {
		this.promos = promos;
	}

}
