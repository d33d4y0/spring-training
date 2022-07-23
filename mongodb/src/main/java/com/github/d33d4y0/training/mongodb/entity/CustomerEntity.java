package com.github.d33d4y0.training.mongodb.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.github.d33d4y0.training.mongodb.domain.AddressDomain;

@Document(collection = "customer-index")
public class CustomerEntity {

	@Id
	private String id;
	@Field(targetType = FieldType.STRING)
	private String name;
	@Field(targetType = FieldType.INT32)
	private Integer age;
	@Field(targetType = FieldType.STRING)
//	for unique field
	@Indexed(unique = true)
	private String citizenId;
	@Field(targetType = FieldType.DATE_TIME)
	private LocalDateTime registeredDateTime;
	private AddressDomain address;
	private boolean active;

	public CustomerEntity() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public LocalDateTime getRegisteredDateTime() {
		return registeredDateTime;
	}

	public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
		this.registeredDateTime = registeredDateTime;
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

}
