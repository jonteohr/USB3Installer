package net.hypr.core;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class FooterBar extends JPanel {
	
	private JLabel label;
	protected JProgressBar progress;
	
	
	public FooterBar() {
		
		setPreferredSize(new Dimension(640, 16));
		setLayout(new GridLayout());
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		Border margin = new EmptyBorder(0, 5, 0, 5);
		
		label = new JLabel("Ready", SwingConstants.LEFT);
		label.setBorder(margin);
		add(label);
		
		progress = new JProgressBar();
		progress.setMaximum(6);
		progress.setStringPainted(false);
		progress.setPreferredSize(new Dimension(10, 16));
		progress.setValue(0);
		add(progress);
		
	}
	
	/**
	 * Sets the text for the status-label.
	 */
	public void setStatus(String arg) {
		label.setText(arg);
	}

}
