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
 * Created by JFormDesigner on Tue Nov 03 15:14:38 EST 2009
 */

package edu.yale.plugins.tasks.search;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import org.archiviststoolkit.swing.ATBasicDialog;

public class SearchDialog extends ATBasicDialog {

	private String msNumber;
	private String ruNumber;
	private String series;
	private String accessionNumber;
	private String box;
	BoxLookup boxLookup;


	public SearchDialog(Frame owner) throws ClassNotFoundException, SQLException {
		super(owner);
		initComponents();
		boxLookup = new BoxLookup();
	}

	public SearchDialog(Dialog owner) throws ClassNotFoundException, SQLException {
		super(owner);
		initComponents();
		boxLookup = new BoxLookup();
	}

	private void searchButtonActionPerformed() {

		msNumber = msNumberField.getText();
		ruNumber = ruNumberField.getText();
		series = seriesField.getText();
		accessionNumber = accessionNumberField.getText();
		box = boxField.getText();

		if (msNumber.length() == 0 && ruNumber.length() == 0) {
			JOptionPane.showMessageDialog(this, "You must enter data");
		} else {
//			boxLookup.doSearch(msNumber, ruNumber, series, accessionNumber, box);
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
		msNumberField = new JTextField();
		label2 = new JLabel();
		ruNumberField = new JTextField();
		label3 = new JLabel();
		seriesField = new JTextField();
		label4 = new JLabel();
		accessionNumberField = new JTextField();
		label5 = new JLabel();
		boxField = new JTextField();
		buttonBar = new JPanel();
		searchButton = new JButton();
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));

				//---- label1 ----
				label1.setText("MS #");
				contentPanel.add(label1, cc.xy(1, 1));
				contentPanel.add(msNumberField, cc.xy(3, 1));

				//---- label2 ----
				label2.setText("RU #");
				contentPanel.add(label2, cc.xy(1, 3));
				contentPanel.add(ruNumberField, cc.xy(3, 3));

				//---- label3 ----
				label3.setText("Series");
				contentPanel.add(label3, cc.xy(1, 5));
				contentPanel.add(seriesField, cc.xy(3, 5));

				//---- label4 ----
				label4.setText("Accn");
				contentPanel.add(label4, cc.xy(1, 7));
				contentPanel.add(accessionNumberField, cc.xy(3, 7));

				//---- label5 ----
				label5.setText("Box");
				contentPanel.add(label5, cc.xy(1, 9));
				contentPanel.add(boxField, cc.xy(3, 9));
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

				//---- searchButton ----
				searchButton.setText("Search");
				searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchButtonActionPerformed();
					}
				});
				buttonBar.add(searchButton, cc.xy(2, 1));

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
	private JTextField msNumberField;
	private JLabel label2;
	private JTextField ruNumberField;
	private JLabel label3;
	private JTextField seriesField;
	private JLabel label4;
	private JTextField accessionNumberField;
	private JLabel label5;
	private JTextField boxField;
	private JPanel buttonBar;
	private JButton searchButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public String getMsNumber() {
		return msNumber;
	}

	public void setMsNumber(String msNumber) {
		this.msNumber = msNumber;
	}

	public String getRuNumber() {
		return ruNumber;
	}

	public void setRuNumber(String ruNumber) {
		this.ruNumber = ruNumber;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}
}
