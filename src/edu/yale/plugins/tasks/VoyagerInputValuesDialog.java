/*
 * Archivists' Toolkit(TM) Copyright © 2005-2007 Regents of the University of California, New York University, & Five Colleges, Inc.  
 * All rights reserved. 
 *	
 * This software is free. You can redistribute it and / or modify it under the terms of the Educational Community License (ECL) 
 * version 1.0 (http://www.opensource.org/licenses/ecl1.php) 
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the ECL license for more details about permissions and limitations. 
 *
 *
 * Archivists' Toolkit(TM) 
 * http://www.archiviststoolkit.org 
 * info@archiviststoolkit.org 
 *
 * Created by JFormDesigner on Mon Sep 14 11:30:00 EDT 2009
 */

package edu.yale.plugins.tasks;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import org.archiviststoolkit.swing.*;
public class VoyagerInputValuesDialog extends ATBasicDialog {

	private String keyHolding;

	public VoyagerInputValuesDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public VoyagerInputValuesDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void okButtonActionPerformed() {
		if (voyagerKey.getText().length() == 0 || voyagerHoldingNumber.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "You must enter both a bib and holding number", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			System.out.println("Concat: " + voyagerKey.getText() + "_"+ voyagerHoldingNumber.getText());
			setKeyHolding(voyagerKey.getText() + "_"+ voyagerHoldingNumber.getText());
			System.out.println("KeyHolding: " + getKeyHolding());
			super.performOkAction();
		}
	}

	private void cancelButtonActionPerformed() {
		super.performCancelAction();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		voyagerKey = new JTextField();
		label2 = new JLabel();
		voyagerHoldingNumber = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setModal(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));

				//---- label1 ----
				label1.setText("Voyager Key");
				contentPanel.add(label1, cc.xy(1, 1));

				//---- voyagerKey ----
				voyagerKey.setColumns(15);
				contentPanel.add(voyagerKey, cc.xy(3, 1));

				//---- label2 ----
				label2.setText("Voyager Holding #");
				contentPanel.add(label2, cc.xy(1, 3));

				//---- voyagerHoldingNumber ----
				voyagerHoldingNumber.setColumns(15);
				contentPanel.add(voyagerHoldingNumber, cc.xy(3, 3));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("pref")));

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed();
					}
				});
				buttonBar.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed();
					}
				});
				buttonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JTextField voyagerKey;
	private JLabel label2;
	private JTextField voyagerHoldingNumber;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public String getKeyHolding() {
		return keyHolding;
	}

	public void setKeyHolding(String keyHolding) {
		this.keyHolding = keyHolding;
	}
}
