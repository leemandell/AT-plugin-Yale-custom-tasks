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
 * Date: Oct 16, 2009
 * Time: 1:35:14 PM
 */

package edu.yale.plugins.tasks.search;

import org.archiviststoolkit.dialog.ErrorDialog;
import org.archiviststoolkit.util.MyTimer;
import org.archiviststoolkit.util.StringHelper;
import org.archiviststoolkit.hibernate.SessionFactory;
import org.archiviststoolkit.model.Locations;
import org.archiviststoolkit.mydomain.DomainAccessObjectFactory;
import org.archiviststoolkit.mydomain.DomainAccessObject;
import org.archiviststoolkit.mydomain.PersistenceException;
import org.archiviststoolkit.mydomain.LookupException;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collection;
import java.util.HashMap;
import java.text.NumberFormat;

public class BoxLookup {


	TreeMap<String, SeriesInfo> seriesInfo = new TreeMap<String, SeriesInfo>();

	HashMap<Long, String> componentTitleLookup = new HashMap<Long, String>();

	String logMessage = "";

	Collection<BoxLookupReturnRecords> results = new ArrayList<BoxLookupReturnRecords>();

	DomainAccessObject locationDAO;

	Connection con;

	public BoxLookup() throws SQLException, ClassNotFoundException {
		Class.forName(SessionFactory.getDriverClass());
		con = DriverManager.getConnection(SessionFactory.getDatabaseUrl(),
				SessionFactory.getUserName(),
				SessionFactory.getPassword());
	}

