package com.coffeehouse.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonFilter("CoffeeFiltering")
public class CoffeeBean {

	private Integer coffeeId;
	@Size(min=2, message="Name should contains minimum of 2 characters")
	@ApiModelProperty(notes="Name should contains minimum of 2 characters")
	private String coffee_name;
	private String coffee_desc;
	
	@Min(value=1, message="the quantity should be minimum of 1")
	@ApiModelProperty(notes="the quantity should be minimum of 1")
	private Integer noOfServes;
	private Integer availableQunatity;
	
	@Min(value=1, message="the price should be minimum of 1")
	@ApiModelProperty(notes="the price should be minimum of 1")
	private Double pricePerCup;
	
	public CoffeeBean() {
		
	}
	public Integer getCoffeeId() {
		return coffeeId;
	}
	public void setCoffeeId(Integer coffeeId) {
		this.coffeeId = coffeeId;
	}
	public String getCoffee_name() {
		return coffee_name;
	}
	public void setCoffee_name(String coffee_name) {
		this.coffee_name = coffee_name;
	}
	public String getCoffee_desc() {
		return coffee_desc;
	}
	public void setCoffee_desc(String coffee_desc) {
		this.coffee_desc = coffee_desc;
	}
	
	public Double getPricePerCup() {
		return pricePerCup;
	}
	public void setPricePerCup(Double pricePerCup) {
		this.pricePerCup = pricePerCup;
	}
	public Integer getNoOfServes() {
		return noOfServes;
	}
	public void setNoOfServes(Integer noOfServes) {
		this.noOfServes = noOfServes;
	}
	public Integer getAvailableQunatity() {
		return availableQunatity;
	}
	public void setAvailableQunatity(Integer availableQunatity) {
		this.availableQunatity = availableQunatity;
	}
	
	
	
}
