package com.coffeehouse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeehouse.bean.CoffeeBean;
import com.coffeehouse.bean.CustomerBean;
import com.coffeehouse.bean.ItemizedBill;
import com.coffeehouse.bean.OrderBean;
import com.coffeehouse.bean.OrderItem;
import com.coffeehouse.bean.Reciept;
import com.coffeehouse.bean.ReportBean;
import com.coffeehouse.dao.CoffeeHouseDao;
import com.coffeehouse.exceptions.CoffeeItemAlreadyExistsException;
import com.coffeehouse.exceptions.CoffeeItemNotExistsException;
import com.coffeehouse.exceptions.SufficientQunatityNotAvailableException;

@Service
public class CoffeeHouseServiceImpl implements CoffeeHouseService{

	@Autowired CoffeeHouseDao coffeeHouseDao;
	
	@Autowired
	public void helloWorld() {
		
		System.out.println("From Service");
		coffeeHouseDao.helloWorld();
	}

	@Override
	public String addCoffeeItem(CoffeeBean coffeeBean) {
		CoffeeBean existCoffe=coffeeHouseDao.getCoffeeItem(coffeeBean.getCoffee_name());
		if(existCoffe!=null) throw new CoffeeItemAlreadyExistsException("Coffee Item you want to add is "
				+ "already exists with name "+coffeeBean.getCoffee_name());
		coffeeHouseDao.addCoffeeItem(coffeeBean);
		return "";
	}

	@Override
	public String addCustomer(CustomerBean customer) {
		return coffeeHouseDao.addCustomer(customer);
	}

	@Override
	public Reciept placeOrder(OrderBean order) {
		Set<String> listOfOrderedCoffes=new HashSet<>();
		Map<String, Double> coffee_price_map =new HashMap<>();
		List<CoffeeBean> availableCoffeeItems =coffeeHouseDao.getListOfCoffeeItems();
		CustomerBean existCustomer=coffeeHouseDao.getCustomer(order.getPhone_number());
		if(existCustomer==null) {
			coffeeHouseDao.addCustomer(new CustomerBean(order.getCust_name(), order.getPhone_number()));
		}			
		
		if(availableCoffeeItems!=null && availableCoffeeItems.size()>0) {
			
			Map<String, Integer> coffee_avail_quantity =new HashMap<>();
			availableCoffeeItems.forEach(item -> {
				coffee_avail_quantity.put(item.getCoffee_name(), item.getAvailableQunatity());
				coffee_price_map.put(item.getCoffee_name(), item.getPricePerCup());
			});
			
			Set<String> existingCoffeeList = coffee_avail_quantity.keySet();
			
			if(coffee_avail_quantity!=null) {
				order.getItems().forEach(orderItem->{
					if(!existingCoffeeList.contains(orderItem.getItemName()))
						throw new CoffeeItemNotExistsException("Coffee "+orderItem.getItemName()+" you tried to order is not available with us, kindly please order with existing list");
					if(coffee_avail_quantity.get(orderItem.getItemName()) <=orderItem.getQuantity())
						throw new SufficientQunatityNotAvailableException("sufficient quantity not availalbe for your order");
					listOfOrderedCoffes.add(orderItem.getItemName());
			});
			
			}
		}
		coffeeHouseDao.placeOrder(order);
		return this.generateReceipt(order, coffee_price_map);
		
	}

	@SuppressWarnings("unused")
	private Reciept generateReceipt(OrderBean order, Map<String, Double> coffee_price_map) {
		Reciept reciept=new Reciept();
		List<ItemizedBill> itemizedBill =new ArrayList<>();
		ItemizedBill itemized =null;
		
		double totalPrice =0;
		
		for(OrderItem item : order.getItems()) {
			itemized = new ItemizedBill();
			itemized.setCoffee_name(item.getItemName());
			itemized.setQuantity(item.getQuantity());
			itemized.setPricePerCup(coffee_price_map.get(item.getItemName()));
			itemized.setPrice(item.getQuantity()*coffee_price_map.get(item.getItemName()));
			totalPrice+=itemized.getPrice();
			itemizedBill.add(itemized);
		}
		
		reciept.setOrderedDate(LocalDateTime.now());
		reciept.setTotalCost(totalPrice);
		reciept.setItemizedBill(itemizedBill);
		return reciept;
	}
	@Override
	public CoffeeBean getCoffeeItem(String coffeeName) {
		return coffeeHouseDao.getCoffeeItem(coffeeName);
	}

	@Override
	public CustomerBean getCustomer(String name, String phoneNumber) {
		
		return coffeeHouseDao.getCustomer(phoneNumber);
	}

	@Override
	public List<CoffeeBean> getListOfCoffeeItems() {
		return  coffeeHouseDao.getListOfCoffeeItems();
	}

	@Override
	public List<ReportBean> getDaillyReport() {
		return coffeeHouseDao.getDaillyReport();
	}
	
}
