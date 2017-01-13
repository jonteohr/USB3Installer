package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel = new TextPanel();
	private JButton btn = new JButton("Click me!");
	private Toolbar toolbar = new Toolbar();
	private FormPanel formPanel = new FormPanel();
	
	private static String windowTitle = "WIN7 USB3 Installer";

	/**
	 * Självaste egenskaperna för fönstret i sig.
	 */
	public MainFrame() {
		super(windowTitle);
		
		setLayout(new BorderLayout());
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textPanel.appendText("hest\n");
				
			}
		});
		
		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
				
			}
			
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
		setResizable(false);
		System.out.println("Launching...");
		
		textPanel.appendText("Inväntar konfigurering...");
	}
	
}
