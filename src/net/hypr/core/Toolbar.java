package net.hypr.core;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	
	private JButton file = new JButton("File");
	private JButton Bye = new JButton("Test");
	
	private StringListener textListener;
	
	Font smallSystemFont = new Font("Lucide Grande", Font.PLAIN, 11);
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(file);
		add(Bye);
		
		file.addActionListener(this);
		Bye.addActionListener(this);
		
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton) e.getSource();
		
		if(clicked == Bye) {
			
			if(textListener != null) {
				textListener.textEmitted("Bye\n");
			}
			
		} else if(clicked == file) {
			
			if(textListener != null) {
				textListener.textEmitted("File clicked!\n");
			}
			
		}
		
	}
	
}
