package net.hypr.core;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class OSWarning extends JDialog {
	
	public OSWarning() {
		JOptionPane.showMessageDialog(rootPane, "You have not selected an operating system..", "Error", JOptionPane.ERROR_MESSAGE);
	}

}
