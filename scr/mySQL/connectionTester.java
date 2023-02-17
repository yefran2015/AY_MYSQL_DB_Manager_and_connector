package mySQL;

import java.sql.*;


public class connectionTester {

	private final static String USER = "school";
	private final static String PASS = "andrey";
	
	public static void main(String[] args){
		//oldWay();
		newWay();
	}
	
	private static void newWay(){
		String url = "jdbc:mysql://192.168.0.102:3306/";
		String dbName = "db_search_websites_robot";
		String driver = "com.mysql.jdbc.Driver";
		try{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,USER,PASS);
			
			conn.close();
		}catch(Exception e){
			
		}
	}
	private static void oldWay(){
		Connection conn = null;

		try
		{

			String url = "jdbc:mysql://192.168.0.102:3306/db_search_websites_robot";
			Class.forName ("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection (url, USER, PASS);
			System.out.println ("Database connection established");
		}
		catch (Exception e)
		{
			System.out.println("Errror: ");
			e.printStackTrace();

		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close ();
					System.out.println ("Database connection terminated");
				}
				catch (Exception e) { /* ignore close errors */ }
			}
		}
	}
}