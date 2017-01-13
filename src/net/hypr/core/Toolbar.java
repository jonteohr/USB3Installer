package net.hypr.core;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	
	private JButton arkiv = new JButton("Arkiv");
	private JButton Bye = new JButton("Test");
	
	private StringListener textListener;
	
	Font smallSystemFont = new Font("Lucide Grande", Font.PLAIN, 11);
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(arkiv);
		add(Bye);
		
		arkiv.addActionListener(this);
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
				textListener.textEmitted("Hajjdu\n");
			}
			
		} else if(clicked == arkiv) {
			
			if(textListener != null) {
				textListener.textEmitted("Arkiv klickad!\n");
			}
			
		}
		
	}
	
}
