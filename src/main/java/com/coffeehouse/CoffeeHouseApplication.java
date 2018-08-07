package com.coffeehouse;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class CoffeeHouseApplication {

	/*private static String driverClass ="com.mysql.jdbc.Driver";
	private static String dbUserName = "root";
	private static String dbPassword="password";
	private static String dbUrl = "jdbc:mysql://localhost/coffee_house";*/
	
	private static String driverClass;
	private static String dbUserName;
	private static String dbPassword;
	private static String dbUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(CoffeeHouseApplication.class, args);
	}
	
	/*@Bean
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn =DriverManager.getConnection("jdbc:mysql://localhost/coffee_house?" +
				                                   "user=root&password=password");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}*/
	
	@Bean
	public static PropertyPlaceholderConfigurer EnvironmentPropertyPlaceholderConfigurer(){		
	   PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	   Resource[] 	resources 		= null;
	   String appProperties="mysql-db.properties";
	   resources = new ClassPathResource[ ]{ new ClassPathResource( appProperties )};
	   
	   ppc.setLocations( resources );
	   ppc.setIgnoreUnresolvablePlaceholders( true ); 	 
	   try{
		   Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/" + appProperties));
		   driverClass	=	(String) props.get("jdbc.driverClass");
		   dbUrl	=	(String) props.get("jdbc.jdbcUrl");
		   dbUserName	=	(String) props.get("jdbc.username");
		   dbPassword	=	(String) props.get("jdbc.password");

  }catch(Exception e){
		   throw new RuntimeException(e.getMessage());
	   }	  
	   return ppc;
	}
	
	
	@Bean(name="dataSource",destroyMethod = "")
	private static DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName( driverClass );
        ds.setUsername( dbUserName );
        ds.setPassword( dbPassword );
        ds.setUrl( dbUrl );        
        return ds;
    }
}
