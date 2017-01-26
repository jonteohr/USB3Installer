package net.hypr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.SwingWorker;

public class cmdExec extends SwingWorker<Integer, String> {
	
	/**
	 * Cmd commands that are to be executed.
	 * [2] and [1] repeated for both files.
	 */
	public String[] cmd = {
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
		
		try {
			
			ProcessBuilder pb = new ProcessBuilder(
				"cmd.exe", "/c", cmd[0] + " && " + cmd[1] + " && " + cmd[2] + " && " + cmd[3] + " && " + cmd[1] + " && " + cmd[2]
			);
			pb.directory(new File(formPanel.workspaceDir.toString()));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			String s;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while((s = stdout.readLine()) != null && !isCancelled()) {
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
		textPanel.appendText((this.getState()).toString() + "!");
		textPanel.appendText("Make sure you check the log for errors.");
		formPanel.okBtn.setEnabled(true);
		footerBar.progress.setIndeterminate(false);
		formPanel.disableList(false);
		formPanel.disableWorkspace(false);
	}
	
}
