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

import se.uu.ub.cora.data.DataResourceLinkFactory;

public class DataResourceLinkModuleStarterTest {
	DataResourceLinkModuleStarter starter;
	List<DataResourceLinkFactory> dataResourceFactoryImplementations;
	DataResourceLinkFactorySpy dataResourceLinkFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		starter = new DataResourceLinkModuleStarterImp();
		dataResourceFactoryImplementations = new ArrayList<>();
		dataResourceLinkFactorySpy = new DataResourceLinkFactorySpy();

	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "No implementations found for DataResourceLinkFactory")
	public void testStartModuleThrowsErrorIfNoDataRecordFactoryImplementations() throws Exception {
		starter.startUsingDataResourceLinkFactoryImplementations(dataResourceFactoryImplementations);
	}

	@Test(expectedExceptions = DataInitializationException.class, expectedExceptionsMessageRegExp = ""
			+ "More than one implementation found for DataResourceLinkFactory")
	public void testStartModuleThrowsErrorIfMoreThanOneDataRecordFactoryImplementations()
			throws Exception {
		dataResourceFactoryImplementations.add(new DataResourceLinkFactorySpy());
		dataResourceFactoryImplementations.add(new DataResourceLinkFactorySpy());
		starter.startUsingDataResourceLinkFactoryImplementations(dataResourceFactoryImplementations);
	}

	@Test
	public void testGetDataRecordFactory() throws Exception {
		dataResourceFactoryImplementations.add(dataResourceLinkFactorySpy);
		starter.startUsingDataResourceLinkFactoryImplementations(dataResourceFactoryImplementations);
		assertSame(starter.getDataResourceLinkFactory(), dataResourceLinkFactorySpy);
	}
}
