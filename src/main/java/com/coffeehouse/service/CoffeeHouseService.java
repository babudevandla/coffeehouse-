package com.coffeehouse.service;

import java.util.List;

import com.coffeehouse.bean.CoffeeBean;
import com.coffeehouse.bean.CustomerBean;
import com.coffeehouse.bean.OrderBean;
import com.coffeehouse.bean.Reciept;
import com.coffeehouse.bean.ReportBean;

public interface CoffeeHouseService {

	public void helloWorld();
	public CoffeeBean getCoffeeItem(String coffeeName);
	public CustomerBean getCustomer(String name, String phoneNumber);
	public String addCoffeeItem(CoffeeBean coffeeBean);
	public String addCustomer(CustomerBean customer);
	public Reciept placeOrder(OrderBean order);
	public List<CoffeeBean> getListOfCoffeeItems();
	public List<ReportBean> getDaillyReport();
}
