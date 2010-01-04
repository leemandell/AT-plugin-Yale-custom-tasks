
package edu.yale.plugins.tasks;

import org.java.plugin.Plugin;
import org.archiviststoolkit.plugin.ATPlugin;
import org.archiviststoolkit.ApplicationFrame;
import org.archiviststoolkit.hibernate.SessionFactory;
import org.archiviststoolkit.util.UserPreferences;
import org.archiviststoolkit.dialog.ErrorDialog;
import org.archiviststoolkit.dialog.ATFileChooser;
import org.archiviststoolkit.model.*;
import org.archiviststoolkit.editor.ArchDescriptionFields;
import org.archiviststoolkit.swing.InfiniteProgressPanel;
import org.archiviststoolkit.swing.ATProgressUtil;
import org.archiviststoolkit.mydomain.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;

import edu.yale.plugins.tasks.search.BoxLookup;
import edu.yale.plugins.tasks.search.BoxLookupReturnScreen;

/**
 * Archivists' Toolkit(TM) Copyright � 2005-2007 Regents of the University of California, New York University, & Five Colleges, Inc.
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
 * A simple plugin to test the functionality of 
 *
 * Created by IntelliJ IDEA.
 *
 * @author: Nathan Stevens
 * Date: Feb 10, 2009
 * Time: 1:07:45 PM
 */

public class YalePluginTasks extends Plugin implements ATPlugin {

	//testing git commits

	public static final String APPLY_CONTAINER_INFORMATION_TASK = "Assign Container Information";
	public static final String EXPORT_VOYAGER_INFORMATION = "Export Voyager Information";
	public static final String PARTIAL_EAD_IMPORT = "Partial EAD Import";
	public static final String BOX_LOOKUP = "Box Lookup";


	public static final String PLUGIN_NAME = "Yale Tasks";

	protected ApplicationFrame mainFrame;
	protected ArchDescriptionFields parentEditorFields;
	private ResourcesCommon resourceOrComponent;

	protected YaleAnalogInstancesFields editorFields;
	private DomainEditor analogInstanceEditor;
	private JTable callingTable;
	private int selectedRow;
	protected ArchDescriptionAnalogInstances analogInstance;

	private Connection con = null;


	// the default constructor
	public YalePluginTasks() { }

	// get the category this plugin belongs to
	public String getCategory() {
		return ATPlugin.DEFAULT_CATEGORY;
	}

	// get the name of this plugin
	public String getName() {
		return PLUGIN_NAME;
	}

