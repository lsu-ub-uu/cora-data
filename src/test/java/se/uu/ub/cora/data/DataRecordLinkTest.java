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

	DataRecordLink spiderRecordLink;

	@BeforeMethod
	public void setUp() {
		spiderRecordLink = DataRecordLink.withNameInData("nameInData");

		DataAtomic linkedRecordType = DataAtomic
				.withNameInDataAndValue("linkedRecordType", "myLinkedRecordType");
		spiderRecordLink.addChild(linkedRecordType);

		DataAtomic linkedRecordId = DataAtomic.withNameInDataAndValue("linkedRecordId",
				"myLinkedRecordId");
		spiderRecordLink.addChild(linkedRecordId);

	}

	@Test
	public void testInit() {
		assertEquals(spiderRecordLink.getNameInData(), "nameInData");
		assertNotNull(spiderRecordLink.getAttributes());
		assertNotNull(spiderRecordLink.getChildren());
		assertEquals(spiderRecordLink.getFirstAtomicValueWithNameInData("linkedRecordType"),
				"myLinkedRecordType");
		assertEquals(spiderRecordLink.getFirstAtomicValueWithNameInData("linkedRecordId"),
				"myLinkedRecordId");
		assertNotNull(spiderRecordLink.getActions());
	}

	@Test
	public void testInitWithRepeatId() {
		spiderRecordLink.setRepeatId("hugh");
		assertEquals(spiderRecordLink.getRepeatId(), "hugh");
	}

	@Test
	public void testAddAttribute() {
		spiderRecordLink = DataRecordLink.withNameInData("nameInData");
		spiderRecordLink.addAttributeByIdWithValue("someId", "someValue");

		Map<String, String> attributes = spiderRecordLink.getAttributes();
		Map.Entry<String, String> entry = attributes.entrySet().iterator().next();
		assertEquals(entry.getKey(), "someId");
		assertEquals(entry.getValue(), "someValue");
	}

	@Test
	public void testInitWithLinkedPath() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("linkedPath");
		spiderRecordLink.addChild(spiderDataGroup);
		assertNotNull(spiderRecordLink.getFirstChildWithNameInData("linkedPath"));
	}

	@Test
	public void testAddAction() {
		spiderRecordLink.addAction(Action.READ);

		assertTrue(spiderRecordLink.getActions().contains(Action.READ));
		assertFalse(spiderRecordLink.getActions().contains(Action.DELETE));
		// small hack to get 100% coverage on enum
		Action.valueOf(Action.READ.toString());
	}

}
