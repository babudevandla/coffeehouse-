package com.coffeehouse.bean;

import java.time.LocalDateTime;
import java.util.List;

public class Reciept {

	private LocalDateTime orderedDate;
	private Double totalCost;
	private List<ItemizedBill> itemizedBill;
	
	public Reciept() {
		super();
	}
	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public List<ItemizedBill> getItemizedBill() {
		return itemizedBill;
	}
	public void setItemizedBill(List<ItemizedBill> itemizedBill) {
		this.itemizedBill = itemizedBill;
	}
	
	
	
	
}
