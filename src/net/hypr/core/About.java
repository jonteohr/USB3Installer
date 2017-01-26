package net.hypr.core;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class About extends JDialog {
	
	private final String aboutTxt = "USB3Installer was made to automatically install\nUSB 3 drivers onto a Windows 7 installation .ISO file.";
	
	public About() {
		JOptionPane.showMessageDialog(rootPane, aboutTxt, "About", JOptionPane.INFORMATION_MESSAGE);
	}

}
