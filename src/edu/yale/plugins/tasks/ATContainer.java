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
 * Time: 10:48:38 AM
 */

package edu.yale.plugins.tasks;

public class ATContainer {

	private String containerLabel;
	private String accessionNumber;
	private String barcode;

	public ATContainer(String containerLabel, String accessionNumber, String barcode) {
		this.containerLabel = containerLabel;
		this.accessionNumber = accessionNumber;
		this.barcode = barcode;
	}

	public String getContainerLabel() {
		return containerLabel;
	}

	public void setContainerLabel(String containerLabel) {
		this.containerLabel = containerLabel;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
