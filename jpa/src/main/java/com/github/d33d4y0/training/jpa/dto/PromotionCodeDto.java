package com.github.d33d4y0.training.jpa.dto;

import com.github.d33d4y0.training.jpa.entity.PromotionCodeEntity;

public class PromotionCodeDto {

	private String code;
	private Double discount;

	public PromotionCodeDto() {

	}

	public PromotionCodeDto(PromotionCodeEntity entity) {
		super();
		this.code = entity.getCode();
		this.discount = entity.getDiscount();
	}

	public PromotionCodeDto(String code, Double discount) {
		super();
		this.code = code;
		this.discount = discount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
