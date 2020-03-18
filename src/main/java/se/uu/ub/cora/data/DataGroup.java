/*
 * Copyright 2019, 2020 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DataGroup extends DataElement, Data {

	void addAttributeByIdWithValue(String id, String value);

	String getAttribute(String attributeId);

	Map<String, String> getAttributes();

	boolean containsChildWithNameInData(String nameInData);

	void addChild(DataElement dataElement);

	List<DataElement> getChildren();

	DataElement getFirstChildWithNameInData(String nameInData);

	String getFirstAtomicValueWithNameInData(String nameInData);

	List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData);

	DataGroup getFirstGroupWithNameInData(String childNameInData);

	List<DataGroup> getAllGroupsWithNameInData(String nameInData);

	Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes);

	/**
	 * removeFirstChildWithNameInData, removes the first child in this DataGroup that has the
	 * specified nameInData. A {@link DataMissingException} SHOULD be thrown if no child exists with
	 * the specified nameInData.
	 */
	void removeFirstChildWithNameInData(String childNameInData);

	/**
	 * removeAllChildrenWithNameInData, removes all children in this DataGroup that has the
	 * specified nameInData. A {@link DataMissingException} SHOULD be thrown if no child exists with
	 * the specified nameInData.
	 */
	void removeAllChildrenWithNameInData(String childNameInData);

}