	// Method to set the main frame
	public void setApplicationFrame(ApplicationFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	// Method that display the window
	public void showPlugin() {
	}

	// method to display a plugin that needs a parent frame
	public void showPlugin(Frame owner) {
	}

	// method to display a plugin that needs a parent dialog
	public void showPlugin(Dialog owner) {
	}

	// Method to return the jpanels for plugins that are in an AT editor
	public HashMap getEmbeddedPanels() {
		return null;
	}

	public void setEditorField(DomainEditorFields domainEditorFields) {
	}

	// Method to set the editor field component
	public void setEditorField(ArchDescriptionFields editorField) {
	}

	/**
	 * Method to set the domain object for this plugin
	 */
	public void setModel(DomainObject domainObject, InfiniteProgressPanel monitor) {
	}

	/**
	 * Method to get the table from which the record was selected
	 * @param callingTable The table containing the record
	 */
	public void setCallingTable(JTable callingTable) {
	}

	/**
	 * Method to set the selected row of the calling table
	 * @param selectedRow
	 */
	public void setSelectedRow(int selectedRow) {
	}

	/**
	 * Method to set the current record number along with the total number of records
	 * @param recordNumber The current record number
	 * @param totalRecords The total number of records
	 */
	public void setRecordPositionText(int recordNumber, int totalRecords) { }

	// Method to do a specific task in the plugin
	public void doTask(String task) {
		ApplicationFrame frame = ApplicationFrame.getInstance();
		DomainTableWorkSurface workSurface= frame.getWorkSurfaceContainer().getCurrentWorkSurface();
		final DomainSortableTable worksurfaceTable = (DomainSortableTable)workSurface.getTable();
		final ResourcesDAO access = new ResourcesDAO();
		if (task.equals(APPLY_CONTAINER_INFORMATION_TASK)) {
			if (workSurface.getClazz() != Resources.class) {
				JOptionPane.showMessageDialog(frame, "This function only works for the resources module");
			} else if (worksurfaceTable.getSelectedRowCount() != 1) {
				JOptionPane.showMessageDialog(frame, "You must select one resource record");
			} else {

				Thread performer = new Thread(new Runnable() {
					public void run() {
						InfiniteProgressPanel monitor = ATProgressUtil.createModalProgressMonitor(ApplicationFrame.getInstance(), 1000, true);
						monitor.start("gathering containers...");
						Resources resource = (Resources) worksurfaceTable.getFilteredList().get(worksurfaceTable.getSelectedRow());
						YaleLocationAssignmentResources dialog = null;
						System.out.println("I am here");
						try {
							resource =
									(Resources) access.findByPrimaryKeyLongSession(resource.getIdentifier());
							dialog = new YaleLocationAssignmentResources(ApplicationFrame.getInstance());
							dialog.assignContainerListValues(resource.gatherContainers(monitor));
						} catch (LookupException e) {
							monitor.close();
							new ErrorDialog("", e).showDialog();
						} finally {
							monitor.close();
						}
						try {
							finishAssignContainerInformation(dialog, access, resource);
						} catch (PersistenceException e) {
							new ErrorDialog("", e).showDialog();
						} catch (SQLException e) {
							new ErrorDialog("", e).showDialog();
						}
					}
				}, "Gather containers");
				performer.start();
			}

		} else if (task.equals(EXPORT_VOYAGER_INFORMATION)) {
			if (workSurface.getClazz() != Resources.class) {
				JOptionPane.showMessageDialog(frame, "This function only works for the resources module");
			} else if (worksurfaceTable.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(frame, "You must select at least one resource record");
			} else {
				ATFileChooser filechooser = new ATFileChooser();
				if(filechooser.showSaveDialog(ApplicationFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
					final File outputFile = filechooser.getSelectedFile();
					Thread performer = new Thread(new Runnable() {
						public void run() {
							InfiniteProgressPanel monitor = ATProgressUtil.createModalProgressMonitor(ApplicationFrame.getInstance(), 1000);
							monitor.start("Exporting Voyager Information...");
							long resourceId;
							Resources selectedResource, resource;
							ArchDescriptionAnalogInstances instance;
							PrintWriter writer = null;
							ContainerGatherer gatherer;
							String accessionNumber;
							try {
								writer = new PrintWriter(outputFile);
								int totalRecords = worksurfaceTable.getSelectedObjects().size();
								for (int i: worksurfaceTable.getSelectedRows())  {
									selectedResource = (Resources) worksurfaceTable.getFilteredList().get(i);
									resourceId = selectedResource.getResourceId();
									resource = (Resources) access.findByPrimaryKeyLongSession(resourceId);
									monitor.setTextLine("Exporting resource " + i + " of " + totalRecords + " - " + resource.getTitle(), 1);
									gatherer = new ContainerGatherer(resource);
									for (ATContainer container: gatherer.gatherContainers(monitor)) {
										accessionNumber = container.getAccessionNumber();
										writer.println(resource.getResourceIdentifier2() + "," +
												gatherer.getVoyagerHoldingsKey() + "," +
												accessionNumber + "," +
												gatherer.lookupAccessionDateFromHashmap(accessionNumber) + "," +
												container.getContainerLabel() + "," +
												"," + //just a dummy for box number extension
												container.getBarcode() + ",");
									}
								}
								writer.flush();
							} catch (LookupException e) {
								monitor.close();
								new ErrorDialog("Error loading resource", e).showDialog();
							} catch (FileNotFoundException e) {
								monitor.close();
								new ErrorDialog("Error creating file writer", e).showDialog();
							} catch (PersistenceException e) {
								new ErrorDialog("Error looking up accession date", e).showDialog();
							} finally {
								writer.close();
								monitor.close();
							}
						}
					}, "Exporting Voyager Information");
					performer.start();
				}
			}
		} else if (task.equals(BOX_LOOKUP)) {
			try {
				BoxLookupReturnScreen returnScreen = new BoxLookupReturnScreen(ApplicationFrame.getInstance());
				returnScreen.showDialog();
			} catch (ClassNotFoundException e) {
				new ErrorDialog("", e).showDialog();
			} catch (SQLException e) {
				new ErrorDialog("", e).showDialog();
			}
		}
	}


	// Method to get the list of specific task the plugin can perform
	public String[] getTaskList() {
		String[] tasks = {APPLY_CONTAINER_INFORMATION_TASK, EXPORT_VOYAGER_INFORMATION, BOX_LOOKUP};
		return tasks;
	}

	// Method to return the editor type for this plugin
	public String getEditorType() {
		return null;
	}

	// code that is executed when plugin starts. not used here
	protected void doStart()  {


		try {

			Class.forName(SessionFactory.getDriverClass());
			con = DriverManager.getConnection(SessionFactory.getDatabaseUrl(),
					SessionFactory.getUserName(),
					SessionFactory.getPassword());

		} catch (ClassNotFoundException e) {
			new ErrorDialog("", e).showDialog();
			UserPreferences.getInstance().saveToPreferences();
			System.exit(0);
		} catch (SQLException e) {
			new ErrorDialog("", e).showDialog();
			UserPreferences.getInstance().saveToPreferences();
			System.exit(0);
		}


	}

	// code that is executed after plugin stops. not used here
	protected void doStop()  { }

	// main method for testing only
	public static void main(String[] args) {
		YaleLocationAssignmentResources dialog = new YaleLocationAssignmentResources(null);
		dialog.showDialog();
	}

	private void finishAssignContainerInformation(YaleLocationAssignmentResources dialog, ResourcesDAO access, Resources resource) throws PersistenceException, SQLException {
		if (dialog != null) {
//			System.out.println("about to show dialog");
			ApplicationFrame.getInstance().setRecordClean();
			dialog.showDialog();
			if (ApplicationFrame.getInstance().getRecordDirty()) {
				access.updateLongSession(resource);
			} else {
				access.closeLongSession();
			}
		}
	}


}
