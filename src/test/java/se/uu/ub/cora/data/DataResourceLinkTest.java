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

	DataResourceLink resourceLink;

	@BeforeMethod
	public void setUp() {
		resourceLink = DataResourceLink.withNameInData("nameInData");

		DataAtomic streamId = DataAtomic.withNameInDataAndValue("streamId",
				"myStreamId");
		resourceLink.addChild(streamId);

	}

	@Test
	public void testInit() {
		assertEquals(resourceLink.getNameInData(), "nameInData");
		assertNotNull(resourceLink.getAttributes());
		assertNotNull(resourceLink.getChildren());
		assertEquals(resourceLink.getFirstAtomicValueWithNameInData("streamId"),
				"myStreamId");
		assertNotNull(resourceLink.getActions());
	}

	@Test
	public void testInitWithRepeatId() {
		resourceLink.setRepeatId("hugh");
		assertEquals(resourceLink.getRepeatId(), "hugh");
	}

	@Test
	public void testAddAction() {
		resourceLink.addAction(Action.READ);

		assertTrue(resourceLink.getActions().contains(Action.READ));
		assertFalse(resourceLink.getActions().contains(Action.DELETE));
		// small hack to get 100% coverage on enum
		Action.valueOf(Action.READ.toString());
	}
}
