package net.hypr.core;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel = new TextPanel();
	private Toolbar toolbar = new Toolbar();
	private FormPanel formPanel = new FormPanel();
	private FooterBar footerPanel = new FooterBar();
	
	private static String windowTitle = "WIN7 USB3 Installer";

	/**
	 * The properties for the window itself
	 */
	public MainFrame() {
		super(windowTitle);
		
		setLayout(new BorderLayout());
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
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
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		try {
			setIconImage(ImageIO.read(new File("resources/icon.png")));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		setVisible(true);
		
		
		textPanel.appendText("Waiting for configuration\n");
		textPanel.appendText("Need help? Click the help button in the upper menu!\n");

	}
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainFrame.class.getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}