	public void doSearch(String msNumber, String ruNumber, String seriesTitle, String accessionNumber, String boxNumber, BoxLookupReturnScreen returnSrceen) {

		seriesInfo = new TreeMap<String, SeriesInfo>();

		componentTitleLookup = new HashMap<Long, String>();

		logMessage = "";

		Collection<BoxLookupReturnRecords> results = new ArrayList<BoxLookupReturnRecords>();
//		Connection con = null;
//		String url;
//
//		try {
//			//Register the JDBC driver for MySQL.
//			Class.forName("com.mysql.jdbc.Driver");
//
//			//Define URL of database server for
//			// database named JunkDB on the localhost
//			// with the default port number 3306.
//			url = "jdbc:mysql://localhost:3306/yale";
//
//			//Get a connection to the database for a
//			// user named auser with the password
//			// drowssap, which is password spelled
//			// backwards.
//			con = DriverManager.getConnection(url,"root", "notlob");
//			//Display URL and connection information
//			System.out.println("URL: " + url);
//			System.out.println("Connection: " + con);
//
//		} catch (ClassNotFoundException e) {
//			new ErrorDialog("", e).showDialog();
//			UserPreferences.getInstance().saveToPreferences();
//			System.exit(0);
//		} catch (SQLException e) {
//			new ErrorDialog("", e).showDialog();
//			UserPreferences.getInstance().saveToPreferences();
//			System.exit(0);
//		}

		try {

			locationDAO = DomainAccessObjectFactory.getInstance().getDomainAccessObject(Locations.class);

			String sqlString ="SELECT Resources.resourceId\n" +
					"FROM Resources\n" +
					"WHERE resourceIdentifier1 = ? and resourceIdentifier2 = ?";
			PreparedStatement resourceIdLookup = con.prepareStatement(sqlString);

			sqlString ="SELECT ResourcesComponents.resourceComponentId, \n" +
					"\tResourcesComponents.resourceLevel, \n" +
					"\tResourcesComponents.title, \n" +
					"\tResourcesComponents.hasChild, \n" +
					"\tResourcesComponents.subdivisionIdentifier\n" +
					"FROM ResourcesComponents\n" +
					"WHERE ResourcesComponents.resourceId = ?";
			PreparedStatement componentLookupByResource = con.prepareStatement(sqlString);

			sqlString ="SELECT ResourcesComponents.resourceComponentId, \n" +
					"\tResourcesComponents.resourceLevel, \n" +
					"\tResourcesComponents.title, \n" +
					"\tResourcesComponents.hasChild\n" +
					"FROM ResourcesComponents\n" +
					"WHERE ResourcesComponents.parentResourceComponentId = ?";
			PreparedStatement componentLookupByComponent = con.prepareStatement(sqlString);


			sqlString ="SELECT *" +
					"FROM ArchDescriptionInstances\n" +
					"WHERE resourceComponentId in (?)";
			PreparedStatement instanceLookupByComponent = con.prepareStatement(sqlString);


//			String resourceIdString = JOptionPane.showInputDialog("Please enter a resource id");
//			SearchDialog searchDialog = new SearchDialog(ApplicationFrame.getInstance());
//
//			if (searchDialog.showDialog() == JOptionPane.OK_OPTION) {
				System.out.println("doing search");
//				String[] idParts = resourceIdString.split("\\.");
//				String resourceType = idParts[0];
				String resourceType = null;
				String resourceId2 = null;
				if (msNumber.length() != 0) {
					resourceType = "MS";
					resourceId2 = msNumber;
				} else if (ruNumber.length() != 0) {
					resourceType = "RU";
					resourceId2 = ruNumber;
				}
				resourceIdLookup.setString(1, resourceType);
				resourceIdLookup.setString(2, resourceId2);

				String uniqueId;
//				String seriesTitle = "";
				String hashKey;

				String targetUniqueId = "";
				if (seriesTitle.length() != 0) {
					targetUniqueId = seriesTitle;
				} else if (accessionNumber.length() != 0) {
					targetUniqueId = accessionNumber;
				}

				MyTimer timer = new MyTimer();
				timer.reset();
				ResultSet rs = resourceIdLookup.executeQuery();
				if (rs.next()) {
					Long resourceId = rs.getLong(1);
					System.out.println("Resource ID: " + resourceId);
					componentLookupByResource.setLong(1, resourceId);
					ResultSet components = componentLookupByResource.executeQuery();

					while (components.next()) {
						uniqueId = determineComponentUniqueIdentifier(resourceType, components.getString("subdivisionIdentifier"), components.getString("title"));

						hashKey = uniqueId + seriesTitle;
						if (!seriesInfo.containsKey(hashKey)) {
							seriesInfo.put(hashKey, new SeriesInfo(uniqueId, seriesTitle));
						}

						if (targetUniqueId.equals("") || uniqueId.equalsIgnoreCase(targetUniqueId)) {
							recurseThroughComponents(components.getLong("resourceComponentId"),
								hashKey,
								components.getBoolean("hasChild"),
								componentLookupByComponent,
								instanceLookupByComponent);
						}
					}

					ResultSet instances;
					TreeMap<String, ContainerInfo> containers;
					String containerLabel;
					Long componentId;
					String componentTitle;
					Double container1NumIndicator;
					String container1AlphaIndicator;

					for (SeriesInfo series: seriesInfo.values()) {
//						instanceLookupByComponent.setString(1, series.getComponentIds());
//						System.out.println("Component Ids: " + series.getComponentIds());
						sqlString ="SELECT * " +
								"FROM ArchDescriptionInstances\n" +
								"WHERE resourceComponentId in (" + series.getComponentIds() + ")";
						System.out.println(sqlString);
						Statement sqlStatement = con.createStatement();
						instances = sqlStatement.executeQuery(sqlString);
						containers = new TreeMap<String, ContainerInfo>();
						while (instances.next()) {

							container1NumIndicator = instances.getDouble("container1NumericIndicator");
							container1AlphaIndicator = instances.getString("container1AlphaNumIndicator");
							if (boxNumber.equals("") || boxNumber.equalsIgnoreCase(determineBoxNumber(container1NumIndicator, container1AlphaIndicator))) {
								containerLabel = createContainerLabel(instances.getString("container1Type"),
										container1NumIndicator,
										container1AlphaIndicator,
										instances.getString("instanceType"));
								componentId = instances.getLong("resourceComponentId");
								componentTitle = componentTitleLookup.get(componentId);
								if (!containers.containsKey(containerLabel)) {
									containers.put(containerLabel, new ContainerInfo(containerLabel,
											instances.getString("barcode"),
											instances.getBoolean("userDefinedBoolean1"),
											getLocationString(instances.getLong("locationId")),
											componentTitle,
											instances.getString("userDefinedString2")));
								}
							}
						}
						logMessage += "\n\n";
						for (ContainerInfo container: containers.values()) {
							results.add(new BoxLookupReturnRecords(createPaddedResourceIdentifier(resourceType ,resourceId2),
									series.getUniqueId(),
									container.getComponentTitle(),
									container.getLocation(),
									container.getBarcode(),
									container.isRestriction(),
									container.getLabel(),
									container.getContainerType()));
							logMessage += "\nAccession Number: " + series.getUniqueId() +
									" Sereis Title: " + series.getSeriesTitle() +
									" Container: " + container.getLabel() +
									" Barcode: " + container.getBarcode() +
									" Restrictions: " + container.isRestriction();

						}
//					}

//					sqlString ="SELECT * " +
//							"FROM ArchDescriptionInstances\n" +
//							"WHERE resourceComponentId in (" + leafComponentIds + ")";
//					System.out.println(sqlString);
//					Statement sqlStatement = con.createStatement();
//					rs = sqlStatement.executeQuery(sqlString);
//					while (rs.next()) {
////						System.out.println(rs.getLong("archDescriptionInstancesId"));
//					}
//					final JTextArea textArea = new JTextArea();
//					textArea.setText("Elapsed Time: " +
//							MyTimer.toString(timer.elapsedTimeMillis()) + "\n\n" + logMessage);
//					JScrollPane scrollPane = new JScrollPane(textArea);
//					scrollPane.setPreferredSize(new Dimension(1000, 400));
//					JOptionPane.showMessageDialog(ApplicationFrame.getInstance(), scrollPane);

//					BoxLookupReturnScreen returnScreen = new BoxLookupReturnScreen(ApplicationFrame.getInstance());
					returnSrceen.updateResultList(results);
					returnSrceen.setElapsedTimeText(MyTimer.toString(timer.elapsedTimeMillis()));
//					returnSrceen.showDialog();
//					return MyTimer.toString(timer.elapsedTimeMillis());
				}


			}
		} catch (SQLException e) {
			new ErrorDialog("", e).showDialog();
		} catch (PersistenceException e) {
			new ErrorDialog("", e).showDialog();
		} catch (LookupException e) {
			new ErrorDialog("", e).showDialog();
		}

	}


