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
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.starter.DataInitializationException;
import se.uu.ub.cora.data.starter.DataListFactorySpy;
import se.uu.ub.cora.data.starter.DataListModuleStarter;
import se.uu.ub.cora.data.starter.DataListModuleStarterImp;

public class DataListProviderTest {

	private String nameOfDataType;

	@BeforeMethod
	public void beforeMethod() {
		DataListProvider.setDataListFactory(null);
		nameOfDataType = "someDataType";
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataListProvider> constructor = DataListProvider.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataListProvider> constructor = DataListProvider.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataListUsesExistingDataGroupFactory() throws Exception {
		DataListFactorySpy dataListFactorySpy = new DataListFactorySpy();
		DataListProvider.setDataListFactory(dataListFactorySpy);

		DataListProvider.getDataListWithNameOfDataType(nameOfDataType);

		assertEquals(dataListFactorySpy.nameOfDataType, nameOfDataType);
	}

	@Test
	public void testStartingOfDataListFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataListProvider.class
				.getDeclaredMethod("ensureDataListFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataListModuleStarterSpy starter = startDataListModuleInitializerWithStarterSpy();
		DataListProvider.getDataListWithNameOfDataType(nameOfDataType);
		assertTrue(starter.startWasCalled);
	}

	@Test
	public void testNonExceptionThrowingStartupForDataGroupAsLink() throws Exception {
		DataListModuleStarterSpy starter = startDataListModuleInitializerWithStarterSpy();
		DataListProvider.getDataListWithNameOfDataType(nameOfDataType);
		assertTrue(starter.startWasCalled);
	}

	private DataListModuleStarterSpy startDataListModuleInitializerWithStarterSpy() {
		DataListModuleStarter starter = new DataListModuleStarterSpy();
		DataListProvider.setStarter(starter);
		return (DataListModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataListModuleStarter starter = DataListProvider.getStarter();
		assertStarterIsDataGroupModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataListProvider.getDataListWithNameOfDataType(nameOfDataType);
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(), "No implementations found for DataListFactory");
	}

	private void assertStarterIsDataGroupModuleStarter(DataListModuleStarter starter) {
		assertTrue(starter instanceof DataListModuleStarterImp);
	}

}
