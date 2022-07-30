package com.github.d33d4y0.training.jpa.entity;

public class Address {

	private String country;
	private String province;
	private String district;
	private String postalCode;

	public Address() {
	}

	public Address(String country, String province, String district, String postalCode) {
		super();
		this.country = country;
		this.province = province;
		this.district = district;
		this.postalCode = postalCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
