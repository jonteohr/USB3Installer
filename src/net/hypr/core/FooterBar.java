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
	private JProgressBar progress;
	
	
	public FooterBar() {
		
		setPreferredSize(new Dimension(640, 16));
		setLayout(new GridLayout());
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		Border margin = new EmptyBorder(0, 5, 0, 5);
		
		label = new JLabel("v 0.4", SwingConstants.LEFT);
		label.setBorder(margin);
		add(label);
		
		progress = new JProgressBar();
		progress.setMaximum(6);
		progress.setStringPainted(true);
		progress.setPreferredSize(new Dimension(10, 16));
		progress.setValue(0);
		add(progress);
		
	}
	
	/**
	 * Sets the progress of the progressbar in the lower right corner. It ranges from 0-6.
	 * @param i
	 */
	public void setProgress(int i) {
		progress.setValue(i);
	}

}
