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
		textArea.setEditable(false);
		textArea.setMargin(new Insets(2, 5, 2, 5));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
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
	 * Remove all text inside the panel.
	 */
	public void clearText() {
		textArea.setText(null);
	}

}