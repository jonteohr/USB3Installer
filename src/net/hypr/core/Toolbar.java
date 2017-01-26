package net.hypr.core;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener, ItemListener {
	
	private JMenuBar menuBar;
	private JMenu menu, submenu;
	protected JMenuItem menuItem;
	
	public Toolbar() {
		
		// Creates the menu bar
		menuBar = new JMenuBar();
		
		// First menu. (File)
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("File.");
		menuBar.add(menu);
		
		// Add stuff to the file-dropdown
		menuItem = new JMenuItem("Choose Workspace", KeyEvent.VK_W);
		menuItem.getAccessibleContext().setAccessibleDescription("Choose the workspace.");
		menuItem.addActionListener(new FormPanel());
		menuItem.setEnabled(true);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_Q);
		menuItem.getAccessibleContext().setAccessibleDescription("Exit the program.");
		menuItem.addActionListener(this);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("Quitting...");
			}
		});
		menu.add(menuItem);
		
		// Second menu (Help)
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("Info about the program.");
		menuBar.add(menu);
		
		// Adds a about button. This will open a small window with some information!
		menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.getAccessibleContext().setAccessibleDescription("Information regarding the software.");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		// Addsa a "how do I...?" button to the toolbar.
		menuItem = new JMenuItem("How do I use this?", KeyEvent.VK_O);
		menuItem.getAccessibleContext().setAccessibleDescription("Opens the default browser and goes to the github wiki.");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		// Adds a option to go to the github project
		menuItem = new JMenuItem("Github", KeyEvent.VK_G);
		menuItem.getAccessibleContext().setAccessibleDescription("Links to the Github project.");
		menuItem.addActionListener(this);
		menu.add(menuItem);
						
	}
	
	public void setStringListener(StringListener listener) {
		//this.textListener = listener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem btn = (JMenuItem) e.getSource();
		
		if(btn.getText() == "Github") {
			openSite("https://condolent.github.io/USB3Installer/");
		}
		
		if(btn.getText() == "Exit") {
			System.exit(ABORT);
		}
		
		if(btn.getText() == "About") {
			System.out.println("About clicked.\nCalled popup.");
			openAbout();
		}
		
		if(btn.getText() == "How do I use this?") {
			openSite("https://github.com/condolent/USB3Installer/wiki");
		}
		
		/*if(btn.getText() == "Choose Workspace") {
			System.out.println("Choose Workspace clicked.");
			System.err.println("This function isn't finished yet.");
		}*/
		
	}
	
	/**
	 * Opens the actual "about" popup-window.
	 */
	public void openAbout() {
		new About();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		// TODO
		
	}
	
	/**
	 * Returns the menu-object with all its content.
	 * @return
	 */
	public JMenuBar getMenu() {
		return menuBar;
	}
	
	/**
	 * Opens up the default browser and goes to the specified URL.
	 * @param url
	 */
	public void openSite(String url) {
		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else {
			System.err.println("Couldn't open the browser since Desktop is not supported.");
		}
	}
	
	
	
	/*private JButton file = new JButton("File", createImageIcon("images/archive.png"));
	private JButton help = new JButton("Help");
	
	private StringListener textListener;
	
	Font smallSystemFont = new Font("Lucide Grande", Font.PLAIN, 11);
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		file.setFont(smallSystemFont);
		help.setFont(smallSystemFont);
		
		add(file);
		add(help);
		
		file.addActionListener(this);
		help.addActionListener(this);
		
		file.setToolTipText("Archive");
		help.setToolTipText("Help");
		
	}*/
	
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Toolbar.class.getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}
