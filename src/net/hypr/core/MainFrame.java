package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel = new TextPanel();
	private Toolbar toolbar = new Toolbar();
	private FormPanel formPanel = new FormPanel();
	private FooterBar footerPanel = new FooterBar();
	
	private static String windowTitle = "WIN7 USB3 Installer";
	
	/**
	 * Cmd commands that are to be executed.
	 * [2] and [1] repeated for both files.
	 */
	private String[] cmd = {
			"dism /mount-wim /wimfile:boot.wim /index:2 /mountdir:mount",
			"dism /image:mount /add-driver:\"driver\" /recurse",
			"dism /unmount-wim /mountdir:mount /commit",
			"dism /mount-wim /wimfile:install.wim /index:" + formPanel.getOsIndex() + " /mountdir:mount"
	};

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
					textPanel.appendText("[ERROR] Workspace not selected...");
				} else if(formPanel.osIndex == 0) {
					textPanel.appendText("[ERROR] No operating system defined!");
				} else if(workspace != null && !(formPanel.osIndex == 0)) {
					textPanel.appendText("Working in: " + workspace);
					footerPanel.setProgress(3);
					
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
	
	
	
}
