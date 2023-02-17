package mySQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class DB_Controller implements ActionListener{
	private DB_Handler db_handlr;
	private DB_GUI gui;
	private DB_Log log;
	private DB_Encoder encoder;
	private DB_Saver saver;
	private DB_Loader loader;
	
	public void start(){
		encoder = new DB_Encoder();
		log = new DB_Log();
		db_handlr = new DB_Handler();
		saver = new DB_Saver();
		loader = new DB_Loader();
//		db_handlr.setDefaultCredentials();	
//		gui = new DB_GUI(this, DB_Encoder.encodingsArray, db_handlr.getUser(), db_handlr.getHost(), db_handlr.getDataBase(), db_handlr.getHostPort(), DB_Handler.defaultQuery);

		db_handlr.setDefaultCredentials();	
		gui = new DB_GUI(this, DB_Encoder.encodingsArray, db_handlr.getUser(), db_handlr.getHost(),
				db_handlr.getDataBase(), db_handlr.getHostPort(),
				DB_Handler.defaultQuery);
		
	}
	
	
	
	public ResultSet query(String query) throws SQLException{
		return db_handlr.executeQuery(query);
	}
	
	public void changeDBEncoding(String inEncoding){
		encoder.setIn(inEncoding);
	}
	public void changeLocalEncoding(String outEncoding){
		encoder.setOut(outEncoding);
	}
	public String getDBEncoding(){
		return encoder.getIn();
	}
	public String getLocalEncoding(){
		return encoder.getOut();
	}
	
	public  static void main(String[] args){
		DB_Controller db_cntroller = new DB_Controller();
		db_cntroller.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String message ="";
		if(e.getActionCommand()=="Execute Query"){
			
			
			
			String queryStr =gui.getJtfQuery().getText().trim();
			
			
			if(!queryStr.isEmpty()){
				if(!db_handlr.isConnEstablished()){
					message = db_handlr.establishConn();
					log.add(message);//start connection
					gui.showMessage(message);//start connection
				}
				gui.showMessage(queryStr);
				log.add(queryStr);
				if(db_handlr.isConnEstablished()){
					
					try {
						ResultSet rs= query(queryStr);
						message = db_handlr.resultSetToString(rs, encoder);
//						rs.beforeFirst();
//						while(rs.next()){
//							System.out.println(rs.);
//						}
						
						
						
						db_handlr.getResultSet().last();//This needs to be for showing count of found items 
						gui.setJtaOutput(message+"\nTotal Founded: "+db_handlr.getResultSet().getRow());//showing found items
						
					} catch (SQLException | UnsupportedEncodingException e1) {
						message =e1.toString();
						log.add(message);
						gui.showMessage(message);
					}
				}else
					gui.showMessage("Connection not Established");
				
			}else
				gui.showMessage("Query String is Empty");
			
			
			gui.addToHistory(db_handlr.getCommandsHistory());
			
			if(db_handlr.isConnEstablished()){
				message = db_handlr.terminateConn();
				log.add(message);
				gui.showMessage(message);
			}
		
		
			
		}else if(e.getActionCommand()=="Show All"){
			
			if(!db_handlr.isConnEstablished()){
				message = db_handlr.establishConn();
				log.add(message);//start connection
				gui.showMessage(message);//start connection
			}
			
			if(db_handlr.isConnEstablished()){
				
				try {
					message = db_handlr.resultSetToString(query(DB_Handler.defaultQuery), encoder);
					gui.showMessage(DB_Handler.defaultQuery);
					log.add(DB_Handler.defaultQuery);
					gui.setJtaOutput(message);
					
				} catch (SQLException | UnsupportedEncodingException e1) {
					message =e1.toString();
					log.add(message);
					gui.showMessage(message);
				}
			}else
				gui.showMessage("Connection not Established");
			
			
		}else if((e.getActionCommand()=="Chng Encoding")){
			
			encoder.setIn(gui.getJtfDBEncoding().getText().trim());
			encoder.setOut(gui.getJtfLocalEncoding().getText().trim());
			gui.showMessage("Encoding Changed: in "+encoder.getIn() +" out "+encoder.getOut());
			log.add("Encoding Changed: in "+encoder.getIn() +" out "+encoder.getOut());
			
			
		}else if((e.getActionCommand()=="Set Credentials")){
			
			String answr =db_handlr.establishConn(gui.getJtfLogin().getText().trim(), gui.getJtfPass().getText().trim(), 
					gui.getJtfDomain().getText().trim(), gui.getJtfPort().getText().trim()
					, gui.getJtfDataBase().getText().trim());//start connection
			gui.showMessage(answr);
			log.add(answr);
			if(answr.compareToIgnoreCase("Database connection established")==0){
				gui.showMessage("New Credentials are setted. Now may execute queries.");
				log.add("New Credentials are setted. Now may execute queries.");
			}
			String termStr = db_handlr.terminateConn();//terminate connection
			log.add(termStr);
			gui.showMessage(termStr);
			
		}else if(e.getActionCommand()=="Default" ){
			db_handlr.setDefaultCredentials();
			gui.setJtfLogin(db_handlr.getUser());
			gui.setJtfDomain(db_handlr.getHost());
			gui.setJtfPort(db_handlr.getHostPort());
			gui.setJtfDataBase(db_handlr.getDataBase());
			gui.setJtfPass(db_handlr.getPass());
		}else if(e.getActionCommand()=="Save"){//Save menu item
			saver.save(db_handlr);
			saver.save(log);
			db_handlr.getCredentials().setPass("");
			saver.save(db_handlr.getCredentials());
			
		}else if(e.getActionCommand()=="Load"){
			log=loader.loadLog("temp.dat_L");
			db_handlr.setCommandsHistory(loader.loadCommandHistory("temp.dat_H"));
			db_handlr.setCredentials(loader.loadCredentials("temp.dat_C"));
			gui.repaint();
		}else if(e.getActionCommand()=="Options"){
			Iterator<String> ltr = log.getLog().iterator();
			while(ltr.hasNext()){
				System.out.println(ltr.next());
			}
		}
			
	}
}
