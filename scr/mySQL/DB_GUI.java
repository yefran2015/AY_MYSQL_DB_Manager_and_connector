package mySQL;


import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class DB_GUI extends JFrame implements ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JButton jbtCredentials = new JButton("Set Credentials");
	private JButton jbtSetDefault = new JButton("Default");

	private JComboBox<String> jcboHistory;
	private JComboBox<String> jcboDataBaseEncoding;
	private JComboBox<String> jcboLocalEncoding;
	
	private MenuBar menuBar;
	
	
	private DB_Controller controler;
	private DB_Controller getController(){
		return controler;
	}
	
	public void setDefault(String loginStr, String domainStr, String portStr, String databaseStr){
		jtfLogin.setText(loginStr);
		jtfPass.setText("");
		jtfDomain.setText(domainStr);
		jtfPort.setText(portStr);
		jtfDataBase.setText(databaseStr);
		
	}
	
	DB_GUI(DB_Controller controler, String[] encodings, String loginStr, String domain, String database, String port, String defaultQuery, String passwd){
		this(controler, encodings ,loginStr, domain, database, port, defaultQuery);
		this.jtfPass.setText(passwd);
	}
	DB_GUI(DB_Controller controler, String[] encodings , String loginStr, String domain, String database, String port, String defaultQuery){
		this(controler, encodings ,loginStr, domain, database, port);
		this.jtfQuery.setText(defaultQuery);
	}
	
	DB_GUI(DB_Controller controler, String[] encodings , String loginStr, String domain, String database, String port){
		this(controler, encodings);
		jtfLogin.setText(loginStr);
		jtfPass.setText("");
		jtfDomain.setText(domain);
		jtfPort.setText(port);
		jtfDataBase.setText(database);
	}

	DB_GUI(DB_Controller controler, String[] encodings){
		this.controler = controler;
		
		setTitle("Search DataBase");
		setSize(700,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setLayout(new BorderLayout(0,5));
		menuBar = new MenuBar("This is Data Base Handler\nauthor: andrus\nver 0.1.0", this.controler);
		setJMenuBar(menuBar);
		
		jtaOutput.setWrapStyleWord(true);
		jtaOutput.setLineWrap(true);
		jtaOutput.setEditable(false);
		
		jbtCredentials.setForeground(Color.RED);
		// Sets the button to the specified Color.
	//	jbtCredentials.setBackground(Color.LIGHT_GRAY);
		// Sets the button text to the inverse of the button Color.
	//	jbtCredentials.setForeground(new Color(jbtCredentials.getBackground().getRGB() ^ Integer.MAX_VALUE));

		
		JScrollPane searchScrollPane = new JScrollPane(jtaOutput);
		outputContainer.add(searchScrollPane);
		jbtOk.addActionListener(controler);
		jbtQuery.addActionListener(controler);
		jbtQuery.setForeground(Color.BLUE);
		input.add(jtfQuery);
		buttons.add(jbtOk);
		buttons.add(jbtQuery);
		inputContainer.add(input);
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
		loginPanel.add(jbtCredentials);
		loginPanel.add(jbtSetDefault);
		loginPanel.add(new JLabel("DB Encoding"));
		jcboDataBaseEncoding = new JComboBox<String>(encodings);
		jcboDataBaseEncoding.setSelectedItem(controler.getDBEncoding());
		jcboDataBaseEncoding.addItemListener(this);
		
		loginPanel.add(jcboDataBaseEncoding);
		loginPanel.add(new JLabel("Local Output Encoding"));
		jcboLocalEncoding = new JComboBox<String>(encodings);
		jcboLocalEncoding.setSelectedItem(controler.getLocalEncoding());
		jcboLocalEncoding.addItemListener(this);
		loginPanel.add(jcboLocalEncoding);
		loginPanel.add(jbtChngEncoding);
		jtfDBEncoding.setText(controler.getDBEncoding());
		loginPanel.add(jtfDBEncoding);
		loginPanel.add(new JLabel());
		jtfLocalEncoding.setText(controler.getLocalEncoding());
		loginPanel.add(jtfLocalEncoding);
		jbtChngEncoding.addActionListener(controler);
		jbtSetDefault.addActionListener(controler);
		jbtCredentials.addActionListener(controler);
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
		
		setVisible(true);	
	}
	
	
	
	public void setJtaOutput(String jtaOutput) {
		this.jtaOutput.setText(jtaOutput);
	}

	public JTextField getJtfLogin() {
		return jtfLogin;
	}

	public JTextField getJtfPass() {
		return jtfPass;
	}

	public JTextField getJtfDomain() {
		return jtfDomain;
	}

	public JTextField getJtfPort() {
		return jtfPort;
	}

	public JTextField getJtfDataBase() {
		return jtfDataBase;
	}

	public JTextField getJtfDBEncoding() {
		return jtfDBEncoding;
	}

	public JTextField getJtfLocalEncoding() {
		return jtfLocalEncoding;
	}

	public JTextField getJtfQuery() {
		return jtfQuery;
	}

	public void setJtfLogin(String login) {
		this.jtfLogin.setText(login);
	}

	public void setJtfPass(String pass) {
		this.jtfPass.setText(pass);
	}

	public void setJtfDomain(String domain) {
		this.jtfDomain.setText(domain);
	}

	public void setJtfPort(String port) {
		this.jtfPort.setText(port);
	}

	public void setJtfDataBase(String dataBase) {
		this.jtfDataBase.setText(dataBase);
	}

	public void setJtfDBEncoding(JTextField jtfDBEncoding) {
		this.jtfDBEncoding = jtfDBEncoding;
	}

	public void setJtfLocalEncoding(JTextField jtfLocalEncoding) {
		this.jtfLocalEncoding = jtfLocalEncoding;
	}

	public void showMessage(String message){
		Date now = new Date();
		jtaMessages.setText(now.toString()+" "+message+"\n"+jtaMessages.getText());
	}
	
	
	public void addNewListToComboBox(Stack<String> list, JComboBox<String> cmbo){//this method is too heavy(remove, read/add)
		cmbo.removeAllItems();
		for(int i=list.toArray().length-1;i>=0;i--)
			cmbo.addItem((String)list.toArray()[i]);		
	}
	public void addToHistory(Stack<String> stack){
		jcboHistory.removeAllItems();
		for(int i=stack.toArray().length-1;i>=0;i--){
			jcboHistory.addItem((String) stack.toArray()[i]);
		}
	
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==jcboDataBaseEncoding){
			getController().changeDBEncoding(jcboDataBaseEncoding.getItemAt(jcboDataBaseEncoding.getSelectedIndex()));
			jtfDBEncoding.setText(jcboDataBaseEncoding.getItemAt(jcboDataBaseEncoding.getSelectedIndex()));

			
			}
		if(e.getSource()==jcboLocalEncoding){
			getController().changeLocalEncoding(jcboLocalEncoding.getItemAt(jcboLocalEncoding.getSelectedIndex()));
			jtfLocalEncoding.setText(jcboLocalEncoding.getItemAt(jcboLocalEncoding.getSelectedIndex()));
			}
	}
}



