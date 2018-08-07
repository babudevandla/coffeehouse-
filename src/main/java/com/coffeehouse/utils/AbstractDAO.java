package com.coffeehouse.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AbstractDAO {
	@Autowired
	DriverManagerDataSource dataSource = null;
	ApplicationContext context;
	public DriverManagerDataSource getDataSource() {
		if(dataSource==null)
			dataSource = (DriverManagerDataSource)context.getBean("dataSource");
		return dataSource;
	}
	
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnectionForSelect() throws SQLException{
		Connection connection = getDataSource().getConnection();
		connection.setReadOnly(true);
		return connection;
	}
	
	public Connection getConnectionForUpdate() throws SQLException{

		Connection connection = getDataSource().getConnection();
		if(connection.isReadOnly())
			connection.setReadOnly(false);

		return connection;
	}
}
