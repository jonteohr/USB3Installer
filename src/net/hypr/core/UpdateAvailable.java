package net.hypr.core;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class UpdateAvailable extends JDialog {
	
	protected Toolbar toolBar = new Toolbar();
	
	private static String updateTxt = "There's a new update available.\nWant to update now?";
	private static String updateURL = "https://sourceforge.net/projects/usb3installer/";
	
	public UpdateAvailable() {
		
	}
	
	public void showMsg() {
		String updateButtons[] = {"Update", "No"};
		int promptResult = JOptionPane.showOptionDialog(rootPane, updateTxt, "Update Available", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, updateButtons, updateButtons[0]);
		if(promptResult == JOptionPane.YES_OPTION) {
			toolBar.openSite(updateURL);
		}
	}

}
