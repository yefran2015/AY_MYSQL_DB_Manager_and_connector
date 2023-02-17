package mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.TestCase;


public class Test_DB_Handler extends TestCase{
	
	
	public void testConnection() throws SQLException{
		DB_Handler dbHndlr = new DB_Handler();
		ResultSet resultSet = null;
		
		dbHndlr.establishConn("school", "andrey", "192.168.0.102", "3306", "db_search_websites_robot");
		resultSet = dbHndlr.executeQuery("SELECT * FROM addresses3");
		while(resultSet.next()){
			System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5));
			
		}
		
		
		
		dbHndlr.terminateConn();
	}
}
