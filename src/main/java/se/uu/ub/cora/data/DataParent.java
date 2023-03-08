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
	 * @deprecated Use {@link #getAllChildrenMatchingFilter(DataChildFilter)} instead.
	 */
	@Deprecated
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
	 * @deprecated Use {@link #removeAllChildrenMatchingFilter(DataChildFilter)} instead.
	 */
	@Deprecated
	boolean removeAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes);

	/**
	 * getAllChildrenMatchingFilter is used to get all children that matches the specified
	 * childFilter.
	 * </p>
	 * See, {@link DataChildFilter#childMatches(DataChild)} for exactly how a child is considered a
	 * match.
	 * <p>
	 * An empty list SHOULD be returned if no child exists with the specified nameInData.
	 * 
	 * @param childFilter
	 *            A DataChildFilter to filter the children to return
	 * @return A List with all children that matches the specified childFilter
	 */
	List<DataChild> getAllChildrenMatchingFilter(DataChildFilter childFilter);

	/**
	 * removeAllChildrenMatchingFilter removes all children in this DataGroup that matches the
	 * specified childFilter.
	 * </p>
	 * See, {@link DataChildFilter#childMatches(DataChild)} for exactly how a child is considered a
	 * match.
	 * 
	 * @param childFilter
	 *            A DataChildFilter to filter the children to remove
	 * @return true if any child has been removed, false otherwise
	 */
	boolean removeAllChildrenMatchingFilter(DataChildFilter childFilter);

	/**
	 * containsChildOfTypeWithNameAndAttributes checks if this DataParent has at least one child
	 * with the specified name class, nameInData and attributes.
	 * 
	 * @param <T>
	 *            Automatically set to the requested type of class
	 * @param type
	 *            A Class that the child must have to be found. Must be {@link DataChild} or a class
	 *            that extends it.
	 * @param name
	 *            A String with the nameInData of the child to find
	 * @param attributes
	 *            A Varargs with attributes the child must have to be found
	 * @return A boolean, true if a child exists with the specified name, else false.
	 */

	<T> boolean containsChildOfTypeWithNameAndAttributes(Class<T> type, String name,
			DataAttribute... attributes);

	/**
	 * getFirstChildOfTypeWithNameAndAttributes is used to get the first {@link DataChild} that
	 * matches the specified class, nameInData and attributes. The returned list is typed to the
	 * same class that is requested.
	 * 
	 * @param <T>
	 *            Automatically set to the requested type of class
	 * @param type
	 *            A Class that the child must have to be returned. Must be {@link DataChild} or a
	 *            class that extends it.
	 * @param name
	 *            A String with the nameInData of the child to get
	 * @param attributes
	 *            A Varargs with attributes the child must have to be returned
	 * @return
	 */
	<T extends DataChild> T getFirstChildOfTypeWithNameAndAttributes(Class<T> type, String name,
			DataAttribute... attributes);

	/**
	 * getChildrenOfTypeWithNameAndAttributes is used to get a List of the all {@link DataChild}s
	 * that matches the specified class, nameInData and attributes. The returned list is typed to
	 * the same class that is requested.
	 * 
	 * @param <T>
	 *            Automatically set to the requested type of class
	 * @param type
	 *            A Class that the children must have to be returned. Must be {@link DataChild} or a
	 *            class that extends it.
	 * @param name
	 *            A String with the nameInData of the children to get
	 * @param attributes
	 *            A Varargs with attributes the children must have to be returned
	 * @return
	 */
	<T extends DataChild> List<T> getChildrenOfTypeWithNameAndAttributes(Class<T> type, String name,
			DataAttribute... attributes);

	/**
	 * removeFirstChildWithTypeNameAndAttributes is used to remove the first {@link DataChild}s that
	 * matches the specified class, nameInData and attributes.
	 * 
	 * @param <T>
	 *            Automatically set to the type of class to remove
	 * @param type
	 *            A Class that the child must have to be removed. Must be {@link DataChild} or a
	 *            class that extends it.
	 * @param name
	 *            A String with the nameInData of the child to remove
	 * @param attributes
	 *            A Varargs with attributes the child must have to be removed
	 * @return true if any child has been removed, false otherwise
	 */
	<T> boolean removeFirstChildWithTypeNameAndAttributes(Class<T> type, String name,
			DataAttribute... attributes);

	/**
	 * removeChildrenWithTypeNameAndAttributes is used to remove all {@link DataChild}s that matches
	 * the specified class, nameInData and attributes.
	 * 
	 * @param <T>
	 *            Automatically set to the type of class to remove
	 * @param type
	 *            A Class that the children must have to be removed. Must be {@link DataChild} or a
	 *            class that extends it.
	 * @param name
	 *            A String with the nameInData of the children to remove
	 * @param attributes
	 *            A Varargs with attributes the children must have to be removed
	 * @return true if any child has been removed, false otherwise
	 */
	<T> boolean removeChildrenWithTypeNameAndAttributes(Class<T> type, String name,
			DataAttribute... attributes);

}
