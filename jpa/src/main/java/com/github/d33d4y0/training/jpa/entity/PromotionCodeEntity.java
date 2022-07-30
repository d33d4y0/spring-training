package com.github.d33d4y0.training.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.github.d33d4y0.training.jpa.dto.PromotionCodeDto;

@Entity
@Table(name = "promotion")
public class PromotionCodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String code;
	private Double discount;
	@ManyToMany(mappedBy = "promoCodes")
	private List<CustomerEntity> customers;

	public PromotionCodeEntity() {

	}

	public PromotionCodeEntity(PromotionCodeDto dto) {
		super();
		this.code = dto.getCode();
		this.discount = dto.getDiscount();
	}
	
	public PromotionCodeEntity(String code, Double discount) {
		super();
		this.code = code;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

}
