/*
 * Copyright 2015, 2019 Uppsala University Library
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

public class DataGroupTest {
	@Test
	public void testInit() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		assertEquals(dataGroup.getNameInData(), "nameInData");
		assertNotNull(dataGroup.getAttributes());
		assertNotNull(dataGroup.getChildren());
	}

	@Test
	public void testGroupIsData() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		assertTrue(dataGroup instanceof Data);
	}

	@Test
	public void testGroupAsLink() {
		DataGroup dataGroup = DataGroup.asLinkWithNameInDataAndTypeAndId("nameInData", "someType",
				"someId");
		assertEquals(dataGroup.getNameInData(), "nameInData");
		assertEquals(dataGroup.getFirstAtomicValueWithNameInData("linkedRecordType"), "someType");
		assertEquals(dataGroup.getFirstAtomicValueWithNameInData("linkedRecordId"), "someId");
	}

	@Test
	public void testInitWithRepeatId() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.setRepeatId("hrumph");
		assertEquals(dataGroup.getNameInData(), "nameInData");
		assertNotNull(dataGroup.getAttributes());
		assertNotNull(dataGroup.getChildren());
		assertEquals(dataGroup.getRepeatId(), "hrumph");
	}

	@Test
	public void testAddAttribute() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addAttributeByIdWithValue("nameInData", "value");
		Map<String, String> attributes = dataGroup.getAttributes();
		Entry<String, String> entry = attributes.entrySet().iterator().next();
		assertEquals(entry.getKey(), "nameInData");
		assertEquals(entry.getValue(), "value");
	}

	@Test
	public void testGetAttribute() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addAttributeByIdWithValue("attributeId", "attributeValue");
		assertEquals(dataGroup.getAttribute("attributeId"), "attributeValue");

	}

	@Test
	public void testAddChild() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement dataElement = DataAtomic.withNameInDataAndValue("childNameInData",
				"childValue");
		dataGroup.addChild(dataElement);
		List<DataElement> children = dataGroup.getChildren();
		DataElement childElementOut = children.get(0);
		assertEquals(childElementOut.getNameInData(), "childNameInData");
	}

	@Test
	public void testContainsChildWithId() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("otherChildId", "otherChildValue"));
		DataElement child = DataAtomic.withNameInDataAndValue("childId", "child value");
		dataGroup.addChild(child);
		assertTrue(dataGroup.containsChildWithNameInData("childId"));
	}

	@Test
	public void testContainsChildWithIdNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement child = DataAtomic.withNameInDataAndValue("childId", "child value");
		dataGroup.addChild(child);
		assertFalse(dataGroup.containsChildWithNameInData("childId_NOT_FOUND"));
	}

	@Test
	public void testRemoveChildWithId() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement child = DataAtomic.withNameInDataAndValue("childId", "child value");
		dataGroup.addChild(child);
		dataGroup.removeChild("childId");
		assertFalse(dataGroup.containsChildWithNameInData("childId"));
	}

	@Test(expectedExceptions = DataMissingException.class)
	public void testRemoveChildWithIdNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.removeChild("childId");
		assertFalse(dataGroup.containsChildWithNameInData("childId"));
	}

	@Test
	public void testGetAtomicValue() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		assertEquals(dataGroup.getFirstAtomicValueWithNameInData("atomicNameInData"),
				"atomicValue");
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Atomic value not found for childNameInData:" + "atomicNameInData_NOT_FOUND")
	public void testExtractAtomicValueNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		dataGroup.getFirstAtomicValueWithNameInData("atomicNameInData_NOT_FOUND");
	}

	@Test
	public void testGetAllDataAtomicsWithNameInData() {
		DataGroup book = createDataGroupWithTwoAtomicChildrenAndOneGroupChild();

		assertEquals(book.getAllDataAtomicsWithNameInData("someChild").size(), 2);

	}

	private DataGroup createDataGroupWithTwoAtomicChildrenAndOneGroupChild() {
		DataGroup book = DataGroup.withNameInData("book");
		DataAtomic child1 = DataAtomic.withNameInDataAndValue("someChild", "child1");
		child1.setRepeatId("0");
		book.addChild(child1);

		DataAtomic child2 = DataAtomic.withNameInDataAndValue("someChild", "child2");
		child2.setRepeatId("1");
		book.addChild(child2);

		DataGroup child3 = DataGroup.withNameInData("someChild");
		book.addChild(child3);
		return book;
	}

	@Test
	public void testGetGroup() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		dataGroup.addChild(dataGroup2);
		assertEquals(dataGroup.getFirstGroupWithNameInData("childNameInData"), dataGroup2);
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Group not found for childNameInData:childNameInData_NOT_FOUND")
	public void testGetFirstGroupWithNameInDataNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		dataGroup.addChild(dataGroup2);
		dataGroup.getFirstGroupWithNameInData("childNameInData_NOT_FOUND");
	}

	@Test
	public void testGetFirstChildWithNameInData() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		dataGroup.addChild(dataGroup2);
		assertEquals(dataGroup.getFirstChildWithNameInData("childNameInData"), dataGroup2);
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Element not found for childNameInData:childNameInData_NOT_FOUND")
	public void testGetFirstChildWithNameInDataNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		dataGroup.addChild(dataGroup2);
		dataGroup.getFirstChildWithNameInData("childNameInData_NOT_FOUND");
	}

	@Test
	public void testGetAllGroupsWithNameInData() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		addTwoGroupChildrenWithSameNameInData(dataGroup);

		List<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInData("childNameInData");
		assertEquals(groupsFound.size(), 2);
	}

	private void addTwoGroupChildrenWithSameNameInData(DataGroup parentDataGroup) {
		DataGroup dataGroup = DataGroup.withNameInData("childNameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("firstName", "someName"));
		dataGroup.setRepeatId("0");
		parentDataGroup.addChild(dataGroup);
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataAtomic.withNameInDataAndValue("firstName", "someOtherName"));
		dataGroup2.setRepeatId("1");
		parentDataGroup.addChild(dataGroup2);
	}

	@Test
	public void testGetAllGroupsWithNameInDataNoMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));

		List<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInData("childNameInData");
		assertEquals(groupsFound.size(), 0);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributesOneMatch() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));

		assertEquals(groupsFound.size(), 1);
		assertGroupsFoundAre(groupsFound, child3);
	}

	private void assertGroupsFoundAre(Collection<DataGroup> groupsFound,
			DataGroup... assertedGroups) {
		int i = 0;
		for (DataGroup groupFound : groupsFound) {
			assertEquals(groupFound, assertedGroups[i]);
			i++;
		}
	}

	private DataGroup createTestGroupForAttributesReturnChildGroupWithAttribute(
			DataGroup dataGroup) {
		addAndReturnDataGroupChildWithNameInData(dataGroup, "groupId2");
		addAndReturnDataGroupChildWithNameInData(dataGroup, "groupId3");
		addAndReturnDataGroupChildWithNameInData(dataGroup, "groupId2");
		DataGroup child3 = addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup,
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));
		return child3;
	}

	private DataGroup addAndReturnDataGroupChildWithNameInData(DataGroup dataGroup,
			String nameInData) {
		DataGroup child = DataGroup.withNameInData(nameInData);
		dataGroup.addChild(child);
		return child;
	}

	private DataGroup addAndReturnDataGroupChildWithNameInDataAndAttributes(DataGroup dataGroup,
			String nameInData, DataAttribute... attributes) {
		DataGroup child = DataGroup.withNameInData(nameInData);
		dataGroup.addChild(child);
		for (DataAttribute attribute : attributes) {
			child.addAttributeByIdWithValue(attribute.getNameInData(), attribute.getValue());
		}
		return child;
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributesTwoMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);
		DataGroup child4 = addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup,
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));

		assertEquals(groupsFound.size(), 2);
		assertGroupsFoundAre(groupsFound, child3, child4);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributesOneWrongAttributeValueTwoMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);
		DataGroup child4 = addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup,
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));
		addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup, "groupId2",
				DataAttribute.withNameInDataAndValue("nameInData", "value2"));

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));

		assertEquals(groupsFound.size(), 2);
		assertGroupsFoundAre(groupsFound, child3, child4);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributesOneWrongAttributeNameTwoMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);
		DataGroup child4 = addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup,
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));
		addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup, "groupId2",
				DataAttribute.withNameInDataAndValue("nameInData2", "value1"));

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"));

		assertEquals(groupsFound.size(), 2);
		assertGroupsFoundAre(groupsFound, child3, child4);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndTwoAttributesNoMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);
		addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup, "groupId2",
				DataAttribute.withNameInDataAndValue("nameInData", "value1"),
				DataAttribute.withNameInDataAndValue("nameInData2", "value2"));

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"),
				DataAttribute.withNameInDataAndValue("nameInData2", "value1"));

		assertEquals(groupsFound.size(), 0);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndTwoAttributesOneMatches() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		createTestGroupForAttributesReturnChildGroupWithAttribute(dataGroup);
		DataGroup child4 = addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup,
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"),
				DataAttribute.withNameInDataAndValue("nameInData2", "value2"));
		addAndReturnDataGroupChildWithNameInDataAndAttributes(dataGroup, "groupId2",
				DataAttribute.withNameInDataAndValue("nameInData", "value1"),
				DataAttribute.withNameInDataAndValue("nameInData3", "value2"));

		Collection<DataGroup> groupsFound = dataGroup.getAllGroupsWithNameInDataAndAttributes(
				"groupId2", DataAttribute.withNameInDataAndValue("nameInData", "value1"),
				DataAttribute.withNameInDataAndValue("nameInData2", "value2"));

		assertEquals(groupsFound.size(), 1);
		assertGroupsFoundAre(groupsFound, child4);
	}

	@Test
	public void testRemoveChild() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		createAndAddAnAtomicChildToDataGroup(dataGroup);
		dataGroup.removeFirstChildWithNameInData("childId");
		assertFalse(dataGroup.containsChildWithNameInData("childId"));
	}

	@Test(expectedExceptions = DataMissingException.class)
	public void testRemoveChildNotFound() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		createAndAddAnAtomicChildToDataGroup(dataGroup);
		dataGroup.removeFirstChildWithNameInData("childId_NOTFOUND");
	}

	private DataElement createAndAddAnAtomicChildToDataGroup(DataGroup dataGroup) {
		DataElement child = DataAtomic.withNameInDataAndValue("childId", "child value");
		dataGroup.addChild(child);
		return child;
	}

	@Test
	public void testGetAllChildrenWithNameInDataNoChildFound() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement atomicChild = DataAtomic.withNameInDataAndValue("childNameInData",
				"child value");
		dataGroup.addChild(atomicChild);
		DataGroup groupChild = DataGroup.withNameInData("childNameInData");
		dataGroup.addChild(groupChild);

		List<DataElement> childrenFound = dataGroup
				.getAllChildrenWithNameInData("NOThildNameInData");
		assertEquals(childrenFound.size(), 0);
	}

	@Test
	public void testGetAllChildrenWithNameInDataOneGroup() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataGroup groupChild = DataGroup.withNameInData("childNameInData");
		dataGroup.addChild(groupChild);

		List<DataElement> childrenFound = dataGroup.getAllChildrenWithNameInData("childNameInData");
		assertEquals(childrenFound.size(), 1);
		assertEquals(childrenFound.get(0), groupChild);
	}

	@Test
	public void testGetAllChildrenWithNameInDataOneDataAtomic() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement atomicChild = DataAtomic.withNameInDataAndValue("childNameInData",
				"child value");
		dataGroup.addChild(atomicChild);

		List<DataElement> childrenFound = dataGroup.getAllChildrenWithNameInData("childNameInData");
		assertEquals(childrenFound.size(), 1);
		assertEquals(childrenFound.get(0), atomicChild);
	}

	@Test
	public void testGetAllChildrenWithNameInDataOneGroupAndOneDataAtomic() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		DataElement atomicChild = DataAtomic.withNameInDataAndValue("childNameInData",
				"child value");
		dataGroup.addChild(atomicChild);
		DataGroup groupChild = DataGroup.withNameInData("childNameInData");
		dataGroup.addChild(groupChild);

		List<DataElement> childrenFound = dataGroup.getAllChildrenWithNameInData("childNameInData");
		assertEquals(childrenFound.size(), 2);
		assertEquals(childrenFound.get(0), atomicChild);
		assertEquals(childrenFound.get(1), groupChild);
	}
}
