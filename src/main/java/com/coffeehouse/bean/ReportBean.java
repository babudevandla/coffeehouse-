package com.coffeehouse.bean;

public class ReportBean {

	private String coffeeName;
	private Integer totalServesPerDay;
	private Integer totalServesSoldPerDay;
	
	public ReportBean() {
		super();
	}
	public String getCoffeeName() {
		return coffeeName;
	}
	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	public Integer getTotalServesPerDay() {
		return totalServesPerDay;
	}
	public void setTotalServesPerDay(Integer totalServesPerDay) {
		this.totalServesPerDay = totalServesPerDay;
	}
	public Integer getTotalServesSoldPerDay() {
		return totalServesSoldPerDay;
	}
	public void setTotalServesSoldPerDay(Integer totalServesSoldPerDay) {
		this.totalServesSoldPerDay = totalServesSoldPerDay;
	}
	

	
	
	
}
