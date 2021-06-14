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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataElementTest {
	private DataElement dataElement;

	@BeforeMethod
	public void BeforeMethod() {
		dataElement = new DataAtomicSpy();
	}

	@Test
	public void testAddAttributeWithValue() throws Exception {
		dataElement.addAttributeByIdWithValue("someAttributeId", "someAttributeValue");
		assertTrue(dataElement.getAttributes().isEmpty());
	}

	@Test
	public void testHasAttributes() throws Exception {
		assertFalse(dataElement.hasAttributes());
	}

	@Test(expectedExceptions = DataMissingException.class, expectedExceptionsMessageRegExp = ""
			+ "This class has not implemented getAttribute.")
	public void testGetAttributeDoesNotExist() {
		dataElement.getAttribute("someAttributeId");
	}

	@Test
	public void testGetAttributesInInterfaceThroughDataAtomicSpy() {
		assertTrue(dataElement.getAttributes().isEmpty());
	}

}
