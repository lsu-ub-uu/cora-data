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

package se.uu.ub.cora.data.starter;

import static org.testng.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataGroupFactory;
import se.uu.ub.cora.data.DataGroupFactorySpy;

public class DataGroupModuleStarterTest {
	DataGroupModuleStarterImp starter;
	List<DataGroupFactory> dataGroupFactoryImplementations;
	DataGroupFactorySpy dataGroupFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new DataGroupModuleStarterImp();
		dataGroupFactoryImplementations = new ArrayList<>();
		dataGroupFactorySpy = new DataGroupFactorySpy();

	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for DataGroupFactory")
	public void testStartModuleThrowsErrorIfNoDataGroupFactoryImplementations() throws Exception {
		starter.startUsingDataGroupFactoryImplementations(dataGroupFactoryImplementations);
	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for DataGroupFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneDataGroupFactoryImplementations()
			throws Exception {
		dataGroupFactoryImplementations.add(new DataGroupFactorySpy());
		dataGroupFactoryImplementations.add(new DataGroupFactorySpy());
		starter.startUsingDataGroupFactoryImplementations(dataGroupFactoryImplementations);
	}

	@Test
	public void testGetDataGroupFactory() throws Exception {
		dataGroupFactoryImplementations.add(dataGroupFactorySpy);
		starter.startUsingDataGroupFactoryImplementations(dataGroupFactoryImplementations);
		assertSame(starter.getDataGroupFactory(), dataGroupFactorySpy);
	}
}
