package mySQL;

import java.io.*;
import java.util.Stack;

public class DB_Loader {
	ObjectInputStream input;
	DB_Log log;
	Stack<String> commands;
	DB_Credentials credentials;
	
	public DB_Log loadLog(String file){
		log=null;
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			log = (DB_Log)input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return log;
	}
	@SuppressWarnings("unchecked")
	public Stack<String> loadCommandHistory(String file){
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			commands = (Stack<String>)input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return commands;
	}
	public DB_Credentials loadCredentials(String file){
		credentials=null;
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			credentials = (DB_Credentials)input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return credentials;
	}
}
