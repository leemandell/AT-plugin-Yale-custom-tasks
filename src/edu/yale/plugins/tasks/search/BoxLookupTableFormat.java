package edu.yale.plugins.tasks.search;

import ca.odell.glazedlists.gui.WritableTableFormat;
import ca.odell.glazedlists.gui.AdvancedTableFormat;
import ca.odell.glazedlists.GlazedLists;

import java.util.Comparator;

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
 * Date: Jun 8, 2006
 * Time: 9:15:11 PM
 */
public class BoxLookupTableFormat implements WritableTableFormat<BoxLookupReturnRecords>, AdvancedTableFormat<BoxLookupReturnRecords> {


	public int getColumnCount() {
		return 8;
	}

	public String getColumnName(int column) {
		if(column == 0)      return "Collection ID";
		else if(column == 1) return "ACCN/Series";
		else if(column == 2) return "Title";
		else if(column == 3) return "Location";
		else if(column == 4) return "Box";
		else if(column == 5) return "Container Type";
		else if(column == 6) return "Barcode";
		else if(column == 7) return "Restriction";

		throw new IllegalStateException();
	}

	public Object getColumnValue(BoxLookupReturnRecords boxLookupReturnRecords, int column) {
		if(column == 0)      return boxLookupReturnRecords.getCollectionId();
		else if(column == 1) return boxLookupReturnRecords.getUniqueId();
		else if(column == 2) return boxLookupReturnRecords.getTitle();
		else if(column == 3) return boxLookupReturnRecords.getLocation();
		else if(column == 4) return boxLookupReturnRecords.getBoxLabel();
		else if(column == 5) return boxLookupReturnRecords.getContainerType();
		else if(column == 6) return boxLookupReturnRecords.getBarcode();
		else if(column == 7) return boxLookupReturnRecords.isRestriction();

		throw new IllegalStateException();
	}

	public boolean isEditable(BoxLookupReturnRecords boxLookupReturnRecords, int i) {
		if (i == 6) {
			return true;
		} else {
			return false;
		}
	}

	public BoxLookupReturnRecords setColumnValue(BoxLookupReturnRecords boxLookupReturnRecords, Object o, int i) {
		return null;
	}

	public Class getColumnClass(int column) {
		if(column == 0)      return String.class;
		else if(column == 1) return String.class;
		else if(column == 2) return String.class;
		else if(column == 3) return String.class;
		else if(column == 4) return String.class;
		else if(column == 5) return String.class;
		else if(column == 6) return String.class;
		else if(column == 7) return Boolean.class;

		throw new IllegalStateException();
	}

	public Comparator getColumnComparator(int i) {
		return GlazedLists.comparableComparator();
	}
}