package net.hypr.core;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
	
	protected static String version = "0.5b";
	
	protected TextPanel textPanel = new TextPanel();
	private Toolbar toolbar = new Toolbar();
	protected FormPanel formPanel = new FormPanel();
	protected FooterBar footerPanel = new FooterBar();
	private cmdExec cmdExec = new cmdExec(formPanel, footerPanel, textPanel);
	private WorkspaceWarning workWarning;
	private OSWarning osWarning;
	
	private static String windowTitle = "WIN7 USB3 Installer";
	
	URL url = this.getClass().getResource("/resource/icon.png");
	ImageIcon imageIcon = new ImageIcon(url);

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
					runWorker();
				}
			}
		});
		
		formPanel.workspaceDir = new File(".").toPath();
		formPanel.workField.setText(formPanel.workspaceDir.toString());
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(640, 480);
		setResizable(false);
		System.out.println("Launching...");
		setLocationRelativeTo(null);
		setVisible(true);
		
		setIconImage(imageIcon.getImage());
		
		/*try {
			//setIconImage(ImageIO.read(new File("resources/icons/icon.png")));
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}*/
		
		textPanel.appendText("Waiting for configuration.");
		textPanel.appendText("Need help? Click the help button in the upper menu!");
		
		
		
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				
				if(!cmdExec.running) {
					System.err.println("Closing...");
					System.exit(0);
				} else {
					String objButtons[] = {"Yes", "No"};
					int promptResult = JOptionPane.showOptionDialog(null, "A process is in progress.\nReally sure you want to quit?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, objButtons, objButtons[1]);
					if(promptResult == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
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

	
	private ImageIcon getImage(String path)
	{
	    URL url = getClass().getResource(path);
	    System.out.println(url);
	    if (url != null)
	        return (new ImageIcon(url));
	    return null;
	}
	
	/**
	 * Run the SwingWorker
	 */
	public void runWorker() {
		textPanel.clearText();
		formPanel.disableList(true);
		formPanel.disableWorkspace(true);
		cmdExec.execute();
		footerPanel.progress.setIndeterminate(true);
		formPanel.okBtn.setEnabled(false);
		footerPanel.setStatus("Working...");
		cmdExec.cmdInstall[0] = "dism /mount-wim /wimfile:install.wim /index:" + formPanel.getOsIndex() + " /mountdir:mount";
	}
	
}
