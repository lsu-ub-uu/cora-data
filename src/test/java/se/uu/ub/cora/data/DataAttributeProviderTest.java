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

import se.uu.ub.cora.data.starter.DataAttributeModuleStarter;
import se.uu.ub.cora.data.starter.DataAttributeModuleStarterImp;
import se.uu.ub.cora.data.starter.DataInitializationException;

public class DataAttributeProviderTest {
	@BeforeMethod
	public void beforeMethod() {
		DataAttributeProvider.setDataAttributeFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataAttributeProvider> constructor = DataAttributeProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataAttributeProvider> constructor = DataAttributeProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataAttributeUsesExistingDataAttributeFactory() throws Exception {
		DataAttributeFactorySpy dataAttributeFactorySpy = new DataAttributeFactorySpy();
		DataAttributeProvider.setDataAttributeFactory(dataAttributeFactorySpy);
		String nameInData = "someNameInData";
		String value = "someValue";
		DataAttribute dataAttribute = DataAttributeProvider
				.getDataAttributeUsingNameInDataAndValue(nameInData, value);

		assertTrue(dataAttributeFactorySpy.withNameInDataAndValueWasCalled);
		assertEquals(dataAttributeFactorySpy.nameInData, nameInData);
		assertEquals(dataAttributeFactorySpy.value, value);
		assertSame(dataAttribute, dataAttributeFactorySpy.returnedDataAttribute);
	}

	@Test
	public void testStartingOfDataAttributeFactoryCanOnlyBeDoneByOneThreadAtATime()
			throws Exception {
		Method declaredMethod = DataAttributeProvider.class
				.getDeclaredMethod("ensureDataAttributeFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataAttributeModuleStarterSpy starter = startDataGroupModuleInitializerWithStarterSpy();
		DataAttributeProvider.getDataAttributeUsingNameInDataAndValue("someNameInData",
				"someValue");
		assertTrue(starter.startWasCalled);
	}

	private DataAttributeModuleStarterSpy startDataGroupModuleInitializerWithStarterSpy() {
		DataAttributeModuleStarter starter = new DataAttributeModuleStarterSpy();
		DataAttributeProvider.setStarter(starter);
		return (DataAttributeModuleStarterSpy) starter;
	}

	@Test
	public void testNonExceptionThrowingStartupForDataGroupAsLink() throws Exception {
		DataAttributeModuleStarterSpy starter = startDataGroupModuleInitializerWithStarterSpy();
		DataAttributeProvider.getDataAttributeUsingNameInDataAndValue("someNameInData",
				"someValue");
		assertTrue(starter.startWasCalled);
	}

	@Test
	public void testInitUsesDefaultAttributeModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataAttributeModuleStarter starter = DataAttributeProvider.getStarter();
		assertStarterIsDataAttributeModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataAttributeProvider.getDataAttributeUsingNameInDataAndValue("someNameInData",
					"someValue");
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataAttributeFactory");
	}

	private void assertStarterIsDataAttributeModuleStarter(DataAttributeModuleStarter starter) {
		assertTrue(starter instanceof DataAttributeModuleStarterImp);
	}

}
