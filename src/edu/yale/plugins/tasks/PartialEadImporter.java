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
 * @author Lee Mandell
 * Date: Sep 16, 2009
 * Time: 12:40:36 PM
 */

package edu.yale.plugins.tasks;

import org.archiviststoolkit.model.Resources;
import org.archiviststoolkit.ApplicationFrame;
import org.archiviststoolkit.importer.ImportUtils;
import org.archiviststoolkit.swing.ATProgressUtil;
import org.archiviststoolkit.swing.InfiniteProgressPanel;
import org.archiviststoolkit.dialog.ATFileChooser;
import org.archiviststoolkit.mydomain.DomainTableWorkSurface;
import org.archiviststoolkit.mydomain.DomainSortableTable;
import org.archiviststoolkit.mydomain.ResourcesDAO;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class PartialEadImporter {


	public PartialEadImporter() {
	}


	public void doTask() {
		ApplicationFrame frame = ApplicationFrame.getInstance();
		DomainTableWorkSurface workSurface= frame.getWorkSurfaceContainer().getCurrentWorkSurface();
		final DomainSortableTable worksurfaceTable = (DomainSortableTable)workSurface.getTable();
		final ResourcesDAO access = new ResourcesDAO();

		if (workSurface.getClazz() != Resources.class) {
			JOptionPane.showMessageDialog(frame, "This function only works for the resources module");
		} else if (worksurfaceTable.getSelectedRowCount() != 1) {
			JOptionPane.showMessageDialog(frame, "You must select  one resource record");
		} else {
			ATFileChooser filechooser = new ATFileChooser();
			if(filechooser.showSaveDialog(ApplicationFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
				final File importFile = filechooser.getSelectedFile();
				Thread performer = new Thread(new Runnable() {
					public void run() {
						InfiniteProgressPanel monitor = ATProgressUtil.createModalProgressMonitor(ApplicationFrame.getInstance(), 1000);
						monitor.start("Importing partial EAD file...");
						try {
							ArrayList<String> lines = ImportUtils.loadFileIntoStringArray(importFile);

						} finally {
							monitor.close();
						}
					}
				}, "Partial EAD Import");
				performer.start();


			}

		}

	}
}
