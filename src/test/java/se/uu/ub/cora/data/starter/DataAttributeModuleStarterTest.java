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

import se.uu.ub.cora.data.DataAttributeFactory;
import se.uu.ub.cora.data.DataAttributeFactorySpy;

public class DataAttributeModuleStarterTest {
	DataAttributeModuleStarterImp starter;
	List<DataAttributeFactory> dataAttributeFactoryImplementations;
	DataAttributeFactorySpy dataAttributeFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new DataAttributeModuleStarterImp();
		dataAttributeFactoryImplementations = new ArrayList<>();
		dataAttributeFactorySpy = new DataAttributeFactorySpy();

	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for DataAttributeFactory")
	public void testStartModuleThrowsErrorIfNoDataAttributeFactoryImplementations()
			throws Exception {
		starter.startUsingDataAttributeFactoryImplementations(dataAttributeFactoryImplementations);
	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for DataAttributeFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneDataAttributeFactoryImplementations()
			throws Exception {
		dataAttributeFactoryImplementations.add(new DataAttributeFactorySpy());
		dataAttributeFactoryImplementations.add(new DataAttributeFactorySpy());
		starter.startUsingDataAttributeFactoryImplementations(dataAttributeFactoryImplementations);
	}

	@Test
	public void testGetDataAttributeFactory() throws Exception {
		dataAttributeFactoryImplementations.add(dataAttributeFactorySpy);
		starter.startUsingDataAttributeFactoryImplementations(dataAttributeFactoryImplementations);
		assertSame(starter.getDataAttributeFactory(), dataAttributeFactorySpy);
	}
}
