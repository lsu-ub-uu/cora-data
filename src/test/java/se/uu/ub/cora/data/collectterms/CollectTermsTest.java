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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;

import java.util.Collections;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CollectTermsTest {

	CollectTerms collectTerms;

	@BeforeMethod
	private void beforeMethod() {
		collectTerms = new CollectTerms();
	}

	@Test
	public void testBuildEmpty() throws Exception {
		assertFalse(collectTerms.recordId.isPresent());
		assertFalse(collectTerms.recordType.isPresent());
		assertEquals(collectTerms.permissionTerms, Collections.emptyList());
		assertEquals(collectTerms.storageTerms, Collections.emptyList());
		assertEquals(collectTerms.indexTerms, Collections.emptyList());
	}

	@Test
	public void testAddPermissionTerm() throws Exception {
		PermissionTerm term = new PermissionTerm(null, null, null);

		collectTerms.addPermissionTerm(term);

		assertEquals(collectTerms.permissionTerms.size(), 1);
		assertSame(collectTerms.permissionTerms.get(0), term);
	}

	@Test
	public void testAddStorageTerm() throws Exception {
		StorageTerm term = new StorageTerm(null, null, null);

		collectTerms.addStorageTerm(term);

		assertEquals(collectTerms.storageTerms.size(), 1);
		assertSame(collectTerms.storageTerms.get(0), term);
	}

	@Test
	public void testAddIndexTerm() throws Exception {
		IndexTerm term = new IndexTerm(null, null, null, null);

		collectTerms.addIndexTerm(term);

		assertEquals(collectTerms.indexTerms.size(), 1);
		assertSame(collectTerms.indexTerms.get(0), term);
	}

}
