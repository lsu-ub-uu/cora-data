/*
 * Copyright 2019 Uppsala University Library
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

public interface DataGroup extends DataElement, DataPart {

	@Override
	String getNameInData();

	String getFirstAtomicValueWithNameInData(String nameInData);

	DataGroup getFirstGroupWithNameInData(String childNameInData);

	void addChild(DataElement dataElement);

	List<DataElement> getChildren();

	boolean containsChildWithNameInData(String nameInData);

	void setRepeatId(String repeatId);

	void addAttributeByIdWithValue(String id, String value);

	DataElement getFirstChildWithNameInData(String nameInData);

	List<DataGroup> getAllGroupsWithNameInData(String nameInData);

	String getAttribute(String attributeId);

	List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData);

	void removeFirstChildWithNameInData(String childNameInData);

	Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes);

	@Override
	Map<String, String> getAttributes();
}
