package net.hypr.core;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WorkspaceWarning extends JDialog {
	
	public WorkspaceWarning() {
		JOptionPane.showMessageDialog(rootPane, "You have not selected a workspace..", "Error", JOptionPane.ERROR_MESSAGE);
	}

}
