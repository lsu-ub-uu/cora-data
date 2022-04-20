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
package se.uu.ub.cora.data.ability;

import java.util.Collection;
import java.util.List;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataMissingException;

/**
 * DataParent is a container for related DataElements, i.e. it has children.
 */
public interface DataParent {

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
	 *            A String with the nameInData of the child
	 * @return A boolean, true if a child exists with the specified name, else false.
	 */
	boolean containsChildWithNameInData(String nameInData);

	/**
	 * addChild adds a {@link DataChild} to this DataGroup
	 * 
	 * @param dataElement
	 *            to add to this DataGroup
	 */
	void addChild(DataChild dataChild);

	/**
	 * addChildren is used to add the entered dataElements as children into the current dataGroup.
	 * If the entered collection of dataElements is empty should no children be added.
	 * 
	 * @param dataChildren
	 *            to add as children
	 */
	void addChildren(Collection<DataChild> dataChildren);

	/**
	 * getChildren is used to get a List with all the children of this {@link DataGroup}
	 * 
	 * @return A List of {@link DataChild} with all the children of this datagroup.
	 */
	List<DataChild> getChildren();

	/**
	 * getAllChildrenWithNameInData is used to get all children that matches the specified
	 * nameInData as DataElements.
	 * <p>
	 * An empty list SHOULD be returned if no child exists with the specified nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @return A List with all children that has the specified nameInData
	 */
	List<DataChild> getAllChildrenWithNameInData(String nameInData);

	/**
	 * getAllChildrenWithNameInDataAndAttributes is used to get all children that matches the
	 * specified nameInData and the specified attributes, as DataElements.
	 * <p>
	 * An empty list SHOULD be returned if no child exists with the specified nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @param childAttributes
	 *            A Varargs with attributes the children must have to be returned
	 * @return A List with all children that has the specified nameInData
	 */
	List<DataChild> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * getFirstChildWithNameInData is used to get the first {@link DataChild} that matches the
	 * specified nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no child exists with the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to get
	 * @return The first {@link DataChild} that matches the specified nameInData.
	 */
	DataChild getFirstChildWithNameInData(String nameInData);

	/**
	 * getFirstAtomicValueWithNameInData is used to get the value of the first {@link DataAtomic}
	 * that matches the specified nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no child exists with the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to get
	 * @return String value of the atomic that matches the specified nameInData.
	 */
	String getFirstAtomicValueWithNameInData(String nameInData);

	/**
	 * getFirstDataAtomicWithNameInData returns the first DataAtomic child with the specified
	 * nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no child exists with the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to get
	 * @return The first {@link DataAtomic} that matches nameInData.
	 */
	DataAtomic getFirstDataAtomicWithNameInData(String nameInData);

	/**
	 * getAllDataAtomicsWithNameInData is used to get a List of the all {@link DataAtomic} that
	 * matches the specified nameInData.
	 * <p>
	 * If no matching elements are found SHOULD an empty list be returned.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @return A List of {@link DataAtomic} that matches the specified nameInData.
	 */
	List<DataAtomic> getAllDataAtomicsWithNameInData(String nameInData);

	/**
	 * getAllDataAtomicsWithNameInDataAndAttributes is used to get a Collection of the all
	 * {@link DataAtomic} that matches the specified nameInData and the specified attributes.
	 * <p>
	 * If no matching elements are found SHOULD an empty collection be returned.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @param childAttributes
	 *            A Varargs with attributes the children must have to be returned
	 * @return A Collection of {@link DataAtomic} that matches the specified nameInData and the
	 *         specified attributes.
	 */
	Collection<DataAtomic> getAllDataAtomicsWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * getFirstGroupWithNameInData returns the first DataGroup child with the specified nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no child exists with the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to get
	 */
	DataGroup getFirstGroupWithNameInData(String nameInData);

	/**
	 * getAllGroupsWithNameInData is used to get a List of the all {@link DataGroup} that matches
	 * the specified nameInData.
	 * <p>
	 * If no matching elements are found SHOULD an empty list be returned.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @return A List of {@link DataGroup} that matches the specified nameInData.
	 */
	List<DataGroup> getAllGroupsWithNameInData(String nameInData);

	/**
	 * getAllGroupsWithNameInData is used to get a List of the all {@link DataGroup} that matches
	 * the specified nameInData and the specified attributes.
	 * <p>
	 * If no matching elements are found SHOULD an empty collection be returned.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to get
	 * @param childAttributes
	 *            A Varargs with attributes the children must have to be returned
	 * @return A Collection of {@link DataGroup} that matches the specified nameInData and
	 *         attributes list.
	 */
	Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * removeFirstChildWithNameInData removes the first child in this DataGroup that has the
	 * specified nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to remove
	 * @return true if a child was removed, false otherwise
	 */
	boolean removeFirstChildWithNameInData(String nameInData);

	/**
	 * removeAllChildrenWithNameInData removes all children in this DataGroup that has the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the children to remove
	 * @return true if any child has been removed, false otherwise
	 */
	boolean removeAllChildrenWithNameInData(String nameInData);

	/**
	 * removeAllChildrenWithNameInDataAndAttributes removes all children in this DataGroup that has
	 * the specified nameInData and the specified attributes.
	 * 
	 * @param nameInData
	 *            A String with the nameInData of the child to remove
	 * @param childAttributes
	 *            A Varargs with attributes the children must have to be returned
	 * @return true if any child has been removed, false otherwise
	 */
	boolean removeAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

}
