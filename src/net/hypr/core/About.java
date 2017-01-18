package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class About extends JFrame {
	
	private final String aboutTxt = "\nUSB3Installer was made to automatically install USB 3 drivers onto a Windows 7 installation .ISO file.";
	
	private JButton btn = new JButton("Return");
	private JTextPane textArea = new JTextPane();
	SimpleAttributeSet attribs = new SimpleAttributeSet();
	
	public About() {
		super("About");
		
		// Windows preferences
		setSize(300, 150);
		setVisible(true);
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		setAutoRequestFocus(true);
		isAlwaysOnTop();
		
		this.setLayout(new BorderLayout());
		
		// Special style settings for the text itself
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(attribs, 16);
		StyleConstants.setBold(attribs, true);
		StyleConstants.setFontFamily(attribs, "Arial");
		
		// Component preferences
		textArea.setParagraphAttributes(attribs, true);
		textArea.setEditable(false);
		textArea.setText(aboutTxt);
		textArea.setBackground(null);
		
		// Add the components to the window
		add(textArea, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		// What happens when the user clicks 'Return'?
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("Closed popup.");
				dispose();
			}
		});
	}

}
