package net.hypr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class cmdExec extends SwingWorker<Integer, String> {
	
	public boolean running = false;
	
	protected String[] cmdBoot = {
		"dism /mount-wim /wimfile:boot.wim /index:2 /mountdir:mount",
		"dism /image:mount /add-driver:\"driver\" /recurse",
		"dism /unmount-wim /mountdir:mount /commit"
	};
	protected String[] cmdInstall = {
		"",
		"dism /image:mount /add-driver:\"driver\" /recurse",
		"dism /unmount-wim /mountdir:mount /commit"
	};
	
	private int status;
	private FooterBar footerBar;
	private TextPanel textPanel;
	private FormPanel formPanel;
	
	public cmdExec(FormPanel formPanel, FooterBar footerBar, TextPanel textPanel) {
		this.formPanel = formPanel;
		this.footerBar = footerBar;
		this.textPanel = textPanel;
	}
	
	@Override
	protected Integer doInBackground() {
		
		try {
			// boot.wim
			ProcessBuilder bootBuilder = new ProcessBuilder(
				"cmd.exe", "/c", cmdBoot[0] + " && " + cmdBoot[1] + " && " + cmdBoot[2]
			);
			
			bootBuilder.directory(new File(formPanel.workspaceDir.toString()));
			bootBuilder.redirectErrorStream(true);
			Process p = bootBuilder.start();
			
			String s;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while(((s = stdout.readLine()) != null && !isCancelled())) {
				publish(s);
				System.err.println(s);
			}
			if(!isCancelled()) {
				status = p.waitFor();
			}
			p.getInputStream().close();
			p.getOutputStream().close();
			p.getErrorStream().close();
			p.destroy();
			
			// install.wim
			ProcessBuilder installBuilder = new ProcessBuilder(
			"cmd.exe", "/c", cmdInstall[0] + " && " + cmdInstall[1] + " && " + cmdInstall[2]
			);
			installBuilder.directory(new File(formPanel.workspaceDir.toString()));
			installBuilder.redirectErrorStream(true);
			Process p2 = installBuilder.start();
			BufferedReader stdout2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			String s2;
			while(((s2 = stdout2.readLine()) != null && !isCancelled())) {
				publish(s2);
				System.err.println(s2);
			}
			if(!isCancelled()) {
				status = p2.waitFor();
			}
			p2.getInputStream().close();
			p2.getOutputStream().close();
			p2.getErrorStream().close();
			p2.destroy();
		} catch(IOException | InterruptedException ex) {
			ex.printStackTrace(System.err);
		}
		
		return status;
	}

	@Override
	protected void process(List<String> messages) {
		running = true;
		formPanel.okBtn.setText((this.getState()).toString());
		for(String message : messages) {
			textPanel.sendLog(message + "\n");
		}
	}
	
	@Override
	protected void done() {
		textPanel.appendText((this.getState()).toString() + "!");
		textPanel.appendText("Make sure you check the log for errors.");
		formPanel.okBtn.setEnabled(true);
		formPanel.okBtn.setText("Go!");
		footerBar.progress.setIndeterminate(false);
		formPanel.disableList(false);
		formPanel.disableWorkspace(false);
		footerBar.setStatus("Ready");
		running = false;
		
		// Create popup telling you it finished
		JOptionPane.showMessageDialog(null, "The installation finished.\nCheck the log for errors!", "Done!", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
