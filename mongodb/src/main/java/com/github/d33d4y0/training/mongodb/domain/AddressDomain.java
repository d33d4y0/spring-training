package com.github.d33d4y0.training.mongodb.domain;

public class AddressDomain {

	private String country;
	private String province;
	private String district;

	public AddressDomain() {
		
	}
	public AddressDomain(String country, String province, String district) {
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
