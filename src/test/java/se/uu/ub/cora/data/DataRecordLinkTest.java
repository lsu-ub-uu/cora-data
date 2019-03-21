package se.uu.ub.cora.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecordLink;

public class DataRecordLinkTest {

	DataRecordLink recordLink;

	@BeforeMethod
	public void setUp() {
		recordLink = DataRecordLink.withNameInData("nameInData");

		DataAtomic linkedRecordType = DataAtomic
				.withNameInDataAndValue("linkedRecordType", "myLinkedRecordType");
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
		DataGroup dataGroup = DataGroup.withNameInData("linkedPath");
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

}
