/*
 * Copyright 2015, 2016, 2019 Uppsala University Library
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
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataRecordLinkTest {

	DataRecordLink recordLink;

	@BeforeMethod
	public void setUp() {
		recordLink = DataRecordLink.withNameInData("nameInData");

		DataAtomic linkedRecordType = DataAtomic.withNameInDataAndValue("linkedRecordType",
				"myLinkedRecordType");
		recordLink.addChild(linkedRecordType);

		DataAtomic linkedRecordId = DataAtomic.withNameInDataAndValue("linkedRecordId",
				"myLinkedRecordId");
		recordLink.addChild(linkedRecordId);

	}

	@Test
	public void testInit() {
		assertEquals(recordLink.getNameInData(), "nameInData");
		assertNotNull(recordLink.getAttributes());
		assertNotNull(recordLink.getChildren());
		assertEquals(recordLink.getFirstAtomicValueWithNameInData("linkedRecordType"),
				"myLinkedRecordType");
		assertEquals(recordLink.getFirstAtomicValueWithNameInData("linkedRecordId"),
				"myLinkedRecordId");
		assertNotNull(recordLink.getActions());
	}

	@Test
	public void testInitWithRepeatId() {
		recordLink.setRepeatId("hugh");
		assertEquals(recordLink.getRepeatId(), "hugh");
	}

	@Test
	public void testAddAttribute() {
		recordLink = DataRecordLink.withNameInData("nameInData");
		recordLink.addAttributeByIdWithValue("someId", "someValue");

		Map<String, String> attributes = recordLink.getAttributes();
		Map.Entry<String, String> entry = attributes.entrySet().iterator().next();
		assertEquals(entry.getKey(), "someId");
		assertEquals(entry.getValue(), "someValue");
	}

	@Test
	public void testInitWithLinkedPath() {
		CoraDataGroup dataGroup = CoraDataGroup.withNameInData("linkedPath");
		recordLink.addChild(dataGroup);
		assertNotNull(recordLink.getFirstChildWithNameInData("linkedPath"));
	}

	@Test
	public void testAddAction() {
		recordLink.addAction(Action.READ);

		assertTrue(recordLink.getActions().contains(Action.READ));
		assertFalse(recordLink.getActions().contains(Action.DELETE));
		// small hack to get 100% coverage on enum
		Action.valueOf(Action.READ.toString());
	}

	@Test
	public void testFromDataGroup() {
		CoraDataGroup dataGroupRecordLink = createRecordLinkAsDataGroup();

		DataRecordLink dataRecordLink = DataRecordLink.fromDataGroup(dataGroupRecordLink);

		assertCorrectFromDataRecordLink(dataRecordLink);
		assertNull(dataRecordLink.getRepeatId());
	}

	private CoraDataGroup createRecordLinkAsDataGroup() {
		CoraDataGroup dataGroupRecordLink = CoraDataGroup.withNameInData("nameInData");

		DataAtomic linkedRecordType = DataAtomic.withNameInDataAndValue("linkedRecordType",
				"someLinkedRecordType");
		dataGroupRecordLink.addChild(linkedRecordType);

		DataAtomic linkedRecordId = DataAtomic.withNameInDataAndValue("linkedRecordId",
				"someLinkedRecordId");
		dataGroupRecordLink.addChild(linkedRecordId);
		return dataGroupRecordLink;
	}

	private void assertCorrectFromDataRecordLink(DataRecordLink recordLink) {
		assertEquals(recordLink.getNameInData(), "nameInData");

		DataAtomic convertedRecordType = (DataAtomic) recordLink
				.getFirstChildWithNameInData("linkedRecordType");
		assertEquals(convertedRecordType.getValue(), "someLinkedRecordType");

		DataAtomic convertedRecordId = (DataAtomic) recordLink
				.getFirstChildWithNameInData("linkedRecordId");
		assertEquals(convertedRecordId.getValue(), "someLinkedRecordId");
	}

	@Test
	public void testFromDataGroupWithRepeatId() {
		CoraDataGroup dataGroupRecordLink = createRecordLinkAsDataGroup();
		dataGroupRecordLink.setRepeatId("1");

		DataRecordLink dataRecordLink = DataRecordLink.fromDataGroup(dataGroupRecordLink);

		assertCorrectFromDataRecordLink(dataRecordLink);
		assertEquals(dataRecordLink.getRepeatId(), "1");
	}

}