	/**
	 * Method to get the indicator for container 1
	 * @return Either the Numeric or AlphaNumeric Indicator
	 */
	public String determineBoxNumber(Double container1NumericIndicator, String container1AlphaNumericIndicator) {
		if(container1NumericIndicator != null && container1NumericIndicator != 0) {
			return StringHelper.handleDecimal(container1NumericIndicator.toString());
		} else if (container1AlphaNumericIndicator != null && StringHelper.isNotEmpty(container1AlphaNumericIndicator)) {
			return container1AlphaNumericIndicator;
		} else {
			return "";
		}
	}

	private String createPaddedResourceIdentifier(String id1, String id2) {
		String paddedId2;
		if (id2.length() == 1) {
			paddedId2 = "000" + id2;
		} else if (id2.length() == 2) {
			paddedId2 = "00" + id2;
		} else if (id2.length() == 3) {
			paddedId2 = "0" + id2;
		} else {
			paddedId2 = id2;
		}
		return id1 + paddedId2;
	}



	private String determineComponentUniqueIdentifier(String resourceType, String componenetUniqueId, String seriesTitle) throws SQLException {
		String accessionNumber;
		if (componenetUniqueId != null && componenetUniqueId.length() != 0) {
			return componenetUniqueId.replace("Accession ", "");
		} else if (resourceType.equalsIgnoreCase("ms")) {
			return seriesTitle.replace("Accession ", "");
		} else {
			return "";
		}

//		if (resourceType.equalsIgnoreCase("ms")) {
//			if (components.getString("title").startsWith("Accession"))  {
//				uniqueId = components.getString("title").replace("Accession ", "");
//			} else {
//				uniqueId = "";
//			}
//		} else {
//			if (components.getString("subdivisionIdentifier").startsWith("Accession"))  {
//				uniqueId = components.getString("subdivisionIdentifier").replace("Accession ", "");
//			} else {
//				uniqueId = "";
//			}
//		}
//		return uniqueId;
	}

	private String getLocationString(Long locationId) throws LookupException {
		Locations location = (Locations)locationDAO.findByPrimaryKey(locationId);
		if (location != null) {			
			return location.toString();
		} else {
			return "";
		}
	}

