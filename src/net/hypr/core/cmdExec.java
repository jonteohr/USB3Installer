package net.hypr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

public class cmdExec extends SwingWorker<List<String>, String> {

	//private MainFrame mainFrame = new MainFrame();
	private TextPanel textPanel = new TextPanel();
	private FormPanel formPanel = new FormPanel();
	
	
	/**
	 * Cmd commands that are to be executed.
	 * [2] and [1] repeated for both files.
	 */
	protected String[] cmd = {
			"dism /mount-wim /wimfile:boot.wim /index:2 /mountdir:mount",
			"dism /image:mount /add-driver:\"driver\" /recurse",
			"dism /unmount-wim /mountdir:mount /commit",
			""
	};
	
	
	@Override
	protected List<String> doInBackground() throws Exception {
		List<String> output = new ArrayList<>(25);
		ProcessBuilder builder = new ProcessBuilder(
				"cmd.exe", "/k", cmd[0] + " && " + cmd[1] + " && " + cmd[2] + " && " + cmd[3] + " && " + cmd[1] + " && " + cmd[2]
			);
		builder.directory(new File(formPanel.workspaceDir.toString()));
		builder.redirectErrorStream(true);
		
		Process p = builder.start();
		try(BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
			String line = null;
			while((line = r.readLine()) != null) {
				output.add(line);
				publish(line);
			}
		}
		
		return output;
	}
	
	@Override
	protected void process(List<String> chunks) {
		for(String text : chunks) {
			textPanel.appendText(text + "\n");
		}
	}
	
	@Override
	protected void done() {
		try {
			
			
			
		} catch (Exception ignore) {
			
		}
	}

}
