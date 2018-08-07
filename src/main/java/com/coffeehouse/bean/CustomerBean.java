package com.coffeehouse.bean;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CustomerBean {

	private Integer cust_id;
	@Size(min=2, message="Name shoule contains minimum of 2 letters")
	@ApiModelProperty(notes="Customer name should be available with min of 2 characters")
	private String cust_name;
	@Size(min=10, max=10, message="phone number should contains 10 digits")
	@ApiModelProperty(notes="phone number should contains 10 digits")
	private String phone;
	
	public CustomerBean() {
		
	}
	
	public CustomerBean(String name, String phone) {
		this.cust_name=name;
		this.phone=phone;
	}
	
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
