/*
 * Copyright 2015 Uppsala University Library
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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.Data;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecord;

public class DataRecordTest {
	private DataRecord spiderDataRecord;

	@BeforeMethod
	public void beforeMethod() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataRecord = DataRecord.withSpiderDataGroup(spiderDataGroup);
	}

	@Test
	public void testRecordIsSpiderData() {
		assertTrue(spiderDataRecord instanceof Data);
	}

	@Test
	public void testKeys() {
		spiderDataRecord.addKey("KEY");
		assertTrue(spiderDataRecord.containsKey("KEY"));
	}

	@Test
	public void testGetKeys() {
		spiderDataRecord.addKey("KEY1");
		spiderDataRecord.addKey("KEY2");
		Set<String> keys = spiderDataRecord.getKeys();
		assertTrue(keys.contains("KEY1"));
		assertTrue(keys.contains("KEY2"));
	}

	@Test
	public void testAddAction() {
		spiderDataRecord.addAction(Action.READ);

		assertTrue(spiderDataRecord.getActions().contains(Action.READ));
		assertFalse(spiderDataRecord.getActions().contains(Action.DELETE));
		// small hack to get 100% coverage on enum
		Action.valueOf(Action.READ.toString());
	}

	@Test
	public void testGetSpiderDataGroup() {
		String nameInData = spiderDataRecord.getSpiderDataGroup().getNameInData();
		assertEquals(nameInData, "nameInData");
	}

	@Test
	public void testSpiderDataGroup() {
		DataGroup spiderDataGroup = DataGroup.withNameInData("nameInData");
		spiderDataRecord.setSpiderDataGroup(spiderDataGroup);
		assertEquals(spiderDataRecord.getSpiderDataGroup(), spiderDataGroup);
	}
}
