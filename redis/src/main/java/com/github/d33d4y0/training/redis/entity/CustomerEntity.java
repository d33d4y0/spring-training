package com.github.d33d4y0.training.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.github.d33d4y0.training.redis.domain.AddressDomain;

@RedisHash("customer")
public class CustomerEntity {

	@Id
	private String id;
	@Indexed
	private String firstName;
	@Indexed
	private String lastName;
	@Indexed
	private String citizenId;
	@Indexed
	private Integer age;
	@Indexed
	private AddressDomain address;

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

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public AddressDomain getAddress() {
		return address;
	}

	public void setAddress(AddressDomain address) {
		this.address = address;
	}

}
