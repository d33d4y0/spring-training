package com.github.d33d4y0.training.elasticsearch.domain;

public class Address {

	private String country;
	private String province;
	private String district;

	public Address() {

	}

	public Address(String country, String province, String district) {
		super();
		this.country = country;
		this.province = province;
		this.district = district;
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
