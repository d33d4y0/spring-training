package com.github.d33d4y0.training.jpa.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.d33d4y0.training.jpa.dto.CustomerDto;
import com.github.d33d4y0.training.jpa.dto.PhoneDto;
import com.github.d33d4y0.training.jpa.dto.PromotionCodeDto;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Embedded
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "creditCard_id", referencedColumnName = "id")
	@JsonIgnore
	private CreditCardEntity creditCard;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private List<PhoneEntity> phones;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
	private List<PromotionCodeEntity> promoCodes;

	public CustomerEntity() {

	}

	public CustomerEntity(CustomerDto dto) {
		super();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.address = dto.getAddress();
		this.creditCard = new CreditCardEntity(dto.getCreditCard());
		this.phones = new LinkedList<>();
		if (dto.getPhones() != null) {
			for (PhoneDto phoneDto : dto.getPhones()) {
				phones.add(new PhoneEntity(phoneDto));
			}
		}
		this.promoCodes = new LinkedList<>();
		if (dto.getPromos() != null) {
			for (PromotionCodeDto phoneDto : dto.getPromos()) {
				promoCodes.add(new PromotionCodeEntity(phoneDto));
			}
		}
	}

	public CustomerEntity(CustomerEntity customer, CreditCardEntity creditCard, List<PhoneEntity> phones) {
		super();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.address = customer.getAddress();
		this.creditCard = creditCard;
		this.phones = phones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}

	public List<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

	public List<PromotionCodeEntity> getPromoCodes() {
		return promoCodes;
	}

	public void setPromoCodes(List<PromotionCodeEntity> promoCodes) {
		this.promoCodes = promoCodes;
	}

}
