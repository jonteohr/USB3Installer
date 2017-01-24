package net.hypr.core;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class FooterBar extends JPanel {
	
	private JLabel label;
	public JProgressBar progress;
	public int currentProgress;
	
	public FooterBar() {
		
		// Footer test
		setPreferredSize(new Dimension(640, 16));
		setLayout(new GridLayout());
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		JLabel label;
				
		label = new JLabel("Ready", SwingConstants.LEFT);
		add(label);
		
		progress = new JProgressBar();
		progress.setMaximum(6);
		progress.setStringPainted(true);
		progress.setPreferredSize(new Dimension(10, 16));
		progress.setValue(currentProgress);
		add(progress);
		
	}

}
