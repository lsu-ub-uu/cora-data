/*
 * Copyright 2022 Uppsala University Library
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
package se.uu.ub.cora.data.collectterms;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IndexTermTest {

	IndexTerm storageTerm;

	@BeforeMethod
	private void beforeMethod() {
		storageTerm = new IndexTerm("id", "value", "indexFieldName", "indexType");
	}

	@Test
	public void testMethodsAutoCreatedAsTheClassCurrentlyIsARecord() throws Exception {
		assertEquals(storageTerm.id(), "id");
		assertEquals(storageTerm.value(), "value");
		assertEquals(storageTerm.indexFieldName(), "indexFieldName");
		assertEquals(storageTerm.indexType(), "indexType");
	}

}
