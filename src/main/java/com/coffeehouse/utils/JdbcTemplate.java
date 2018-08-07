package com.coffeehouse.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplate extends AbstractDAO{
	
	public Object executeWriteOperation(JdbcCallback jdbcCallback)
			throws Exception {
		Object object = null;
		Connection con = null;
		try {
				con = getConnectionForUpdate();
				con.setAutoCommit(false);
				object = jdbcCallback.performOperation(con);
				con.commit();
				con.setAutoCommit(true);
			
		} catch (SQLException e) {
			try{con.rollback();}catch(SQLException e2){e2.printStackTrace();};
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			closeConnection(con);
		}
		return object;
	}
	
	
	public Object executeReadOperation(JdbcCallback jdbcCallback)
			throws Exception {
		Object object = null;
		Connection con = null;
		try {
			con =getConnectionForSelect();
			object = jdbcCallback.performOperation(con);	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			closeConnection(con);
		}
		return object;
	}
	private void closeConnection(Connection con){
		try{if(con!=null)con.close();}catch(SQLException e){}
	}
}
