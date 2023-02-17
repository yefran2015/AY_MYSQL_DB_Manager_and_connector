package mySQL;
import java.io.*;

import javax.swing.JOptionPane;

public class DB_Saver {

	ObjectOutputStream output;
	String fileName = "temp.dat";
	
	
	
	public void save(DB_Handler handlr){
		try {
			
			output = new ObjectOutputStream(new FileOutputStream(fileName+"_H"));
			output.writeObject(handlr.getCommandsHistory());
			output.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	public void save(DB_Log log){
		try {
			
			output = new ObjectOutputStream(new FileOutputStream(fileName+"_L"));
			output.writeObject(log);
			output.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	public void save(DB_Credentials credentials){
		try {
			
			output = new ObjectOutputStream(new FileOutputStream(fileName+"_C"));
			output.writeObject(credentials);
			output.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	
	public void save(DB_Handler handlr, DB_Log log){
		
	}
}
