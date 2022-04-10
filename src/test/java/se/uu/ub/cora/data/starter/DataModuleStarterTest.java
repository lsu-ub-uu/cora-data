/*
 * Copyright 2019 Uppsala University Library
 * Copyright 2022 Olov McKie
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

import se.uu.ub.cora.data.DataFactory;
import se.uu.ub.cora.data.spy.DataFactorySpy;

public class DataModuleStarterTest {
	DataModuleStarterImp starter;
	List<DataFactory> dataFactoryImplementations;
	DataFactorySpy dataFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new DataModuleStarterImp();
		dataFactoryImplementations = new ArrayList<>();
		dataFactorySpy = new DataFactorySpy();

	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for DataFactory")
	public void testStartModuleThrowsErrorIfNoDataFactoryImplementations() throws Exception {
		starter.startUsingDataFactoryImplementations(dataFactoryImplementations);
	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for DataFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneDataFactoryImplementations()
			throws Exception {
		dataFactoryImplementations.add(new DataFactorySpy());
		dataFactoryImplementations.add(new DataFactorySpy());
		starter.startUsingDataFactoryImplementations(dataFactoryImplementations);
	}

	@Test
	public void testGetDataFactory() throws Exception {
		dataFactoryImplementations.add(dataFactorySpy);
		starter.startUsingDataFactoryImplementations(dataFactoryImplementations);
		assertSame(starter.getDataFactory(), dataFactorySpy);
	}
}
