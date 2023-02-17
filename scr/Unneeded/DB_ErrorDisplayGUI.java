package Unneeded;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DB_ErrorDisplayGUI extends JFrame implements ActionListener{
	private JTextArea jtaOutput = new JTextArea();
	private JButton jbtClose = new JButton("Close");
	private JPanel mainContainer = new JPanel(new BorderLayout(0, 5));
	DB_ErrorDisplayGUI(String errorMessage){
		init();
		jtaOutput.setText(errorMessage);
		jbtClose.addActionListener(this);
	}
	private void init(){
		setTitle("Search DataBase");
		setSize(700,700);
		setLocationRelativeTo(null);	
		setLayout(new BorderLayout(0,5));
	//	jbtClose.setPreferredSize(new Dimension(100,100));
		JScrollPane searchScrollPane = new JScrollPane(jtaOutput);
		mainContainer.add(searchScrollPane, BorderLayout.CENTER);
		mainContainer.add(jbtClose, BorderLayout.SOUTH);
		add(mainContainer);
		setVisible(true);
	}
	public void setNewMessage(String message){
		this.jtaOutput.setText(message);
	}
	@Override
	public void actionPerformed(ActionEvent evnt) {
		if((evnt.getSource()==jbtClose)){
			this.setVisible(false);
		}
		
	}	
}
