package com.coffeehouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.coffeehouse.bean.CoffeeBean;
import com.coffeehouse.bean.CustomerBean;
import com.coffeehouse.bean.ItemizedBill;
import com.coffeehouse.bean.OrderBean;
import com.coffeehouse.bean.Reciept;
import com.coffeehouse.bean.ReportBean;
import com.coffeehouse.utils.JdbcCallback;
import com.coffeehouse.utils.JdbcTemplate;

@Repository
public class CoffeeHouseDaoImpl extends JdbcTemplate implements CoffeeHouseDao{

	@Override
	public void helloWorld() {
		System.out.println("From dao");
		LocalDateTime start = LocalDate.now().atTime(00, 00, 01);
		LocalDateTime end = LocalDate.now().atTime(23, 59, 59);
		String sql2 = "select * from  order_dtls where date>? and date<?";
		Object obj=null;
		try {
		 obj=executeReadOperation(con->{
			
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setObject(1, start);
				ps.setObject(2, end);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					System.out.println(rs.getInt("order_id")+"     updated");
					System.out.println(rs.getTime("date"));
					System.out.println(rs.getString("coffee_name"));
					System.out.println(rs.getString("cust_name"));
					System.out.println(rs.getString("phone_number"));
					
				}
				
				return null;
		});
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	
	@Override
	public String addCoffeeItem(CoffeeBean coffeeBean) {
		String  sql ="insert into coffee_dtls (coffee_name, description, quantity, price_per_cup) values( ?,?, ?, ?)";
		try {
			executeWriteOperation(new JdbcCallback() {
				public Object performOperation(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, coffeeBean.getCoffee_name());	
					ps.setString(2, coffeeBean.getCoffee_desc());
					ps.setInt(3, coffeeBean.getNoOfServes());
					ps.setDouble(4, coffeeBean.getPricePerCup());
					
					ps.executeUpdate();
					return null;
				}
			});
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}
	@Override
	public String addCustomer(CustomerBean customer) {
		String sql = "insert into customer_dtls (cust_name, phone_number) values(?,?)";
		
		try {
			
			executeWriteOperation(new JdbcCallback() {
				public Object performOperation(Connection con) throws SQLException {
					
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, customer.getCust_name());
					ps.setString(2, customer.getPhone());
					ps.executeUpdate();
					return null;
				}
			});
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}
	@Override
	public String placeOrder(OrderBean order) {

		String sql = "insert into order_dtls (date, coffee_name, cust_name, phone_number,quantity)"
				+ " values(?,?,?,?,?)";
		try {
			executeWriteOperation(new JdbcCallback() {
				public Object performOperation(Connection con) throws SQLException {
					
			PreparedStatement ps = con.prepareStatement(sql);
			order.getItems().forEach(i->{
				try {
					ps.setObject(1, LocalDateTime.now());
					ps.setString(2, i.getItemName());
					ps.setString(3, order.getCust_name());
					ps.setString(4, order.getPhone_number());
					ps.setInt(5, i.getQuantity());
					ps.addBatch();
				} catch (SQLException e) {e.printStackTrace();	}
			});
			ps.executeBatch();
			return null;
				}
			});
			
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}


	@Override
	public CoffeeBean getCoffeeItem(String coffeeName) {
		String sql="select * from coffee_dtls where coffee_name=?";
		CoffeeBean coffee=null;
		try {
		coffee=(CoffeeBean)executeReadOperation(con->{
				CoffeeBean coffeeBean = null;
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, coffeeName);
				ResultSet rs =ps.executeQuery();
				while(rs.next()) {
					coffeeBean =new CoffeeBean();
					coffeeBean.setCoffeeId(rs.getInt("coffee_id"));
					coffeeBean.setCoffee_name(rs.getString("coffee_name"));
					coffeeBean.setCoffee_desc(rs.getString("description"));
					coffeeBean.setNoOfServes(rs.getInt("quantity"));
					coffeeBean.setPricePerCup(rs.getDouble("price_per_cup"));
				}
				return coffeeBean;
		});
		
		}catch(Exception e) {e.printStackTrace();}
		return coffee;
	}


	@Override
	public CustomerBean getCustomer(String phoneNumber) {
		String sql="select * from customer_dtls where phone_number=?";
		CustomerBean customer=null;
		try {
			customer =(CustomerBean)executeReadOperation(con-> {
						CustomerBean customerBean =null;
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, phoneNumber);
						
						ResultSet rs =ps.executeQuery();
						while(rs.next()) {
							customerBean =new CustomerBean();
							customerBean.setCust_id(rs.getInt("cust_id"));
							customerBean.setCust_name(rs.getString("cust_name"));
							customerBean.setPhone(rs.getString("phone_number"));
						}
						return customerBean;
			});		
		}catch(Exception e) {e.printStackTrace();}
		return customer;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CoffeeBean> getListOfCoffeeItems() {
		LocalDateTime start = LocalDate.now().atTime(00, 00, 01);
		LocalDateTime end = LocalDate.now().atTime(23, 59, 59);
		/*String sql="select o.coffee_name, c.quantity, (c.quantity-sum(o.quantity))as available, price_per_cup from order_dtls o, coffee_dtls c "
				+ "where o.date>? and o.date<?  group by o.coffee_name;";*/
		
		String sql="select c.coffee_name, c.quantity, (c.quantity-ifnull(sum(o.quantity), 0))as available, price_per_cup  from  coffee_dtls c left join  order_dtls o on c.coffee_name=o.coffee_name"
				+ " and o.date>? and o.date<?  group by c.coffee_name;";
		List<CoffeeBean> coffeeBeanList =null;
		try {
			coffeeBeanList =(List<CoffeeBean>)executeReadOperation(con-> {
				List<CoffeeBean>	list =new  ArrayList<>();	
				CoffeeBean coffee =null;
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setObject(1, start);
				ps.setObject(2, end);
				
				ResultSet rs =ps.executeQuery();
				while(rs.next()) {
					coffee=new CoffeeBean();
					coffee.setCoffee_name(rs.getString("coffee_name"));
					coffee.setNoOfServes(rs.getInt("quantity"));
					coffee.setAvailableQunatity(rs.getInt("available"));
					coffee.setPricePerCup(rs.getDouble("price_per_cup"));
					list.add(coffee);
				}
				return list;
			});		
		}catch(Exception e) {e.printStackTrace();}
		return coffeeBeanList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ReportBean> getDaillyReport() {

		LocalDateTime start = LocalDate.now().atTime(00, 00, 01);
		LocalDateTime end = LocalDate.now().atTime(23, 59, 59);
		
		String sql="select c.coffee_name, c.quantity, ifnull(sum(o.quantity),0)as totalServedForTheDay from coffee_dtls c left join order_dtls o on  c.coffee_name =o.coffee_name "
				+ "and date>? and date<?  group by c.coffee_name;";
		List<ReportBean> ReportBeanList =null;
		try {
			ReportBeanList =(List<ReportBean>)executeReadOperation(con-> {
				List<ReportBean>	list =new  ArrayList<>();	
				ReportBean report =null;
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setObject(1, start);
				ps.setObject(2, end);
				
				ResultSet rs =ps.executeQuery();
				while(rs.next()) {
					report=new ReportBean();
					report.setCoffeeName(rs.getString("coffee_name"));
					report.setTotalServesPerDay(rs.getInt("quantity"));
					report.setTotalServesSoldPerDay(rs.getInt("totalServedForTheDay"));
					list.add(report);
				}
				return list;
			});		
		}catch(Exception e) {e.printStackTrace();}
		
		return ReportBeanList;
	}


	/*@SuppressWarnings("unchecked")
	@Override
	public Reciept generateReceipt(OrderBean order, Set<String> orderedCoffes) {

		Reciept reciept=new Reciept();
		List<ItemizedBill> itemizedbillList = null;
		
		String sql="select coffee_name, price_per_cup from coffee_dtls where coffee_name in (";
		for(String coffee:orderedCoffes) {
			if(!sql.contains(","))
				sql += coffee;
			else
				sql += ","+coffee;
		}
		sql+=")";
		try {
			itemizedbillList =(List<ItemizedBill>)executeReadOperation(con->{
				
				List<ItemizedBill> itemized =new ArrayList<>();
				ItemizedBill ib = null;
				Statement stmt = con.createStatement();
				ResultSet rs =stmt.executeQuery(sql);
				while(rs.next()) {
					ib =new ItemizedBill();
					ib.setCoffee_name(rs.getString("coffee_name"));
					ib.setPrice(rs.getDouble("price_per_cup"));
					itemized.add(ib);
				}
				
				return itemized;
			});
		}catch(Exception e) {e.printStackTrace();}
		
		
		return null;
	}*/

}
