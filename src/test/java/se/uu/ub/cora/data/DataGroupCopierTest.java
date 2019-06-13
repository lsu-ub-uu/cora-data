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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataGroupCopierTest {

	private DataGroup originalDataGroup;
	private DataGroupCopier dataGroupCopier;
	private DataCopierFactorySpy copierFactory;

	@BeforeMethod
	public void setUp() {
		originalDataGroup = DataGroup.withNameInData("someDataGroup");
		copierFactory = new DataCopierFactorySpy();
		dataGroupCopier = DataGroupCopier.usingDataGroupAndCopierFactory(originalDataGroup,
				copierFactory);
	}

	@Test
	public void testGetCopierFactory() {
		assertEquals(dataGroupCopier.getCopierFactory(), copierFactory);
	}

	@Test
	public void testCopyDataGroupNotSameObject() {
		DataGroup dataGroupCopy = dataGroupCopier.copy();
		assertNotNull(dataGroupCopy);
		assertNotSame(originalDataGroup, dataGroupCopy);
	}

	@Test
	public void testCopyDataGroupSameNameInData() {
		DataGroup dataGroupCopy = dataGroupCopier.copy();
		assertEquals(dataGroupCopy.getNameInData(), originalDataGroup.getNameInData());
	}

	@Test
	public void testCopyDataGroupOneChildDataAtomicIsCopied() {
		createAndAddAtomicChildToOrginalDataGroup("someAtomicChild", "someAtomicValue");

		DataGroup dataGroupCopy = dataGroupCopier.copy();
		assertEquals(dataGroupCopy.getNameInData(), originalDataGroup.getNameInData());

		assertChildIssentToCopierUsingIndex(0);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(dataGroupCopy, 0);
		assertEquals(dataGroupCopy.getChildren().size(), 1);
	}

	private void createAndAddAtomicChildToOrginalDataGroup(String nameInData, String value) {
		DataAtomic atomicChild = DataAtomic.withNameInDataAndValue(nameInData, value);
		originalDataGroup.addChild(atomicChild);
	}

	private void assertChildIssentToCopierUsingIndex(int index) {
		DataElement dataElementSentToCopierFactory = copierFactory.dataElements.get(index);
		DataElement firstChildInOrignalDataGroup = originalDataGroup.getChildren().get(index);

		assertSame(dataElementSentToCopierFactory, firstChildInOrignalDataGroup);
		assertNotNull(copierFactory.dataElements.get(index));
	}

	private void assertChildReturnedFromCopierIsAddedToGroupUsingIndex(DataGroup dataGroupCopy,
			int index) {
		DataCopierSpy factoredCopier = copierFactory.factoredDataCopiers.get(index);
		assertTrue(factoredCopier.copyWasCalled);

		DataElement firstChildInCopiedGroup = dataGroupCopy.getChildren().get(index);
		DataElement elementReturnedFromCopier = copierFactory.factoredDataCopiers
				.get(index).returnedElement;

		assertSame(firstChildInCopiedGroup, elementReturnedFromCopier);
	}

	@Test
	public void testCopyDataGroupTwoChildrenDataAtomicsAreCopied() {
		createAndAddAtomicChildToOrginalDataGroup("someAtomicChild", "someAtomicValue");
		createAndAddAtomicChildToOrginalDataGroup("anotherAtomicChild", "anotherAtomicValue");

		DataGroup copiedDataGroup = dataGroupCopier.copy();

		assertChildIssentToCopierUsingIndex(0);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(copiedDataGroup, 0);
		assertChildIssentToCopierUsingIndex(1);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(copiedDataGroup, 1);
		assertEquals(copiedDataGroup.getChildren().size(), 2);
	}

	@Test
	public void testCopyDataGroupThreeChildrenDataAtomicsAndGroupAreCopied() {
		createAndAddAtomicChildToOrginalDataGroup("someAtomicChild", "someAtomicValue");
		createAndAddAtomicChildToOrginalDataGroup("anotherAtomicChild", "anotherAtomicValue");
		DataGroup childGroup = DataGroup.withNameInData("childGroup");
		childGroup.addChild(
				DataAtomic.withNameInDataAndValue("grandChldNameInData", "grandChildValue"));
		originalDataGroup.addChild(childGroup);

		DataGroup copiedDataGroup = dataGroupCopier.copy();

		assertChildIssentToCopierUsingIndex(0);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(copiedDataGroup, 0);
		assertChildIssentToCopierUsingIndex(1);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(copiedDataGroup, 1);
		assertChildReturnedFromCopierIsAddedToGroupUsingIndex(copiedDataGroup, 2);
		assertEquals(copiedDataGroup.getChildren().size(), 3);
	}
}
