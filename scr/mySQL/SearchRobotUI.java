package mySQL;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class SearchRobotUI extends JFrame implements ActionListener{
	private DB_Handler dbHandlr = new DB_Handler();
	ResultSet resultSet = null;
	
	
	
	
	private JPanel outputContainer = new JPanel(new GridLayout(1,0));
	private JPanel inputContainer = new JPanel(new GridLayout(3,0));
	private JPanel input = new JPanel(new GridLayout(0,1));
	private JPanel buttons = new JPanel(new GridLayout(0,3));
	private JTextArea jtaOutput = new JTextArea("");
	private JTextField jtfQuery = new JTextField();
	private JButton jbtQuery = new JButton("Execute Query");
	private JButton jbtOk = new JButton("Show All");
	private JTextField jtfLogin = new JTextField("Enter Your Login Here");
	private JTextField jtfPass = new JPasswordField();
	private JTextField jtfDomain = new JTextField();
	private JTextField jtfPort= new JTextField();
	private JTextField jtfDataBase= new JTextField();
	private JTextField jtfDBEncoding= new JTextField();
	private JTextField jtfLocalEncoding= new JTextField();
	private JButton jbtChngEncoding = new JButton("Chng Encoding");
	private JPanel loginPanel = new JPanel(new GridLayout(0,4));
	private JPanel messagesPanel = new JPanel(new GridLayout(0,1));
	private JPanel headerPanel = new JPanel(new GridLayout(2,0));
	private JTextArea jtaMessages = new JTextArea();
	private String databaseEncoding ="utf8";
	private String localOutputEncoding ="Windows-1251";
	private JButton jbtLogin = new JButton("Connect");
	private JButton jbtSetDefault = new JButton("Default");
	private String loginStr;
	private String passStr;
	private String domainStr;
	private String databaseStr;
	private String portStr;
	private String queryStr ="SELECT * FROM addresses4 WHERE id<500";
	//private DB_ErrorDisplayGUI errorGUI= new DB_ErrorDisplayGUI("Testing Error Output Screen...");
	private JComboBox<String> jcboHistory;
	
	public void setDefault(){
		loginStr="school";
		passStr="";
		domainStr ="192.168.0.102";
		databaseStr="db_search_websites_robot";
		portStr ="3306";
		jtfLogin.setText(loginStr);
		jtfPass.setText(passStr);
		jtfDomain.setText(domainStr);
		jtfPort.setText(portStr);
		jtfDataBase.setText(databaseStr);
		jtfDBEncoding.setText(databaseEncoding);
		jtfLocalEncoding.setText(localOutputEncoding);
		
		
	}
	SearchRobotUI(){
		
		
		setTitle("Search DataBase");
		setSize(700,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setLayout(new BorderLayout(0,5));	
		
		
		jtaOutput.setWrapStyleWord(true);
		jtaOutput.setLineWrap(true);
		jtaOutput.setEditable(false);
		
		JScrollPane searchScrollPane = new JScrollPane(jtaOutput);
		outputContainer.add(searchScrollPane);
		jbtOk.addActionListener(this);
		jbtQuery.addActionListener(this);
		input.add(jtfQuery);
		buttons.add(jbtOk);
		buttons.add(jbtQuery);
		inputContainer.add(input);
		//String[] tm={"jfda","thwo","hhf;als","djfa;"};
		jcboHistory = new JComboBox<String>();
		jcboHistory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				jtfQuery.setText(jcboHistory.getItemAt(jcboHistory.getSelectedIndex()));
			}
		});
		jcboHistory.setForeground(Color.RED);
		
		inputContainer.add(jcboHistory);
		inputContainer.add(buttons);
		
		/* Login's panel settings here: */
		loginPanel.add(new JLabel("Login:"), JLabel.RIGHT_ALIGNMENT);
		loginPanel.add(jtfLogin);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(jtfPass);
		loginPanel.add(new JLabel("Domain:"));
		loginPanel.add(jtfDomain);
		loginPanel.add(new JLabel("Port:"));
		loginPanel.add(jtfPort);
		loginPanel.add(new JLabel("DataBase:"));
		loginPanel.add(jtfDataBase);
		loginPanel.add(jbtLogin);
		loginPanel.add(jbtSetDefault);
		loginPanel.add(new JLabel("DB Encoding"));
		loginPanel.add(jtfDBEncoding);
		loginPanel.add(new JLabel("Local Output Encoding"));
		loginPanel.add(jtfLocalEncoding);
		loginPanel.add(jbtChngEncoding);
		jbtChngEncoding.addActionListener(this);
		jbtSetDefault.addActionListener(this);
		jbtLogin.addActionListener(this);
		/* End of coding for Login Panel */
		
		/*Message Panel Code Here*/
		jtaMessages.setEditable(false);
		JScrollPane messagesScrollPane = new JScrollPane(jtaMessages);
		messagesScrollPane.setPreferredSize(new Dimension(200,0));
		DefaultCaret caret = (DefaultCaret) jtaMessages.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		

		messagesPanel.setBorder(new LineBorder(Color.BLACK, 2));
		messagesPanel.add(messagesScrollPane);
		headerPanel.add(loginPanel);
		headerPanel.add(messagesScrollPane);
		add(headerPanel, BorderLayout.NORTH);// adding login panel to output JFrame
		
		
		add(outputContainer, BorderLayout.CENTER);
		add(inputContainer, BorderLayout.SOUTH);
		
		
		
		
		
		setDefault();
		setVisible(true);
		
		
		
	}
	
	public void showFoundRecords(String dbEncoding, String localEncoding) throws SQLException{
		
		String input = "";
		if(resultSet!=null){
			jtaOutput.setText("");
			while(resultSet.next()){
				input +=resultSet.getString(1)+"\t"+resultSet.getString(2)
					+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\n";
			}
			
			
			try {
				
				
				byte ptext[] = input.getBytes(dbEncoding);
				String value;
				value = new String(ptext, localEncoding);
			//	jtaOutput.setLocale(new Locale("ru", "ru_RU", "A"));
				jtaOutput.setText(value);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				showMessage(e.toString());
				//e.printStackTrace();
			}
			
//			while(resultSet.next()){
//				jtaOutput.setText(jtaOutput.getText()
//						+resultSet.getString(1)+"\t"+resultSet.getString(2)
//						+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)
//						+"\t"+resultSet.getString(5)+"\n");
//			}
//			Locale list[] = DateFormat.getAvailableLocales();
//	        for (Locale aLocale : list) {
//	            System.out.println(aLocale.toString());
//	        }
//			DataInputStream in;
//			try {
//				in = new DataInputStream(new FileInputStream(input));
//				String result;
//				result = in.readUTF();
//				jtaOutput.setText(result);
//				in.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        
	        
		}
	}
	public void showMessage(String message){
		Date now = new Date();
		jtaMessages.setText(now.toString()+"  "+message+"\n"+jtaMessages.getText());
	}
	
	
	public void addNewListToComboBox(Stack<String> list){//this method is too heavy(remove, read/add)
				jcboHistory.removeAllItems();
				for(int i=list.toArray().length-1;i>=0;i--)
					jcboHistory.addItem((String)list.toArray()[i]);
			
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jbtOk){
			executeQuery(queryStr);	

		}else if(e.getSource() == jbtQuery){
			executeQuery(jtfQuery.getText().trim());			
			System.out.println(dbHandlr.getStackOfCommands().toString());
			
			
		}else if((e.getSource()==jbtLogin)){
			//System.out.println(loginStr+"   "+passStr);
			loginStr = jtfLogin.getText().trim();
			passStr = jtfPass.getText().trim();
			domainStr = jtfDomain.getText().trim();
			portStr = jtfPort.getText().trim();
			databaseStr = jtfDataBase.getText().trim();
			showMessage("You set your credentials--Now you may execute queries");
		}else if((e.getSource()==jbtSetDefault)){
			this.setDefault();
			showMessage("Default Credentials are setted");
			
		}else if(e.getSource() == jbtChngEncoding ){
			databaseEncoding =this.jtfDBEncoding.getText().trim();
			localOutputEncoding = this.jtfLocalEncoding.getText().trim();
			showMessage("Encoding Changed: "+databaseEncoding +" "+localOutputEncoding);
		}
		
		
	}
	public ResultSet executeQuery(String query){
		showMessage(dbHandlr.establishConn(loginStr, passStr, domainStr , portStr, databaseStr));
		try {
			resultSet = dbHandlr.executeQuery(query);
			showMessage(query);
			showFoundRecords(databaseEncoding,localOutputEncoding);
			
			addNewListToComboBox(dbHandlr.getStackOfCommands());
			jcboHistory.setSelectedItem(query);
		} catch (SQLException | NullPointerException ex) {
			//ex.printStackTrace();
			showMessage(ex.toString());
		}
		showMessage(dbHandlr.terminateConn());
		
		return resultSet;
	}
	
	public static void main(String[] args) {
		new SearchRobotUI();
	}
	
}