//private DB_Handler dbHandlr = new DB_Handler();
//ResultSet resultSet = null;



//public void showFoundRecords(String dbEncoding, String localEncoding) throws SQLException{
//	
//	String input = "";
//	if(resultSet!=null){
//		jtaOutput.setText("");
//		while(resultSet.next()){
//			input +=resultSet.getString(1)+"\t"+resultSet.getString(2)
//				+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\n";
//		}
//		
//		
//		try {
//			
//			
//			byte ptext[] = input.getBytes(dbEncoding);
//			String value;
//			value = new String(ptext, localEncoding);
//		//	jtaOutput.setLocale(new Locale("ru", "ru_RU", "A"));
//			jtaOutput.setText(value);
//			
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			showMessage(e.toString());
//			//e.printStackTrace();
//		}
//	
//        	        
//	}
//}



//executeQuery(jtfQuery.getText().trim());			
//System.out.println(dbHandlr.getStackOfCommands().toString());


//this.setDefault();
//showMessage("Default Credentials are setted");



//System.out.println(loginStr+"   "+passStr);
//loginStr = jtfLogin.getText().trim();
//passStr = jtfPass.getText().trim();
//domainStr = jtfDomain.getText().trim();
//portStr = jtfPort.getText().trim();
//databaseStr = jtfDataBase.getText().trim();
//showMessage("You set your credentials--Now you may execute queries");





//public ResultSet executeQuery(String loginStr, String passStr, String domainStr, String portStr, String databaseStr, String query){
//showMessage(dbHandlr.establishConn(loginStr, passStr, domainStr , portStr, databaseStr));
//try {
//	resultSet = dbHandlr.executeQuery(query);
//	showMessage(query);
//	showFoundRecords(databaseEncoding,localOutputEncoding);
//	
//	addNewListToComboBox(dbHandlr.getStackOfCommands());
//	jcboHistory.setSelectedItem(query);
//} catch (SQLException | NullPointerException ex) {
//	//ex.printStackTrace();
//	showMessage(ex.toString());
//}
//showMessage(dbHandlr.terminateConn());
//
//return resultSet;
//}

//public static void main(String[] args) {
//new DB_GUI();
//}
