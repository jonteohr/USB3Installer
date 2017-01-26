package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	protected TextPanel textPanel = new TextPanel();
	private Toolbar toolbar = new Toolbar();
	protected FormPanel formPanel = new FormPanel();
	protected FooterBar footerPanel = new FooterBar();
	private cmdExec cmdExec;
	private WorkspaceWarning workWarning;
	private OSWarning osWarning;
	
	private Path workspaceDirectory;
	
	private static String windowTitle = "WIN7 USB3 Installer";

	/**
	 * The properties for the window itself
	 */
	public MainFrame() {
		super(windowTitle);
		setLayout(new BorderLayout());
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
		setJMenuBar(toolbar.getMenu());
		
		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
				
			}
			
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) throws Exception {
				Path workspace = e.getWorkspace();
				
				
				if(workspace == null) {
					workWarning = new WorkspaceWarning();
					textPanel.appendText("[ERROR] Workspace not selected...");
				}
				
				if(formPanel.osIndex == 0) {
					osWarning = new OSWarning();
					textPanel.appendText("[ERROR] No operating system defined!");
					
				}
				
				if(workspace != null && !(formPanel.osIndex == 0)) {
					workspaceDirectory = workspace;
					runWorker();
					
					/*
					// Try some cmd commands..
					ProcessBuilder builder = new ProcessBuilder(
							"cmd.exe", "/k", cmd[0] + " && " + cmd[1] + " && " + cmd[2] + " && " + cmd[3] + " && " + cmd[1] + " && " + cmd[2]
						);
					builder.directory(new File(workspace.toString()));
					builder.redirectErrorStream(true);
					Process p = builder.start();
					BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while(true) {
						line = r.readLine();
						if(line == null) {
							break;
						}
						textPanel.appendText(line);
						System.err.println(line);
					
					}
					p.waitFor();
					textPanel.appendText("Process finished.");
					textPanel.appendText("Make sure you check for errors!");
					*/
					
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		try {
			setIconImage(ImageIO.read(new File("resources/icon.png")));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		setVisible(true);
		
		textPanel.appendText("Waiting for configuration.");
		textPanel.appendText("Need help? Click the help button in the upper menu!");
		
		
		
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				System.err.println("Quitting...");
				
			}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
		});

	}

	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Toolbar.class.getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	/**
	 * Run the SwingWorker
	 */
	public void runWorker() {
		textPanel.clearText();
		formPanel.disableList(true);
		formPanel.disableWorkspace(true);
		cmdExec = new cmdExec(formPanel, footerPanel, textPanel);
		cmdExec.execute();
		footerPanel.progress.setIndeterminate(true);
		formPanel.okBtn.setEnabled(false);
		cmdExec.cmd[3] = "dism /mount-wim /wimfile:install.wim /index:" + formPanel.getOsIndex() + " /mountdir:mount";
		textPanel.appendText("Working in: " + workspaceDirectory);
	}
	
}
