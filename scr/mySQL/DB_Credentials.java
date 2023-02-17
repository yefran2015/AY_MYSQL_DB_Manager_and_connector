package mySQL;

import java.io.Serializable;

public class DB_Credentials implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -566418041479073052L;
	protected String user;
	private String pass;
	private String host;
	private String hostPort;
	private String dataBase;
	
	public void setDefaultCredentials(){
		user = "school";
		pass = "";
		host = "192.168.0.102";
		hostPort = "3306";
		dataBase = "db_search_websites_robot";
	}
	
	
	DB_Credentials(){
		init();
		setDefaultCredentials();
	}
	DB_Credentials(String user, String pass, String host,
			String hostPort, String dataBase){
		setNewCredentials(user, pass, host, hostPort, dataBase);
	}
	
	public void setNewCredentials(String user, String pass, String host, String hostPort, String dataBase){
		init();
		this.user = user;
		this.pass = pass;
		this.host = host;
		this.hostPort = hostPort;
		this.dataBase = dataBase;
	}
	private void init(){
		user = "";
		pass = "";
		host = "";
		hostPort = "";
		dataBase = "";
	}

	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public String getHost() {
		return host;
	}
	public String getHostPort() {
		return hostPort;
	}
	public String getDataBase() {
		return dataBase;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
