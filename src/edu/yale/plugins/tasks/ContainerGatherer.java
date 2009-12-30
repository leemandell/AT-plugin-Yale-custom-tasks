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
 * Date: Sep 15, 2009
 * Time: 10:44:54 AM
 */

package edu.yale.plugins.tasks;

import org.archiviststoolkit.model.*;
import org.archiviststoolkit.swing.InfiniteProgressPanel;
import org.archiviststoolkit.mydomain.DomainAccessObject;
import org.archiviststoolkit.mydomain.DomainAccessObjectFactory;
import org.archiviststoolkit.mydomain.PersistenceException;
import org.archiviststoolkit.mydomain.LookupException;
import org.archiviststoolkit.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ContainerGatherer {

	private Resources resource;
	private String voyagerHoldingsKey = null;
	private HashMap<String, Date> accessions;
	private DomainAccessObject accessionDAO;

	public ContainerGatherer(Resources resource) {
		this.resource = resource;
	}

	public Collection<ATContainer> gatherContainers(InfiniteProgressPanel monitor) throws PersistenceException, LookupException {

		HashMap<String, ATContainer> containers = new HashMap<String, ATContainer>();
		accessions = new HashMap<String, Date>();
		String accessionNumber;
		accessionDAO = DomainAccessObjectFactory.getInstance().getDomainAccessObject(Accessions.class);

		for (ResourcesComponents component: resource.getResourcesComponents()) {
			accessionNumber = component.getComponentUniqueIdentifier().replace("Accession ", "");
			if (!accessions.containsKey(accessionNumber)) {
				accessions.put(accessionNumber, lookupAccessionDate(accessionNumber));
			}
			gatherContainers(monitor, containers, component, accessionNumber, 2);
		}

		return containers.values();
	}

	private void gatherContainers(InfiniteProgressPanel monitor,
								  HashMap<String, ATContainer> containers,
								  ResourcesComponents component,
								  String accessionNumber,
								  int depth) {

		String key;
		ArchDescriptionAnalogInstances instance;
		String instanceLabel;
		monitor.setTextLine(component.getTitle(), depth);
		System.out.println("Component: " + component.getTitle() + " has child: " + component.isHasChild());
 
		for (Object o : component.getInstances(ArchDescriptionAnalogInstances.class)) {
			instance = (ArchDescriptionAnalogInstances) o;
			instanceLabel = instance.getTopLevelLabel();
			key = accessionNumber + instanceLabel;
			System.out.println("Key: " + key);
			if (!containers.containsKey(key)) {
				containers.put(key, new ATContainer(instanceLabel, accessionNumber, instance.getBarcode()));
				if (voyagerHoldingsKey == null) {
					String[] parts = instance.getUserDefinedString1().split("_");
					voyagerHoldingsKey = parts[1];
				}
			}

		}

		if (component.isHasChild()) {
			for (ResourcesComponents childComponent: component.getResourcesComponents()) {
				gatherContainers(monitor, containers, childComponent, accessionNumber, depth++);
			}
		}

	}

	private Date lookupAccessionDate(String accessionNumber) {

		String fixedAccessionNumber = accessionNumber.replace("-", ".");
		System.out.println(fixedAccessionNumber);
		String[] accessionParts = fixedAccessionNumber.split("\\.");
		String accessionNumber1, accessionNumber2, accessionNumber3, accessionNumber4;
		System.out.println(accessionParts[0] + " " + accessionParts[1] + " " + accessionParts[2]);
		if (accessionParts.length == 4) {
			System.out.println("4 parts");
			accessionNumber1 = accessionParts[0];
			accessionNumber2 = accessionParts[1];
			accessionNumber3 = accessionParts[2];
			accessionNumber4 = accessionParts[3];
		} else if (accessionParts.length == 3) {
			System.out.println("3 parts");
			accessionNumber1 = accessionParts[0];
			accessionNumber2 = accessionParts[1];
			accessionNumber3 = accessionParts[2];
			accessionNumber4 = "";
		} else if (accessionParts.length == 2) {
			System.out.println("2 parts");
			accessionNumber1 = accessionParts[0];
			accessionNumber2 = accessionParts[1];
			accessionNumber3 = "";
			accessionNumber4 = "";
		} else if (accessionParts.length == 1) {
			System.out.println("1 parts");
			accessionNumber1 = accessionParts[0];
			accessionNumber2 = "";
			accessionNumber3 = "";
			accessionNumber4 = "";
		} else  {
			System.out.println("0 parts");
			accessionNumber1 = "";
			accessionNumber2 = "";
			accessionNumber3 = "";
			accessionNumber4 = "";
		}

		Session session = SessionFactory.getInstance().openSession();
		Criteria criteria = session.createCriteria(Accessions.class);
		System.out.println(criteria.toString());
		if (accessionNumber1.length() > 0) {
			criteria.add(Expression.eq(Accessions.PROPERTYNAME_ACCESSION_NUMBER_1, accessionNumber1));
		}
		if (accessionNumber2.length() > 0) {
			criteria.add(Expression.eq(Accessions.PROPERTYNAME_ACCESSION_NUMBER_2, accessionNumber2));
		}
		if (accessionNumber3.length() > 0) {
			criteria.add(Expression.eq(Accessions.PROPERTYNAME_ACCESSION_NUMBER_3, accessionNumber3));
		}
		if (accessionNumber4.length() > 0) {
			criteria.add(Expression.eq(Accessions.PROPERTYNAME_ACCESSION_NUMBER_4, accessionNumber4));
		}
		Accessions accession = (Accessions) criteria.uniqueResult();
		session.close();


//		Accessions accession = (Accessions)accessionDAO.findByUniquePropertyValue(Accessions.PROPERTYNAME_ACCESSION_NUMBER, fixedAccessionNumber);
		return accession.getAccessionDate();
	}

	public String getVoyagerHoldingsKey() {
		return voyagerHoldingsKey;
	}

	public void setVoyagerHoldingsKey(String voyagerHoldingsKey) {
		this.voyagerHoldingsKey = voyagerHoldingsKey;
	}

	public String lookupAccessionDateFromHashmap(String accessionNumber) {
		Date accessionDate = accessions.get(accessionNumber);
		if (accessionDate == null) {
			return "no accession date";
		} else {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			return dateFormatter.format(accessionDate);
		}
	}
}
