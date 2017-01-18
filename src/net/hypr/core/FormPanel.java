package net.hypr.core;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private String workspaceDir = "Not selected..";
	private String driversDir = "Not selected..";
	
	private JLabel workLab = new JLabel("Workspace: ");
	private JLabel driversLab = new JLabel("Drivers: ");
	private JTextField workField = new JTextField(workspaceDir, 8);
	private JTextField driversField = new JTextField(driversDir, 8);
	private JLabel osLab = new JLabel("OS: ");
	private String[] osList = {"Choose OS", "Windows 7 Pro", "Windows 7 Home", "Windows 7 Ultimate"};
	private JButton okBtn = new JButton("Go!");
	private JFileChooser chooser;
	private JButton workspaceChooseBtn = new JButton(UIManager.getIcon("FileView.directoryIcon"));
	private JButton driversChooseBtn = new JButton(UIManager.getIcon("FileView.directoryIcon"));
	
	private FormListener formListener;
	
	public FormPanel() {
		
		workspaceChooseBtn.setToolTipText("Choose workspace");
		driversChooseBtn.setToolTipText("Choose drivers");
		okBtn.setToolTipText("Install drivers!");
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String iso = workField.getText();
				String drivers = driversField.getText();
				
				FormEvent ev = new FormEvent(this, iso, drivers);
				
				if(formListener != null) {
					formListener.formEventOccured(ev);
				}
			}
		});
		
		workspaceChooseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.setDialogTitle("Choose workspace");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setCurrentDirectory(new File("."));
				
			    System.out.println("Workspace chooser clicked");
			    System.err.println("This has no function yet..");
			    
			}
		});
		
		driversChooseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.setDialogTitle("Choose drivers");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setCurrentDirectory(new File("."));
			    
			    System.out.println("Drivers chooser clicked.");
			    System.err.println("This has no function yet..");
			}
		});
		
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Configuration");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		Insets labelInset = new Insets(0, 0, 0, 5);
		Insets textInset = new Insets(0, 0, 0, 0);
		
		workField.setBackground(null);
		workField.setEditable(false);
		workField.setBorder(null);
		
		driversField.setBackground(null);
		driversField.setEditable(false);
		driversField.setBorder(null);
		
		/*
		 * FIRST ROW
		 */
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = labelInset;
		add(workLab, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = textInset;
		gc.anchor = GridBagConstraints.LINE_START;
		add(workField, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(workspaceChooseBtn, gc);
		
		/*
		 * SECOND ROW
		 */
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = labelInset;
		add(driversLab, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = textInset;
		gc.anchor = GridBagConstraints.LINE_START;
		add(driversField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(driversChooseBtn, gc);
		
		/*
		 * THIRD ROW
		 */
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = labelInset;
		gc.anchor = GridBagConstraints.LINE_END;
		add(osLab, gc);
		
		JComboBox osDropDown = new JComboBox(osList);
		osDropDown.setSelectedIndex(0);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = textInset;
		gc.anchor = GridBagConstraints.LINE_START;
		add(osDropDown, gc);
		
		
		/*
		 * FOURTH ROW
		 */
		gc.weightx = 1;
		gc.weighty = 3.0;
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
		
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

}
