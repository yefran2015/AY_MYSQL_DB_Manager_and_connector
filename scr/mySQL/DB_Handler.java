package mySQL;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

public class DB_Handler {
	private Stack<String> commandsHistory = new Stack<String>();
	static String defaultQuery = "";
	private Connection conn = null;
	private Statement statement;
	private ResultSet resultSet;
//	private DB_Encoder encoder;
	private DB_Credentials  credentials;
	
	
	DB_Handler(){
		init();
		credentials = new DB_Credentials();
		//this.encoder=encoder;
		
	}
	public void setDefaultCredentials(){
		credentials.setDefaultCredentials();
	}

	private void init(){
		defaultQuery="SELECT * FROM addresses4";
		credentials=null;
		conn = null;
		statement = null;
		resultSet = null;
	}
	public String establishConn(){
		
		String url = "jdbc:mysql://"+credentials.getHost()+":"+credentials.getHostPort()+"/"+credentials.getDataBase();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, credentials.getUser(), credentials.getPass());
			//System.out.println("Database connection established");
			
		}catch (Exception e){
			return e.getMessage();

		}
		return "Database connection established";
	}
	
	public String establishConn(String user, String pass, String host, String hostPort, String dataBase){
		init();
		credentials = new DB_Credentials(user,pass, host, hostPort, dataBase);
		
		return establishConn();
	}
	
	public ResultSet readAllRecords() throws SQLException{
		return executeQuery("SELECT * FROM "+credentials.getDataBase());
	}
	
	public   String resultSetToString(ResultSet resultSet, DB_Encoder encoder) throws SQLException, UnsupportedEncodingException{
		String answr = "";
		String line;
		if(resultSet!=null){
			while(resultSet.next()){	
				line=(resultSet.getInt("id")+"\t"+resultSet.getString("domain")
					+"\t"+resultSet.getString("title")+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\n");
				answr+=DB_Encoder.encode(line, encoder.getIn(), encoder.getOut());
			}
		}
		return answr;
		
	}



	
	public ResultSet executeQuery(String query) throws SQLException{
		
		
		statement = conn.createStatement();
		resultSet = statement.executeQuery(query);	
		commandsHistory.add(query);
		return resultSet;

	}
	public Stack<String> getCommandsHistory(){
		return commandsHistory;
	}
	public boolean isConnEstablished(){
		if(conn!=null)
			return true;
		else
			return false;
	}
	public String terminateConn(){
		if (conn != null){
			try{
				conn.close ();
				conn = null;
				//System.out.println ("Database connection terminated");
			}catch (Exception e) { /* ignore close errors */
				return e.getMessage();
			}
			return "Database connection terminated";
		}
		return "Error it's here no Previous connection detected";
	}
	public String getUser() {
		return credentials.getUser();
	}
	public String getPass() {
		return credentials.getPass();
	}
	public String getHost() {
		return credentials.getHost();
	}
	public String getHostPort() {
		return credentials.getHostPort();
	}
	public String getDataBase() {
		return credentials.getDataBase();
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setCredentials(DB_Credentials credentials) {
		this.credentials = credentials;
	}
	public void setCommandsHistory(Stack<String> commandsHistory) {
		this.commandsHistory = commandsHistory;
	}
	public DB_Credentials getCredentials() {
		return credentials;
	}
	
}






//private String user = "";
//private String pass = "";
//private String host = "";
//private String hostPort = "";
//private String dataBase = "";

//public void setDefaultCredentials(){
//user = "school";
//pass = "";
//host = "192.168.0.102";
//hostPort = "3306";
//dataBase = "db_search_websites_robot";
//defaultQuery="SELECT * FROM addresses4";
//}

//private void init(){
////user = "";
////pass = "";
////host = "";
////hostPort = "";
////dataBase = "";
//defaultQuery="SELECT * FROM addresses4";
//credentials=null;
//conn = null;
//command = null;
//resultSet = null;
//}


//public String establishConn(String user, String pass, String host, String hostPort, String dataBase){
//init();
//credentials = new DB_Credentials(user,pass, host, hostPort, dataBase);
////this.user = user;
////this.pass = pass;
////this.host = host;
////this.hostPort = hostPort;
////this.dataBase = dataBase;
//
//
//return establishConn();
//
//
//}


//public String establishConn(String user, String pass, String host, String hostPort, String dataBase){
//	init();
//	this.user = user;
//	this.pass = pass;
//	this.host = host;
//	this.hostPort = hostPort;
//	this.dataBase = dataBase;
//	String url = "jdbc:mysql://"+this.host+":"+this.hostPort+"/"+this.dataBase;
//	
//	
//	try{
//		Class.forName("com.mysql.jdbc.Driver");
//		conn = DriverManager.getConnection(url, this.user, this.pass);
//		//System.out.println("Database connection established");
//		
//	}catch (Exception e){
//		return e.getMessage();
//
//	}
//	return "Database connection established";
//}





//public static String[] resultSetToStringArray(ResultSet resultSet) throws SQLException{
//LinkedList<String> tmp = new LinkedList<String>();
//if(resultSet!=null){
//	while(resultSet.next()){
//		tmp.add(resultSet.getString(1)+"\t"+resultSet.getString(2)
//			+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5));
//	}
//}
//String[] answr = new String[tmp.toArray().length];
//for(int i=0;i<answr.length;i++){
//	answr[i]=(String)tmp.toArray()[i];
//}
//
//return answr;
//
