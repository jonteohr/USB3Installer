package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	
	protected JTextArea textArea = new JTextArea();
	
	public TextPanel() {
		
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);

		textArea.setEditable(false);
		textArea.setMargin(new Insets(2, 5, 2, 5));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
	}
	
	/**
	 * Add text to the big text panel to the right.
	 * @param msg
	 * @param c
	 */
	public void appendText(String msg) {
		textArea.append(msg + "\n");
	}
	
	/**
	 * Same as append text, but with no \n suffix
	 */
	public void sendLog(String msg) {
		textArea.append(msg);
	}
	
	/**
	 * Remove all text inside the panel.
	 */
	public void clearText() {
		textArea.setText(null);
	}

}