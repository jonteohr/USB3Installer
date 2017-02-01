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
		"dism /unmount-wim /mountdir:mount /commit",
		""
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
		
		// boot.wim
		mountBoot();
		footerBar.progress.setIndeterminate(false);
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		installDriver();
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		unMount();
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		
		// install.wim
		mountInstall();
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		installDriver();
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		unMount();
		footerBar.progress.setValue((footerBar.progress.getValue()) + 1);
		
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
		formPanel.disableList(false);
		formPanel.disableWorkspace(false);
		footerBar.setStatus("Ready");
		running = false;
		
		// Create popup telling you it finished
		JOptionPane.showMessageDialog(null, "The installation finished.\nCheck the log for errors!", "Done!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Mounts the boot.wim file to the mount-directory
	 */
	private void mountBoot() {
		try {
			
			ProcessBuilder bootBuilder = new ProcessBuilder(
				"cmd.exe", "/c", cmdBoot[0]
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
		} catch(IOException | InterruptedException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	/**
	 * Installs the drivers to the mounted file
	 */
	public void installDriver() {
		try {
			
			ProcessBuilder bootBuilder = new ProcessBuilder(
				"cmd.exe", "/c", cmdBoot[1]
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
			
		} catch(IOException | InterruptedException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	/**
	 * Unmounts and repackages the recently mounted & installed file.
	 */
	public void unMount() {
		try {
			
			ProcessBuilder bootBuilder = new ProcessBuilder(
				"cmd.exe", "/c", cmdBoot[2]
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
		} catch(IOException | InterruptedException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	/**
	 * Mounts the install.wim
	 */
	public void mountInstall() {
		try {
			
			ProcessBuilder bootBuilder = new ProcessBuilder(
				"cmd.exe", "/c", cmdBoot[3]
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
		} catch(IOException | InterruptedException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
}
