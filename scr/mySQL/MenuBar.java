package mySQL;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;


public class MenuBar extends JMenuBar implements Serializable{

	private static final long serialVersionUID = 1L;
	private String about = "About Messge Not Setup";
	private DB_Controller controler;
	
	
	MenuBar(final String about, DB_Controller controler){//about string
		this(controler);
		this.about=about;
	}
	
	MenuBar(DB_Controller controler){//about string
		this.controler = controler;
		final String logo="logo\\pictureGalleryIcon.jpg";
	    javax.swing.JMenu menuFile;
	    javax.swing.JMenuItem menuFileEnd;
	    javax.swing.JMenuItem menuFilePrint;
	    javax.swing.JMenuItem menuFileSaveAs;
	    javax.swing.JMenuItem menuFileReload;
	    javax.swing.JMenuItem menuFileExit;
	    javax.swing.JMenuItem menuFileNew;
	    javax.swing.JMenuItem menuFileSave;
	    javax.swing.JMenuItem menuFileLoad;
	    javax.swing.JMenuItem menuFileOpen;
	    javax.swing.JMenu menuHelp;
	    javax.swing.JMenuItem menuHelpAbout;
	    javax.swing.JMenuItem menuHelpFAQ;
	    javax.swing.JMenu menuSettings;
	    javax.swing.JMenuItem menuSettingsOption;
	    javax.swing.JMenuItem menuSettingsVideo;
	    javax.swing.JMenuItem menuSettingsAudio;
	    javax.swing.JPopupMenu.Separator jSeparator1;
		

        menuFile = new javax.swing.JMenu();
        menuFileNew = new javax.swing.JMenuItem();
        menuFileOpen = new javax.swing.JMenuItem();
        menuFileSave = new javax.swing.JMenuItem();
        menuFileLoad = new javax.swing.JMenuItem();
        menuFileEnd = new javax.swing.JMenuItem();
        menuFilePrint = new javax.swing.JMenuItem();
        menuFileSaveAs = new javax.swing.JMenuItem();
        menuFileReload = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuFileExit = new javax.swing.JMenuItem();
        menuSettings = new javax.swing.JMenu();
        menuSettingsOption = new javax.swing.JMenuItem();
        menuSettingsVideo = new javax.swing.JMenuItem();
        menuSettingsAudio = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuHelpAbout = new javax.swing.JMenuItem();
        menuHelpFAQ = new javax.swing.JMenuItem();
        menuFile.setText("File");
        menuFile.setMnemonic('F');

        menuFileNew.setText("New");
        menuFileNew.setMnemonic('N');
        menuFileNew.addActionListener(controler);
        menuFile.add(menuFileNew);

        menuFileOpen.setText("Open");
        menuFileOpen.setMnemonic('O');
        menuFile.add(menuFileOpen);

        menuFileSave.setText("Save");
        menuFileSave.setMnemonic('a');
        menuFileSave.addActionListener (controler);
        menuFile.add(menuFileSave);
        
        
        menuFileLoad.setText("Load");
        menuFileLoad.setMnemonic('L');
        menuFileLoad.addActionListener(controler);
        menuFile.add(menuFileLoad);
        
        
        menuFilePrint.setText("Print");
        menuFilePrint.setMnemonic('P');
        menuFile.add(menuFilePrint);
        
        
        menuFileSaveAs.setText("Save As...");
        menuFileSaveAs.setMnemonic('V');
        menuFile.add(menuFileSaveAs);
        
        
        menuFileReload.setText("Reload");
        menuFileReload.setMnemonic('R');
        menuFile.add(menuFileReload);
        
        menuFileEnd.setText("End");
        menuFileEnd.setMnemonic('E');
        menuFile.add(menuFileEnd);
        menuFile.add(jSeparator1);

        menuFileExit.setText("Exit");
        menuFileExit.setMnemonic('x');
        menuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	System.exit(0);
            }
        });
        menuFile.add(menuFileExit);

        add(menuFile);

        menuSettings.setText("Settings");
        menuSettings.setMnemonic('S');
        

        menuSettingsOption.setText("Options");
        menuSettingsOption.setMnemonic('O');
        menuSettingsOption.addActionListener(this.controler);
        menuSettings.add(menuSettingsOption);
        
        menuSettingsVideo.setText("Video");
        menuSettingsVideo.setMnemonic('V');
        menuSettings.add(menuSettingsVideo);
      
        
        menuSettingsAudio.setText("Audio");
        menuSettingsAudio.setMnemonic('A');
        menuSettings.add(menuSettingsAudio);
        
        
        add(menuSettings);

        menuHelp.setText("Help");
        menuHelp.setMnemonic('H');
        menuHelpFAQ.setText("FAQ");
        menuHelpFAQ.setMnemonic('Q');
        menuHelpFAQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        menuHelp.add(menuHelpFAQ);
        
        menuHelpAbout.setText("About");
        menuHelpAbout.setMnemonic('A');
        menuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String stringMessage=about;
            	javax.swing.JOptionPane.showMessageDialog(null,stringMessage,"About",javax.swing.JOptionPane.INFORMATION_MESSAGE,new ImageIcon(logo));
            }
        });
        menuHelp.add(menuHelpAbout);
        
        add(menuHelp);

        
        
	}
	public  String toString(){
    	return "Menu Bar";
    }
}
