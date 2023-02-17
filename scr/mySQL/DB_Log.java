package mySQL;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class DB_Log implements Serializable{
	
	private static final long serialVersionUID = 1L;
	LinkedList<String> log;
	
	DB_Log(){
		log = new LinkedList<String>();
	}
	
	LinkedList<String> getLog(){
		return log;
	}
	
	public void add(String command){
		Date now = new Date();
		log.add(now.toString()+" :: "+command);
	}
	public String[] toStrArray(){///Here may need to read all commands one by one
		return (String[])log.toArray();
	}
	public void clearLog(){
		log.clear();
	}
}
