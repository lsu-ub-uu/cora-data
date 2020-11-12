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

public interface DataGroup extends DataElement, Data {
	/**
	 * hasChildren checks if this DataGroup has at least one child or not
	 * 
	 * @return A boolean, true if at least one child exists, else false
	 */
	boolean hasChildren();

	/**
	 * containsChildWithNameInData checks if this DataGroup has at least one child with the
	 * specified name or not.
	 * 
	 * @param nameInData
	 *            A String with the child name
	 * @return A boolean, true if a child exists with the specified name, else false.
	 */
	boolean containsChildWithNameInData(String nameInData);

	void addChild(DataElement dataElement);

	/**
	 * addChildren is used to add the entered dataElements as children into the current dataGroup.
	 * If the entered collection of dataElements is empty should no children be added.
	 * 
	 * @param dataElements
	 *            to add as children
	 */
	void addChildren(Collection<DataElement> dataElements);

	List<DataElement> getChildren();

	/**
	 * getAllChildrenWithNameInData is used to get all children that matches the specified
	 * nameInData as DataElements.<br>
	 * <br>
	 * An empty list SHOULD be returned if no child exists with the specified nameInData.
	 * 
	 * @param nameInData
	 *            to get children by
	 * @return A List with all children that has the specified nameInData
	 */
	List<DataElement> getAllChildrenWithNameInData(String nameInData);

	/**
	 * getAllChildrenWithNameInDataAndAttributes is used to get all children that matches the
	 * specified nameInData and the specified attributes, as DataElements.<br>
	 * <br>
	 * An empty list SHOULD be returned if no child exists with the specified nameInData.
	 * 
	 * @param nameInData
	 *            to get children by
	 * @param childAttributes
	 *            to get children by
	 * 
	 * @return A List with all children that has the specified nameInData
	 */
	List<DataElement> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	DataElement getFirstChildWithNameInData(String nameInData);

	String getFirstAtomicValueWithNameInData(String nameInData);

	List<DataAtomic> getAllDataAtomicsWithNameInData(String nameInData);

	DataGroup getFirstGroupWithNameInData(String nameInData);

	List<DataGroup> getAllGroupsWithNameInData(String nameInData);

	Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * removeFirstChildWithNameInData removes the first child in this DataGroup that has the
	 * specified nameInData. <br>
	 * <br>
	 * 
	 * @return true if a child was removed, false otherwise
	 */
	boolean removeFirstChildWithNameInData(String nameInData);

	/**
	 * removeAllChildrenWithNameInData removes all children in this DataGroup that has the specified
	 * nameInData.<br>
	 * <br>
	 * 
	 * @return true if any child has been removed, false otherwise
	 */
	boolean removeAllChildrenWithNameInData(String nameInData);

	/**
	 * removeAllChildrenWithNameInDataAndAttributes removes all children in this DataGroup that has
	 * the specified nameInData and the specified attributes.<br>
	 * <br>
	 * 
	 * @param nameInData
	 *            to remove children by
	 * 
	 * @param DataAttribute
	 *            to remove children by
	 * 
	 * @return true if any child has been removed, false otherwise
	 */
	boolean removeAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * getFirstDataAtomicWithNameInData returns the first DataAtomic child with the specified
	 * nameInData.<br>
	 * <br>
	 * A {@link DataMissingException} SHOULD be thrown if no child exists with the specified
	 * nameInData.
	 */
	DataAtomic getFirstDataAtomicWithNameInData(String nameInData);

}
