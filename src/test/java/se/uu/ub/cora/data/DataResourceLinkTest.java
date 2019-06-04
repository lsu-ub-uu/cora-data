package se.uu.ub.cora.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataResourceLinkTest {

	DataResourceLink resourceLink;

	@BeforeMethod
	public void setUp() {
		resourceLink = DataResourceLink.withNameInData("nameInData");

		DataAtomic streamId = DataAtomic.withNameInDataAndValue("streamId", "myStreamId");
		resourceLink.addChild(streamId);

	}

	@Test
	public void testInit() {
		assertEquals(resourceLink.getNameInData(), "nameInData");
		assertNotNull(resourceLink.getAttributes());
		assertNotNull(resourceLink.getChildren());
		assertEquals(resourceLink.getFirstAtomicValueWithNameInData("streamId"), "myStreamId");
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

	@Test
	public void testFromDataGroup() {
		DataGroup dataGroupRecordLink = createRecordLinkAsDataGroup();

		DataResourceLink dataRecordLink = DataResourceLink.fromDataGroup(dataGroupRecordLink);

		assertCorrectFromDataRecordLink(dataRecordLink);
		assertNull(dataRecordLink.getRepeatId());
	}

	private DataGroup createRecordLinkAsDataGroup() {
		DataGroup dataGroupRecordLink = DataGroup.withNameInData("nameInData");

		DataAtomic fileName = DataAtomic.withNameInDataAndValue("filename", "someFileName");
		dataGroupRecordLink.addChild(fileName);

		DataAtomic streamId = DataAtomic.withNameInDataAndValue("streamId", "someStreamId");
		dataGroupRecordLink.addChild(streamId);
		DataAtomic filesize = DataAtomic.withNameInDataAndValue("filesize", "567");
		dataGroupRecordLink.addChild(filesize);
		DataAtomic mimeType = DataAtomic.withNameInDataAndValue("mimeType", "someMimeType");
		dataGroupRecordLink.addChild(mimeType);
		return dataGroupRecordLink;
	}

	private void assertCorrectFromDataRecordLink(DataResourceLink resourceLink) {
		assertEquals(resourceLink.getNameInData(), "nameInData");

		DataAtomic convertedFileName = (DataAtomic) resourceLink
				.getFirstChildWithNameInData("filename");
		assertEquals(convertedFileName.getValue(), "someFileName");

		DataAtomic convertedStreamId = (DataAtomic) resourceLink
				.getFirstChildWithNameInData("streamId");
		assertEquals(convertedStreamId.getValue(), "someStreamId");

		DataAtomic convertedFilesize = (DataAtomic) resourceLink
				.getFirstChildWithNameInData("filesize");
		assertEquals(convertedFilesize.getValue(), "567");

		DataAtomic convertedMimeType = (DataAtomic) resourceLink
				.getFirstChildWithNameInData("mimeType");
		assertEquals(convertedMimeType.getValue(), "someMimeType");
	}
}
