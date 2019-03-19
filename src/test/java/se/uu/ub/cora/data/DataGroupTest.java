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

import se.uu.ub.cora.data.DataMissingException;
import se.uu.ub.cora.data.Data;
import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataElement;
import se.uu.ub.cora.data.DataGroup;

public class DataGroupTest {
	@Test
	public void testInit() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		assertEquals(spiderDataGroup.getNameInData(), "nameInData");
		assertNotNull(spiderDataGroup.getAttributes());
		assertNotNull(spiderDataGroup.getChildren());
	}

	@Test
	public void testGroupIsSpiderData() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		assertTrue(spiderDataGroup instanceof Data);
	}

	@Test
	public void testInitWithRepeatId() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.setRepeatId("hrumph");
		assertEquals(spiderDataGroup.getNameInData(), "nameInData");
		assertNotNull(spiderDataGroup.getAttributes());
		assertNotNull(spiderDataGroup.getChildren());
		assertEquals(spiderDataGroup.getRepeatId(), "hrumph");
	}

	@Test
	public void testAddAttribute() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addAttributeByIdWithValue("nameInData", "value");
		Map<String, String> attributes = spiderDataGroup.getAttributes();
		Entry<String, String> entry = attributes.entrySet().iterator().next();
		assertEquals(entry.getKey(), "nameInData");
		assertEquals(entry.getValue(), "value");
	}

	@Test
	public void testAddChild() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		DataElement dataElement = DataAtomic.withNameInDataAndValue("childNameInData",
				"childValue");
		spiderDataGroup.addChild(dataElement);
		List<DataElement> children = spiderDataGroup.getChildren();
		DataElement childElementOut = children.get(0);
		assertEquals(childElementOut.getNameInData(), "childNameInData");
	}

	@Test
	public void testContainsChildWithId() {
		DataGroup dataGroup = DataGroup.withNameInData("nameInData");
		dataGroup.addChild(
				DataAtomic.withNameInDataAndValue("otherChildId", "otherChildValue"));
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
	public void testExtractAtomicValue() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		assertEquals(spiderDataGroup.getFirstAtomicValueWithNameInData("atomicNameInData"),
				"atomicValue");
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Atomic value not found for childNameInData:" + "atomicNameInData_NOT_FOUND")
	public void testExtractAtomicValueNotFound() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		spiderDataGroup.getFirstAtomicValueWithNameInData("atomicNameInData_NOT_FOUND");
	}

	@Test
	public void testExtractGroup() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		spiderDataGroup.addChild(dataGroup2);
		assertEquals(spiderDataGroup.getFirstGroupWithNameInData("childNameInData"), dataGroup2);
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Group not found for childNameInData:childNameInData_NOT_FOUND")
	public void testGetFirstGroupWithNameInDataNotFound() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		spiderDataGroup.addChild(dataGroup2);
		spiderDataGroup.getFirstGroupWithNameInData("childNameInData_NOT_FOUND");
	}

	@Test
	public void testGetFirstChildWithNameInData() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		spiderDataGroup.addChild(dataGroup2);
		assertEquals(spiderDataGroup.getFirstChildWithNameInData("childNameInData"), dataGroup2);
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "Element not found for childNameInData:childNameInData_NOT_FOUND")
	public void testGetFirstChildWithNameInDataNotFound() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataGroup.withNameInData("grandChildNameInData"));
		spiderDataGroup.addChild(dataGroup2);
		spiderDataGroup.getFirstChildWithNameInData("childNameInData_NOT_FOUND");
	}

	@Test
	public void testGetAllGroupsWithNameInData() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));
		addTwoGroupChildrenWithSameNameInData(spiderDataGroup);

		List<DataGroup> groupsFound = spiderDataGroup
				.getAllGroupsWithNameInData("childNameInData");
		assertEquals(groupsFound.size(), 2);
	}

	private void addTwoGroupChildrenWithSameNameInData(DataGroup spiderDataGroup) {
		DataGroup dataGroup = DataGroup.withNameInData("childNameInData");
		dataGroup.addChild(DataAtomic.withNameInDataAndValue("firstName", "someName"));
		dataGroup.setRepeatId("0");
		spiderDataGroup.addChild(dataGroup);
		DataGroup dataGroup2 = DataGroup.withNameInData("childNameInData");
		dataGroup2.addChild(DataAtomic.withNameInDataAndValue("firstName", "someOtherName"));
		dataGroup2.setRepeatId("1");
		spiderDataGroup.addChild(dataGroup2);
	}

	@Test
	public void testGetAllGroupsWithNameInDataNoMatches() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataGroup.addChild(
				DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue"));

		List<DataGroup> groupsFound = spiderDataGroup
				.getAllGroupsWithNameInData("childNameInData");
		assertEquals(groupsFound.size(), 0);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributesOneMatch() {
		DataGroup dataGroup = DataGroup.withNameInData("someDataGroup");
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(
				dataGroup);

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

	private DataGroup addAndReturnDataGroupChildWithNameInDataAndAttributes(
			DataGroup dataGroup, String nameInData, DataAttribute... attributes) {
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
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(
				dataGroup);
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
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(
				dataGroup);
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
		DataGroup child3 = createTestGroupForAttributesReturnChildGroupWithAttribute(
				dataGroup);
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
}
