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
 * Created by JFormDesigner on Tue Nov 03 15:58:36 EST 2009
 */

package edu.yale.plugins.tasks.search;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import org.archiviststoolkit.swing.ATBasicDialog;
import edu.yale.plugins.tasks.Yale_AlternatingRowColorTable;

public class BoxLookupReturnScreen extends ATBasicDialog {

	private String msNumber;
	private String ruNumber;
	private String series;
	private String accessionNumber;
	private String box;
	BoxLookup boxLookup;


	/** event list that hosts the issues */
	private EventList<BoxLookupReturnRecords> resultsEventList = new BasicEventList<BoxLookupReturnRecords>();


	public BoxLookupReturnScreen(Frame owner) throws ClassNotFoundException, SQLException {
		super(owner);
		initComponents();
		boxLookup = new BoxLookup();
	}

	public BoxLookupReturnScreen(Dialog owner) throws ClassNotFoundException, SQLException {
		super(owner);
		initComponents();
		boxLookup = new BoxLookup();
	}

	private void doneButtonActionPerformed() {
		super.performCancelAction();
	}

	private void searchButtonActionPerformed() {
		msNumber = msNumberField.getText();
		ruNumber = ruNumberField.getText();
		series = seriesField.getText();
		accessionNumber = accessionNumberField.getText();
		box = boxField.getText();

		if (msNumber.equals("") && ruNumber.equals("")) {
			JOptionPane.showMessageDialog(this, "You must enter either an MS number or RU number");
		} else if (!msNumber.equals("") && !ruNumber.equals("")) {
			JOptionPane.showMessageDialog(this, "You can't enter both an MS number and an RU number");
		} else if (!series.equals("") && !accessionNumber.equals("")) {
			JOptionPane.showMessageDialog(this, "You can't enter both a Series and an Accession number");
		} else if (!box.equals("") && accessionNumber.equals("") && series.equals("")) {
			JOptionPane.showMessageDialog(this, "When entering a box number you must enter either a Series or Accession number");
		} else {
			boxLookup.doSearch(msNumber, ruNumber, series, accessionNumber, box, this);
		}
	}

	private void cancelButtonActionPerformed() {
		super.performCancelAction();
	}

	private void clearButtonActionPerformed() {
		msNumberField.setText("");
		ruNumberField.setText("");
		seriesField.setText("");
		accessionNumberField.setText("");
		boxField.setText("");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		panel2 = new JPanel();
		label2 = new JLabel();
		msNumberField = new JTextField();
		label3 = new JLabel();
		ruNumberField = new JTextField();
		label4 = new JLabel();
		seriesField = new JTextField();
		label5 = new JLabel();
		accessionNumberField = new JTextField();
		label6 = new JLabel();
		boxField = new JTextField();
		searchResults = new JLabel();
		buttonBar2 = new JPanel();
		searchButton = new JButton();
		clearButton = new JButton();
		cancelButton = new JButton();
		scrollPane1 = new JScrollPane();
		results = createReturnTable();
		panel1 = new JPanel();
		label1 = new JLabel();
		elapsedTime = new JLabel();
		buttonBar = new JPanel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setModal(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setPreferredSize(new Dimension(1000, 700));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setPreferredSize(new Dimension(750, 600));
				contentPanel.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));

				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						},
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));

					//---- label2 ----
					label2.setText("MS #");
					panel2.add(label2, cc.xy(1, 1));
					panel2.add(msNumberField, cc.xy(3, 1));

					//---- label3 ----
					label3.setText("RU #");
					panel2.add(label3, cc.xy(5, 1));
					panel2.add(ruNumberField, cc.xy(7, 1));

					//---- label4 ----
					label4.setText("Series");
					panel2.add(label4, cc.xy(9, 1));
					panel2.add(seriesField, cc.xy(11, 1));

					//---- label5 ----
					label5.setText("Accn");
					panel2.add(label5, cc.xy(13, 1));
					panel2.add(accessionNumberField, cc.xy(15, 1));

					//---- label6 ----
					label6.setText("Box");
					panel2.add(label6, cc.xy(17, 1));
					panel2.add(boxField, cc.xywh(19, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
					panel2.add(searchResults, cc.xywh(1, 3, 7, 1));

					//======== buttonBar2 ========
					{
						buttonBar2.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
						buttonBar2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
						buttonBar2.add(searchButton, cc.xy(2, 1));

						//---- clearButton ----
						clearButton.setText("Clear");
						clearButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								clearButtonActionPerformed();
							}
						});
						buttonBar2.add(clearButton, cc.xy(3, 1));

						//---- cancelButton ----
						cancelButton.setText("Done");
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								cancelButtonActionPerformed();
							}
						});
						buttonBar2.add(cancelButton, cc.xy(5, 1));
					}
					panel2.add(buttonBar2, cc.xywh(11, 3, 9, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				}
				contentPanel.add(panel2, cc.xy(1, 1));

				//======== scrollPane1 ========
				{
					scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane1.setPreferredSize(new Dimension(750, 450));

					//---- results ----
					results.setPreferredScrollableViewportSize(new Dimension(700, 400));
					scrollPane1.setViewportView(results);
				}
				contentPanel.add(scrollPane1, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- label1 ----
					label1.setText("Elapsed Time:");
					panel1.add(label1, cc.xy(1, 1));
					panel1.add(elapsedTime, cc.xy(3, 1));
				}
				contentPanel.add(panel1, cc.xy(1, 5));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("")));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	private Yale_AlternatingRowColorTable createReturnTable() {

		SortedList<BoxLookupReturnRecords> sortedResults = new SortedList<BoxLookupReturnRecords>(resultsEventList);

		EventTableModel<BoxLookupReturnRecords> resultsTableModel = new EventTableModel<BoxLookupReturnRecords>(sortedResults, new BoxLookupTableFormat());
		Yale_AlternatingRowColorTable returnTable = new Yale_AlternatingRowColorTable(resultsTableModel, sortedResults);
		TableComparatorChooser tableSorter = TableComparatorChooser.install(returnTable, sortedResults, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
//		returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		TableColumn col = returnTable.getColumnModel().getColumn(5);
//		int width = 150;
//		col.setPreferredWidth(width);
// 		returnTable.setPreferredSize(new Dimension(500,400));

		return returnTable;  //To change body of created methods use File | Settings | File Templates.
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JPanel panel2;
	private JLabel label2;
	private JTextField msNumberField;
	private JLabel label3;
	private JTextField ruNumberField;
	private JLabel label4;
	private JTextField seriesField;
	private JLabel label5;
	private JTextField accessionNumberField;
	private JLabel label6;
	private JTextField boxField;
	private JLabel searchResults;
	private JPanel buttonBar2;
	private JButton searchButton;
	private JButton clearButton;
	private JButton cancelButton;
	private JScrollPane scrollPane1;
	private JTable results;
	private JPanel panel1;
	private JLabel label1;
	private JLabel elapsedTime;
	private JPanel buttonBar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public void updateResultList(Collection<BoxLookupReturnRecords> results) {
		searchResults.setText("Search returned " + results.size() + " record(s)");
		resultsEventList.clear();
		resultsEventList.addAll(results);
	}

	public void setElapsedTimeText(String time) {
		elapsedTime.setText(time);
	}
}
