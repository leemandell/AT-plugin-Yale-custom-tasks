/**
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
 * Created by JFormDesigner on Tue Mar 14 08:54:41 PST 2006
 */

package edu.yale.plugins.tasks;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import org.archiviststoolkit.swing.ATBasicComponentFactory;
import org.archiviststoolkit.swing.ATBasicDialog;
import org.archiviststoolkit.model.ArchDescriptionAnalogInstances;
import org.archiviststoolkit.mydomain.DomainEditorFields;
import org.archiviststoolkit.mydomain.DomainEditor;
import org.archiviststoolkit.util.SequencedObjectsUtils;
import org.archiviststoolkit.structure.ATFieldInfo;

public class YaleAnalogInstancesFields extends ATBasicDialog {

	protected YaleAnalogInstancesFields() {
		super();
		initComponents();
	}

	public YaleAnalogInstancesFields(java.awt.Dialog dialog) throws HeadlessException {
		super(dialog);
		initComponents();
	}

	public YaleAnalogInstancesFields(Frame frame) throws HeadlessException {
		super(frame);
		initComponents();
	}

	private void cancelActionPerformed(ActionEvent e) {
		super.performCancelAction();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		panel2 = new JDialog();
		fields = new JPanel();
		label_subjectSource4 = new JLabel();
		containerLabel3 = new JTextField();
		separator3 = new JSeparator();
		label_subjectTermType3 = new JLabel();
		container3Type = new JComboBox();
		label_subjectSource7 = new JLabel();
		container3Type2 = new JComboBox();
		separator2 = new JSeparator();
		panel1 = new JPanel();
		rights3 = new JCheckBox();
		rights2 = new JCheckBox();
		panel3 = new JPanel();
		cancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== panel2 ========
		{
			Container panel2ContentPane = panel2.getContentPane();
			panel2ContentPane.setLayout(new BorderLayout());

			//======== fields ========
			{
				fields.setBorder(Borders.DLU4_BORDER);
				fields.setBackground(new Color(234, 201, 250));
				fields.setOpaque(false);
				fields.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				fields.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;400px):grow")
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
				fields.add(label_subjectSource4, cc.xy(1, 1));

				//---- containerLabel3 ----
				containerLabel3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				fields.add(containerLabel3, cc.xy(3, 1));

				//---- separator3 ----
				separator3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				separator3.setForeground(new Color(147, 131, 86));
				fields.add(separator3, cc.xywh(1, 3, 3, 1));

				//---- label_subjectTermType3 ----
				label_subjectTermType3.setText("Container 3 Type");
				label_subjectTermType3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				ATFieldInfo.assignLabelInfo(label_subjectTermType3, ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_CONTAINER3_TYPE);
				fields.add(label_subjectTermType3, cc.xy(1, 5));

				//---- container3Type ----
				container3Type.setOpaque(false);
				container3Type.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				fields.add(container3Type, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- label_subjectSource7 ----
				label_subjectSource7.setText("User Defined String 2");
				label_subjectSource7.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				ATFieldInfo.assignLabelInfo(label_subjectSource7, ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_USER_DEFINED_STRING2);
				fields.add(label_subjectSource7, cc.xy(1, 7));

				//---- container3Type2 ----
				container3Type2.setOpaque(false);
				container3Type2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				fields.add(container3Type2, cc.xywh(3, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- separator2 ----
				separator2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
				separator2.setForeground(new Color(147, 131, 86));
				fields.add(separator2, cc.xywh(1, 9, 3, 1));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						},
						RowSpec.decodeSpecs("default")));

					//---- rights3 ----
					rights3.setText("User Defined Boolean 1");
					rights3.setOpaque(false);
					rights3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					rights3.setText(ATFieldInfo.getLabel(ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_USER_DEFINED_BOOLEAN1));
					panel1.add(rights3, cc.xywh(1, 1, 2, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

					//---- rights2 ----
					rights2.setText("User Defined Boolean 2");
					rights2.setOpaque(false);
					rights2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					rights2.setText(ATFieldInfo.getLabel(ArchDescriptionAnalogInstances.class, ArchDescriptionAnalogInstances.PROPERTYNAME_USER_DEFINED_BOOLEAN2));
					panel1.add(rights2, cc.xywh(3, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				}
				fields.add(panel1, cc.xywh(1, 11, 3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

				//======== panel3 ========
				{
					panel3.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- cancel ----
					cancel.setText("Cancel");
					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancelActionPerformed(e);
						}
					});
					panel3.add(cancel, cc.xy(3, 1));
				}
				fields.add(panel3, cc.xywh(1, 13, 3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel2ContentPane.add(fields, BorderLayout.CENTER);
			panel2.pack();
			panel2.setLocationRelativeTo(panel2.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JDialog panel2;
	private JPanel fields;
	private JLabel label_subjectSource4;
	public JTextField containerLabel3;
	private JSeparator separator3;
	private JLabel label_subjectTermType3;
	public JComboBox container3Type;
	private JLabel label_subjectSource7;
	public JComboBox container3Type2;
	private JSeparator separator2;
	private JPanel panel1;
	public JCheckBox rights3;
	public JCheckBox rights2;
	private JPanel panel3;
	private JButton cancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	/**
	 * Displays the dialog box representing the editor.
	 *
	 * @return true if it displayed okay
	 */

	/**
	 * The status of the editor.
	 */
	protected int status = 0;


}
