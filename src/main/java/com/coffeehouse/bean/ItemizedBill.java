package com.coffeehouse.bean;

public class ItemizedBill {

	private String coffee_name;
	private Integer quantity;
	private Double pricePerCup;
	private Double price;
	
	public ItemizedBill() {
		super();
	}
	
	public String getCoffee_name() {
		return coffee_name;
	}
	public void setCoffee_name(String coffee_name) {
		this.coffee_name = coffee_name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPricePerCup() {
		return pricePerCup;
	}

	public void setPricePerCup(Double pricePerCup) {
		this.pricePerCup = pricePerCup;
	}
	
	
}
