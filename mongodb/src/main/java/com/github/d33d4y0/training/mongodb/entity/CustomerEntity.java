package com.github.d33d4y0.training.mongodb.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.github.d33d4y0.training.mongodb.dto.CustomerDTO;

@Document(collection = "customer-index")
public class CustomerEntity {

	@Id
	private String id;
	@NotNull
	@Field(targetType = FieldType.STRING)
	private String firstName;
	@NotNull
	@Field(targetType = FieldType.STRING)
	private String lastName;
	@NotNull
	@Field(targetType = FieldType.INT32)
	private Integer age;
	@NotNull
	@Field(targetType = FieldType.STRING)
//	for unique field
	@Indexed(unique = true)
	private String citizenId;

	public CustomerEntity() {

	}

	public CustomerEntity(@NotNull String firstName, @NotNull String lastName, @NotNull Integer age,
			@NotNull String citizenId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.citizenId = citizenId;
	}
	
	public CustomerEntity(CustomerDTO customer) {
		super();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.age = customer.getAge();
		this.citizenId = customer.getCitizenID();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
