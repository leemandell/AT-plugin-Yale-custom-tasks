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
 * Created by JFormDesigner on Wed Jul 22 11:51:13 EDT 2009
 */

package edu.yale.plugins.tasks;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import org.archiviststoolkit.swing.ATBasicDialog;
import org.archiviststoolkit.swing.ATBasicComponentFactory;
import org.archiviststoolkit.structure.ATFieldInfo;
import org.archiviststoolkit.model.ArchDescriptionAnalogInstances;
import org.archiviststoolkit.util.LookupListUtils;

public class YaleAssignContainerInformation extends ATBasicDialog {
	public YaleAssignContainerInformation(Frame owner) {
		super(owner);
		initComponents();
	}

	public YaleAssignContainerInformation(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void assignValuesActionPerformed(ActionEvent e) {
		super.performOkAction();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		super.performCancelAction();
	}

	public String getBarcode() {
		return barcode.getText();
	}

	public String getContainer3Type() {
		return (String)container3Type.getSelectedItem();
	}

	public String getUserDefinedString2() {
		return (String)userDefinedString2.getSelectedItem();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label_subjectSource4 = new JLabel();
		barcode = new JTextField();
		label_container3Type = new JLabel();
		container3Type = ATBasicComponentFactory.createUnboundComboBox(LookupListUtils.getLookupListValues(ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_CONTAINER3_TYPE));
		label_userDefinedString2 = new JLabel();
		userDefinedString2 = ATBasicComponentFactory.createUnboundComboBox(LookupListUtils.getLookupListValues(ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_CONTAINER3_TYPE));
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel();
		restrictionNoChange = new JRadioButton();
		restrictionSetRestrict = new JRadioButton();
		restrictionSetUnrestricted = new JRadioButton();
		panel3 = new JPanel();
		label2 = new JLabel();
		exportedToVoyagerNoChange = new JRadioButton();
		exportedToVoyagerSetExported = new JRadioButton();
		exportedToVoyagerSetNotExported = new JRadioButton();
		buttonBar = new JPanel();
		assignValues = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setModal(true);
		setBackground(new Color(200, 205, 232));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setBackground(new Color(200, 205, 232));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setOpaque(false);
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

				//---- label_subjectSource4 ----
				label_subjectSource4.setText("Barcode");
				label_subjectSource4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				ATFieldInfo.assignLabelInfo(label_subjectSource4, ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_BARCODE);
				contentPanel.add(label_subjectSource4, cc.xy(1, 1));

				//---- barcode ----
				barcode.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				contentPanel.add(barcode, cc.xy(3, 1));

				//---- label_container3Type ----
				label_container3Type.setText("Container 3 Type");
				label_container3Type.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				ATFieldInfo.assignLabelInfo(label_container3Type, ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_CONTAINER3_TYPE);
				contentPanel.add(label_container3Type, cc.xy(1, 3));

				//---- container3Type ----
				container3Type.setOpaque(false);
				container3Type.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				contentPanel.add(container3Type, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- label_userDefinedString2 ----
				label_userDefinedString2.setText("User Defined String 2");
				label_userDefinedString2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				ATFieldInfo.assignLabelInfo(label_userDefinedString2, ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_USER_DEFINED_STRING2);
				contentPanel.add(label_userDefinedString2, cc.xy(1, 5));

				//---- userDefinedString2 ----
				userDefinedString2.setOpaque(false);
				userDefinedString2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				contentPanel.add(userDefinedString2, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//======== panel1 ========
				{
					panel1.setOpaque(false);
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						},
						RowSpec.decodeSpecs("default")));

					//======== panel2 ========
					{
						panel2.setOpaque(false);
						panel2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- label1 ----
						label1.setText("Restrictions:");
						panel2.add(label1, cc.xy(1, 1));

						//---- restrictionNoChange ----
						restrictionNoChange.setText("No change");
						restrictionNoChange.setSelected(true);
						panel2.add(restrictionNoChange, cc.xy(3, 1));

						//---- restrictionSetRestrict ----
						restrictionSetRestrict.setText("Restrict");
						panel2.add(restrictionSetRestrict, cc.xy(5, 1));

						//---- restrictionSetUnrestricted ----
						restrictionSetUnrestricted.setText("Unrestrict");
						panel2.add(restrictionSetUnrestricted, cc.xy(7, 1));
					}
					panel1.add(panel2, cc.xywh(1, 1, 3, 1));
				}
				contentPanel.add(panel1, cc.xywh(1, 7, 3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

				//======== panel3 ========
				{
					panel3.setOpaque(false);
					panel3.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- label2 ----
					label2.setText("Export to voyager");
					panel3.add(label2, cc.xy(1, 1));

					//---- exportedToVoyagerNoChange ----
					exportedToVoyagerNoChange.setText("No change");
					exportedToVoyagerNoChange.setSelected(true);
					panel3.add(exportedToVoyagerNoChange, cc.xy(3, 1));

					//---- exportedToVoyagerSetExported ----
					exportedToVoyagerSetExported.setText("Set exported");
					panel3.add(exportedToVoyagerSetExported, cc.xy(5, 1));

					//---- exportedToVoyagerSetNotExported ----
					exportedToVoyagerSetNotExported.setText("Set not exported");
					panel3.add(exportedToVoyagerSetNotExported, cc.xy(7, 1));
				}
				contentPanel.add(panel3, cc.xywh(1, 9, 3, 1));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setOpaque(false);
				buttonBar.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("pref")));

				//---- assignValues ----
				assignValues.setText("Assign Values");
				assignValues.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						assignValuesActionPerformed(e);
					}
				});
				buttonBar.add(assignValues, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
				buttonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//---- restrictionsButtonGroup ----
		ButtonGroup restrictionsButtonGroup = new ButtonGroup();
		restrictionsButtonGroup.add(restrictionNoChange);
		restrictionsButtonGroup.add(restrictionSetRestrict);
		restrictionsButtonGroup.add(restrictionSetUnrestricted);

		//---- exportedToVoyagerButtonGroup ----
		ButtonGroup exportedToVoyagerButtonGroup = new ButtonGroup();
		exportedToVoyagerButtonGroup.add(exportedToVoyagerNoChange);
		exportedToVoyagerButtonGroup.add(exportedToVoyagerSetExported);
		exportedToVoyagerButtonGroup.add(exportedToVoyagerSetNotExported);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label_subjectSource4;
	public JTextField barcode;
	private JLabel label_container3Type;
	public JComboBox container3Type;
	private JLabel label_userDefinedString2;
	public JComboBox userDefinedString2;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel label1;
	private JRadioButton restrictionNoChange;
	private JRadioButton restrictionSetRestrict;
	private JRadioButton restrictionSetUnrestricted;
	private JPanel panel3;
	private JLabel label2;
	private JRadioButton exportedToVoyagerNoChange;
	private JRadioButton exportedToVoyagerSetExported;
	private JRadioButton exportedToVoyagerSetNotExported;
	private JPanel buttonBar;
	private JButton assignValues;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public Boolean restrictionChange() {
		if (this.restrictionNoChange.isSelected()) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean newRestrictionValue() {
		if (this.restrictionSetRestrict.isSelected()) {
			return true;
		} else if (this.restrictionSetUnrestricted.isSelected()) {
			return false;
		} else {
			return null;
		}
	}

	public Boolean exportedToVoyagerChange() {
		if (this.exportedToVoyagerNoChange.isSelected()) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean newExportedToVoyagerValue() {
		if (this.exportedToVoyagerSetExported.isSelected()) {
			return true;
		} else if (this.exportedToVoyagerSetNotExported.isSelected()) {
			return false;
		} else {
			return null;
		}
	}

}
