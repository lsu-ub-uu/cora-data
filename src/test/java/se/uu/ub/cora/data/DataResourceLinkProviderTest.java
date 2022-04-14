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
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.starter.DataInitializationException;
import se.uu.ub.cora.data.starter.DataResourceLinkFactorySpy;
import se.uu.ub.cora.data.starter.DataResourceLinkModuleStarter;
import se.uu.ub.cora.data.starter.DataResourceLinkModuleStarterImp;

public class DataResourceLinkProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataResourceLinkProvider.setDataResourceLinkFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataResourceLinkProvider> constructor = DataResourceLinkProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataResourceLinkProvider> constructor = DataResourceLinkProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataResourceLinkUsesExistingDataResourceLinkFactory() throws Exception {
		DataResourceLinkFactorySpy dataResourceLinkFactorySpy = new DataResourceLinkFactorySpy();
		DataResourceLinkProvider.setDataResourceLinkFactory(dataResourceLinkFactorySpy);
		String nameInData = "someNameInData";
		DataResourceLink dataResourceLink = DataResourceLinkProvider
				.getDataResourceLinkUsingNameInData(nameInData);

		assertTrue(dataResourceLinkFactorySpy.withNameInDataWasCalled);
		assertEquals(dataResourceLinkFactorySpy.nameInData, nameInData);
		assertSame(dataResourceLink, dataResourceLinkFactorySpy.factoredResourceLink);
	}

	@Test
	public void testStartingOfDataGoupFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataResourceLinkProvider.class
				.getDeclaredMethod("ensureDataResourceLinkFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataResourceLinkModuleStarterSpy starter = startDataResourceLinkModuleInitializerWithStarterSpy();
		DataResourceLinkProvider.getDataResourceLinkUsingNameInData("someNameInData");
		assertTrue(starter.startWasCalled);
	}

	private DataResourceLinkModuleStarterSpy startDataResourceLinkModuleInitializerWithStarterSpy() {
		DataResourceLinkModuleStarter starter = new DataResourceLinkModuleStarterSpy();
		DataResourceLinkProvider.setStarter(starter);
		return (DataResourceLinkModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataResourceLinkModuleStarter starter = DataResourceLinkProvider.getStarter();
		assertStarterIsDataResourceLinkModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataResourceLinkProvider.getDataResourceLinkUsingNameInData("someNameInData");
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataResourceLinkFactory");
	}

	private void assertStarterIsDataResourceLinkModuleStarter(
			DataResourceLinkModuleStarter starter) {
		assertTrue(starter instanceof DataResourceLinkModuleStarterImp);
	}

}
