package com.github.d33d4y0.training.elasticsearch.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.d33d4y0.training.elasticsearch.domain.Address;
import com.github.d33d4y0.training.elasticsearch.domain.CourseDomain;

@Document(indexName = "student-index")
public class StudentEntity {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	private String name;
	private String citizenId;
	private int age;
	@Field(type = FieldType.Nested)
	private Map<String, CourseDomain> registeredCourses = new HashMap<>();
	@Field(type = FieldType.Date, store = true, format = DateFormat.date_hour_minute_second_millis)
	@JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime registeredDateTime;
	private List<String> tags;
	private boolean graduated;
	@Field(type = FieldType.Nested)
	private Address address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, CourseDomain> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(Map<String, CourseDomain> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public LocalDateTime getRegisteredDateTime() {
		return registeredDateTime;
	}

	public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
		this.registeredDateTime = registeredDateTime;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isGraduated() {
		return graduated;
	}

	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
