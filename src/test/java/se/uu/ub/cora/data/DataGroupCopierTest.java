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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataGroupCopierTest {

	private DataGroup dataGroup;
	private DataGroupCopier dataGroupCopier;
	private DataCopierFactorySpy copierFactory;

	@BeforeMethod
	public void setUp() {
		dataGroup = DataGroup.withNameInData("someDataGroup");
		copierFactory = new DataCopierFactorySpy();
		dataGroupCopier = DataGroupCopier.usingDataGroupAndCopierFactory(dataGroup, copierFactory);
	}

	@Test
	public void testCopyDataGroupNotSameObject() {
		DataGroup dataGroupCopy = dataGroupCopier.copy();
		assertNotNull(dataGroupCopy);
		assertNotSame(dataGroup, dataGroupCopy);
	}

	@Test
	public void testCopyDataGroupSameNameInData() {
		DataGroup dataGroupCopy = dataGroupCopier.copy();
		assertEquals(dataGroupCopy.getNameInData(), dataGroup.getNameInData());
	}

	@Test
	public void testCopyDataGroupChildDataAtomicIsCopied() {
		DataAtomic atomicChild = DataAtomic.withNameInDataAndValue("someAtomicChild",
				"someAtomicValue");
		dataGroup.addChild(atomicChild);

		DataGroup dataGroupCopy = dataGroupCopier.copy();

		assertSame(copierFactory.dataElements.get(0), atomicChild);
		assertNotNull(copierFactory.dataElements.get(0));
		// String atomicChildCopy =
		// dataGroupCopy.getFirstAtomicValueWithNameInData("someAtomicChild");
		// assertEquals(atomicChildCopy, atomicChild.getValue());
	}
}
