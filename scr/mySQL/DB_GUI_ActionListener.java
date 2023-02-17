package mySQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DB_GUI_ActionListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Show All"){
//			executeQuery(queryStr);	

		}else if(e.getSource() == "Execute Query"){
//			executeQuery(jtfQuery.getText().trim());			
//			System.out.println(dbHandlr.getStackOfCommands().toString());
			
			
		}else if((e.getSource()=="Connect")){
			//System.out.println(loginStr+"   "+passStr);
//			loginStr = jtfLogin.getText().trim();
//			passStr = jtfPass.getText().trim();
//			domainStr = jtfDomain.getText().trim();
//			portStr = jtfPort.getText().trim();
//			databaseStr = jtfDataBase.getText().trim();
//			showMessage("You set your credentials--Now you may execute queries");
		}else if((e.getSource()=="Default")){
//			this.setDefault();
//			showMessage("Default Credentials are setted");
			
		}else if(e.getSource() == "Chng Encoding"){
//			databaseEncoding =this.jtfDBEncoding.getText().trim();
//			localOutputEncoding = this.jtfLocalEncoding.getText().trim();
//			showMessage("Encoding Changed: "+databaseEncoding +" "+localOutputEncoding);
		}
		
	}

}
