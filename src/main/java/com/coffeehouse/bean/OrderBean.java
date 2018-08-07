package com.coffeehouse.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderBean {

	private Integer orderId;
	@Size(min=2, message="Customer name should be available with min of 2 characters")
	@ApiModelProperty(notes="Customer name should be available with min of 2 characters")
	private String cust_name;
	@Size(min=10, max=10, message="phone number should contains 10 digits")
	@ApiModelProperty(notes="phone number should contains 10 digits")
	private String phone_number;
	private LocalDateTime orderedDate;
	@NotNull
	@Valid
	private List<OrderItem> items;
	
	public OrderBean() {

	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
}
