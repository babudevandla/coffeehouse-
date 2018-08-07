package com.coffeehouse.dao;

import java.util.List;
import java.util.Set;

import com.coffeehouse.bean.CoffeeBean;
import com.coffeehouse.bean.CustomerBean;
import com.coffeehouse.bean.OrderBean;
import com.coffeehouse.bean.Reciept;
import com.coffeehouse.bean.ReportBean;

public interface CoffeeHouseDao {

	public void helloWorld();
	public CoffeeBean getCoffeeItem(String coffeeName);
	public CustomerBean getCustomer(String phoneNumber);
	public List<CoffeeBean> getListOfCoffeeItems();
	public String addCoffeeItem(CoffeeBean coffeeBean);
	public String addCustomer(CustomerBean customer);
	public String placeOrder(OrderBean order);
	public List<ReportBean> getDaillyReport();
	//public Reciept generateReceipt(OrderBean order, Set<String> orderedCoffes);
}
