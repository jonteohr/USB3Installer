package net.hypr.core;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class FormPanel extends JPanel implements ActionListener {
	
	private Path workspaceDir;
	private String driversDir = "Intel USB 3";
	
	private JLabel workLab = new JLabel("Workspace: ");
	private JLabel driversLab = new JLabel("Driver: ");
	private JTextField workField = new JTextField("Not selected..", 8);
	private JTextField driversField = new JTextField(driversDir, 8);
	private JLabel osLab = new JLabel("OS: ");
	private String[] osList = {"Choose OS", "Windows 7 Pro", "Windows 7 Home Basic", "Windows 7 Home Premium", "Windows 7 Ultimate"};
	private JButton okBtn = new JButton("Go!");
	private JFileChooser chooser;
	private JButton workspaceChooseBtn = new JButton(UIManager.getIcon("FileView.directoryIcon"));
	private JComboBox osDropDown = new JComboBox(osList);
	
	public int osIndex;
	
	private FormListener formListener;
	
	public FormPanel() {
		
		osDropDown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Object item = e.getItem();
					
					if(item.equals(osList[1])) {
						osIndex = 3;
						System.out.println(osList[1] + " [" + osIndex + "]");
					} else if(item.equals(osList[2])) {
						osIndex = 1;
						System.out.println(osList[2] + " [" + osIndex + "]");
					} else if(item.equals(osList[3])) {
						osIndex = 2;
						System.out.println(osList[3] + " [" + osIndex + "]");
					} else if(item.equals(osList[4])) {
						osIndex = 4;
						System.out.println(osList[4] + " [" + osIndex + "]");
					}
				}
			}
		});
		
		workspaceChooseBtn.setToolTipText("Choose workspace");
		okBtn.setToolTipText("Install drivers!");
		
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Path iso = workspaceDir;
				
				FormEvent ev = new FormEvent(this, iso);
				ev.setWorkspace(workspaceDir);
				if(formListener != null) {
					try {
						formListener.formEventOccured(ev);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		workspaceChooseBtn.addActionListener(this);
		
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Configuration");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
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
	
	/**
	 * Returns the operating system index value.
	 * @return int osIndex
	 */
	public Integer getOsIndex() {
		return osIndex;
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser();
		chooser.setDialogTitle("Choose workspace");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setCurrentDirectory(new java.io.File("."));
	    System.out.println("Workspace chooser clicked");
	    
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			workspaceDir = chooser.getSelectedFile().toPath();
			workField.setText(workspaceDir.toString());
			System.out.println("New workspace: " + workspaceDir);
		}
		
	}

}
