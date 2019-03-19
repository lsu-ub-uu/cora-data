package se.uu.ub.cora.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataResourceLink;

public class DataResourceLinkTest {

	DataResourceLink spiderResourceLink;

	@BeforeMethod
	public void setUp() {
		spiderResourceLink = DataResourceLink.withNameInData("nameInData");

		DataAtomic streamId = DataAtomic.withNameInDataAndValue("streamId",
				"myStreamId");
		spiderResourceLink.addChild(streamId);

	}

	@Test
	public void testInit() {
		assertEquals(spiderResourceLink.getNameInData(), "nameInData");
		assertNotNull(spiderResourceLink.getAttributes());
		assertNotNull(spiderResourceLink.getChildren());
		assertEquals(spiderResourceLink.getFirstAtomicValueWithNameInData("streamId"),
				"myStreamId");
		assertNotNull(spiderResourceLink.getActions());
	}

	@Test
	public void testInitWithRepeatId() {
		spiderResourceLink.setRepeatId("hugh");
		assertEquals(spiderResourceLink.getRepeatId(), "hugh");
	}

	@Test
	public void testAddAction() {
		spiderResourceLink.addAction(Action.READ);

		assertTrue(spiderResourceLink.getActions().contains(Action.READ));
		assertFalse(spiderResourceLink.getActions().contains(Action.DELETE));
		// small hack to get 100% coverage on enum
		Action.valueOf(Action.READ.toString());
	}
}
