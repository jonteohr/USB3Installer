package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame {
	
	private final String aboutTxt = "\nUSB3Installer was made to automatically install USB 3 drivers onto a Windows 7 installation .ISO file.";
	
	private JButton btn = new JButton("Return");
	private JLabel text = new JLabel("There will be a more in-depth about text here.");
	
	public About() {
		super("About");
		
		setSize(300, 200);
		setVisible(true);
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		setAutoRequestFocus(true);
		isAlwaysOnTop();
		
		this.setLayout(new BorderLayout());
		
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.CENTER);
		
		add(text, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("This should close this popup.");
			}
		});
	}

}
