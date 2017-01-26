package net.hypr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.SwingWorker;

public class cmdExec extends SwingWorker<Integer, String> {
	
	private int status;
	
	private FormPanel formPanel = new FormPanel();
	private FooterBar footerBar = new FooterBar();
	private TextPanel textPanel = new TextPanel();
	
	public cmdExec() {
		textPanel.appendText((this.getState()).toString());
	}
	
	@Override
	protected Integer doInBackground() {
		try {
			
			ProcessBuilder pb = new ProcessBuilder(
				"cmd.exe", "/c", "copy NUL createThis.txt"
			);
			pb.directory(new File(formPanel.workspaceDir.toString()));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			String s;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while((s = stdout.readLine()) != null && !isCancelled()) {
				publish(s);
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
		
		return status;
	}
	
	@Override
	protected void process(List<String> messages) {
		formPanel.okBtn.setText((this.getState()).toString());
		for(String message : messages) {
			textPanel.appendText(message + "\n");
		}
	}
	
	@Override
	protected void done() {
		textPanel.appendText((this.getState()).toString() + " " + status);
		formPanel.okBtn.setEnabled(true);
		footerBar.progress.setIndeterminate(false);
		formPanel.disableList(false);
		formPanel.disableWorkspace(false);
	}
	
}