	private void recurseThroughComponents(Long componentID,
										  String hashKey,
										  Boolean hasChild,
										  PreparedStatement componentLookup,
										  PreparedStatement instanceLookup) throws SQLException {
//		System.out.println("Component ID: " + componentID + " Level: " + level + " Title: " + title + " Has child: " + hasChild);
		if (hasChild) {
			componentLookup.setLong(1, componentID);
			ResultSet components = componentLookup.executeQuery();
			ArrayList<ComponentInfo> componentList = new ArrayList<ComponentInfo>();
			while (components.next()) {
				componentList.add(new ComponentInfo(components.getLong("resourceComponentId"),
						components.getString("resourceLevel"),
						components.getString("title"),
						components.getBoolean("hasChild")));
				componentTitleLookup.put(components.getLong("resourceComponentId"), components.getString("title"));
			}

			if (componentList.size() > 0) {
				for (ComponentInfo component: componentList) {
					recurseThroughComponents(component.getComponentId(), hashKey, component.isHasChild(), componentLookup, instanceLookup);
				}
			} else {
				//this is a hack because the has child flag for components may be set wrong
				SeriesInfo series = seriesInfo.get(hashKey);
				series.addComponentId(componentID);
			}
		} else {

			SeriesInfo series = seriesInfo.get(hashKey);
			series.addComponentId(componentID);
//			instanceLookup.setLong(1, componentID);
//			ResultSet instances = instanceLookup.executeQuery();
//			while (instances.next()) {
////				System.out.println("Instance ID: " + instances.getLong("archDescriptionInstancesId") + " Instance Type: " + instances.getString("instanceType"));
//				SeriesInfo series = seriesInfo.get(hashKey);
//				series.addComponentId(componentID);
//			}
		}
//		//make sure you pick up any instances at this level whether there are children or not
//				SeriesInfo series = seriesInfo.get(hashKey);
//				series.addComponentId(componentID);
		
	}

	private String createContainerLabel(String type, Double numericIndicator, String alphaIndicator, String instanceType) {

		Boolean hasNumbericIndicator;
		if (instanceType.equalsIgnoreCase("Digital Object")) {
			return "Digital Object(s)";
		} else {
			if (numericIndicator != null && numericIndicator != 0.0) {
				hasNumbericIndicator = true;
			} else {
				hasNumbericIndicator = false;
			}
			if (type != null && type.length() > 0 && (hasNumbericIndicator || alphaIndicator.length() > 0)) {
				try {
					if (hasNumbericIndicator) {
						return type + " " + NumberFormat.getInstance().format(numericIndicator);
					} else {
						return type + " " + alphaIndicator;
					}
				} catch (Exception e) {
					new ErrorDialog("Error creating container label", e).showDialog();
					return "no container";
				}
			} else {
				return "no container";
			}
		}
	}

	private class ContainerInfo {

		private String label;
		private String barcode;
		private Boolean restriction;
		private String location;
		private String componentTitle;
		private String containerType;

		private ContainerInfo(String label, String barcode, Boolean restriction, String location, String componentTitle, String containerType) {
			this.label = label;
			if (barcode == null || barcode.equals("0.0")) {
				this.barcode = "";
			} else {
				this.barcode = barcode;
			}
//			this.barcode = barcode;
			this.restriction = restriction;
			this.location = location;
			this.componentTitle = componentTitle;
			this.containerType = containerType;
		}

		public String getLabel() {
			return label;
		}

		public String getBarcode() {
			return barcode;
		}

		public Boolean isRestriction() {
			return restriction;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getComponentTitle() {
			return componentTitle;
		}

		public void setComponentTitle(String componentTitle) {
			this.componentTitle = componentTitle;
		}

		public String getContainerType() {
			return containerType;
		}

		public void setContainerType(String containerType) {
			this.containerType = containerType;
		}
	}

	private class SeriesInfo {

		private String uniqueId;
		private String seriesTitle;
		private String componentIds = null;

		private SeriesInfo(String uniqueId, String seriesTitle) {
			this.uniqueId = uniqueId;
			this.seriesTitle = seriesTitle;
		}


		public String getUniqueId() {
			return uniqueId;
		}

		public void setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
		}

		public String getSeriesTitle() {
			return seriesTitle;
		}

		public void setSeriesTitle(String seriesTitle) {
			this.seriesTitle = seriesTitle;
		}

		public String getComponentIds() {
			return componentIds;
		}

		public void addComponentId(Long componentId) {
			if (this.componentIds == null){
				this.componentIds = componentId.toString();
			} else {
				this.componentIds += ", " + componentId;
			}
		}
	}

	private class ComponentInfo {

		private Long componentId;
		private String resourceLevel;
		private String title;
		private Boolean hasChild;

		private ComponentInfo(Long componentId, String resourceLevel, String title, Boolean hasChild) {
			this.componentId = componentId;
			this.resourceLevel = resourceLevel;
			this.title = title;
			this.hasChild = hasChild;
		}

		public Long getComponentId() {
			return componentId;
		}

		public void setComponentId(Long componentId) {
			this.componentId = componentId;
		}

		public String getResourceLevel() {
			return resourceLevel;
		}

		public void setResourceLevel(String resourceLevel) {
			this.resourceLevel = resourceLevel;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Boolean isHasChild() {
			return hasChild;
		}

		public void setHasChild(Boolean hasChild) {
			this.hasChild = hasChild;
		}
	}

}
