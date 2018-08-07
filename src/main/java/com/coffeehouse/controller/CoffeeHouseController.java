package com.coffeehouse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coffeehouse.bean.CoffeeBean;
import com.coffeehouse.bean.CustomerBean;
import com.coffeehouse.bean.OrderBean;
import com.coffeehouse.bean.Reciept;
import com.coffeehouse.bean.ReportBean;
import com.coffeehouse.service.CoffeeHouseService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class CoffeeHouseController {

	@Autowired 
	CoffeeHouseService coffeeHouseService;
	
	@GetMapping(path="/coffee-house")
	public String helloWorld() {
		
		coffeeHouseService.helloWorld();
		
		return "Hellow World";
	}
	
	@PostMapping(path="/addCoffeeItem") 
	public String addCoffeeItem(@Valid @RequestBody CoffeeBean coffeeBean) {
		
		coffeeHouseService.addCoffeeItem(coffeeBean);
		return "Added new Coffee Item";
	}
	
	@GetMapping(path="/getCoffeeItem/{coffeeName}")
	public MappingJacksonValue getCoffeeItem(@PathVariable String coffeeName) {
		CoffeeBean coffeeBean =coffeeHouseService.getCoffeeItem(coffeeName);
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.
				filterOutAllExcept("coffeeId","coffee_name","coffee_desc","noOfServes","pricePerCup");
		FilterProvider filters= new SimpleFilterProvider().addFilter("CoffeeFiltering", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(coffeeBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@PostMapping(path="/addCustomer") 
	public String addCustomer(@Valid @RequestBody CustomerBean customerBean) {
		
		coffeeHouseService.addCustomer(customerBean);
		return "Added new Customer";
	}
	
	@GetMapping(path="/getCustomer/name/{name}/phone/{phone}")
	public CustomerBean getCustomer(@PathVariable String name,@PathVariable String phone) {
		return coffeeHouseService.getCustomer(name,phone);
	}
	@PostMapping(path="/placeOrder")
	public Reciept placeOrder(@Valid  @RequestBody OrderBean order) {
		
		Reciept reciept =coffeeHouseService.placeOrder(order);
		return reciept;
	}
	
	
	@GetMapping(path="/getListOfCoffeeItems")
	public MappingJacksonValue getListOfCoffeeItems(){
		List<CoffeeBean> list=coffeeHouseService.getListOfCoffeeItems();
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.
				filterOutAllExcept("coffee_name","noOfServes","availableQunatity");
		FilterProvider filters= new SimpleFilterProvider().addFilter("CoffeeFiltering", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping(path="getDaillyReport")
	public List<ReportBean> getDaillyReport() {
		return coffeeHouseService.getDaillyReport();
	}
}
