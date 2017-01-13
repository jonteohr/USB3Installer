package net.hypr.core;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel nameLab = new JLabel("Mapp: ");
	private JLabel occupationLab = new JLabel("Occupation: ");
	private JTextField nameField = new JTextField(10);
	private JTextField occupationField = new JTextField(10);
	private JLabel osLab = new JLabel("Operativsystem: ");
	private String[] osList = {"Välj OS", "Windows 7 Pro", "Windows 7 Home", "Windows 7 Ultimate"};
	private JButton okBtn = new JButton("OK");
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 265;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Konfiguration");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		Insets labelInset = new Insets(0, 0, 0, 5);
		Insets textInset = new Insets(0, 0, 0, 0);
		
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
		add(nameLab, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = textInset;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		/*
		 * SECOND ROW
		 */
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = labelInset;
		add(occupationLab, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = textInset;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
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

}
