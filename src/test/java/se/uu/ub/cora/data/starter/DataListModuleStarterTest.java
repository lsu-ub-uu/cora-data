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

import se.uu.ub.cora.data.DataListFactory;

public class DataListModuleStarterTest {
	DataListModuleStarterImp starter;
	List<DataListFactory> dataListFactoryImplementations;
	DataListFactorySpy dataListFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new DataListModuleStarterImp();
		dataListFactoryImplementations = new ArrayList<>();
		dataListFactorySpy = new DataListFactorySpy();

	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for DataListFactory")
	public void testStartModuleThrowsErrorIfNoDataListFactoryImplementations() throws Exception {
		starter.startUsingDataListFactoryImplementations(dataListFactoryImplementations);
	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for DataListFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneDataListFactoryImplementations()
			throws Exception {
		dataListFactoryImplementations.add(new DataListFactorySpy());
		dataListFactoryImplementations.add(new DataListFactorySpy());
		starter.startUsingDataListFactoryImplementations(dataListFactoryImplementations);
	}

	@Test
	public void testGetDataListFactory() throws Exception {
		dataListFactoryImplementations.add(dataListFactorySpy);
		starter.startUsingDataListFactoryImplementations(dataListFactoryImplementations);
		assertSame(starter.getDataListFactory(), dataListFactorySpy);
	}
}
