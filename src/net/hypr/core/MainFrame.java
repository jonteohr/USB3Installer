package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel = new TextPanel();
	private Toolbar toolbar = new Toolbar();
	private FormPanel formPanel = new FormPanel();
	
	private static String windowTitle = "WIN7 USB3 Installer";

	/**
	 * The properties for the window itself
	 */
	public MainFrame() {
		super(windowTitle);
		
		setLayout(new BorderLayout());
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		//add(toolbar, BorderLayout.NORTH);
		setJMenuBar(toolbar.getMenu());
		
		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
				
			}
			
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				String workspace = e.getWorkspace();
				String drivers = e.getDrivers();
				
				textPanel.appendText("Working in workspace at: " + workspace + "\n");
				textPanel.appendText("Chose drivers file: " + drivers + "\n");
				
				System.out.println("GO clicked!");
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		
		textPanel.appendText("Waiting for configuration\n");
	}
	
}
