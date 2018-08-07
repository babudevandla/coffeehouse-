package com.coffeehouse.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class OrderItem {

	@Size(min=2, message="Item name should be available with min of 2 characters")
	@ApiModelProperty(notes="Item name should be available with min of 2 characters")
	private String itemName;
	@Min(value=1, message="Quantity should be min 1")
	@ApiModelProperty(notes="Quantity should be min 1")
	private Integer quantity;
	
	public OrderItem() {
		
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
