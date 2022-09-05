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

import se.uu.ub.cora.data.spy.DataGroupSpy;
import se.uu.ub.cora.data.spy.DataRecordFactorySpy;
import se.uu.ub.cora.data.spy.DataRecordModuleStarterSpy;
import se.uu.ub.cora.data.starter.DataInitializationException;
import se.uu.ub.cora.data.starter.DataRecordModuleStarter;
import se.uu.ub.cora.data.starter.DataRecordModuleStarterImp;

public class DataRecordProviderTest {

	private DataGroupSpy dataGroup;

	@BeforeMethod
	public void beforeMethod() {
		DataRecordProvider.setDataRecordFactory(null);
		dataGroup = new DataGroupSpy("someNameInData");
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataRecordProvider> constructor = DataRecordProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataRecordProvider> constructor = DataRecordProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataRecordUsesExistingDataGroupFactory() throws Exception {
		DataRecordFactorySpy dataRecordFactorySpy = new DataRecordFactorySpy();
		DataRecordProvider.setDataRecordFactory(dataRecordFactorySpy);

		DataRecordProvider.getDataRecordWithDataGroup(dataGroup);

		assertEquals(dataRecordFactorySpy.dataGroup, dataGroup);
	}

	@Test
	public void testStartingOfDataRecordFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataRecordProvider.class
				.getDeclaredMethod("ensureDataRecordFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataRecordModuleStarterSpy starter = startDataRecordModuleInitializerWithStarterSpy();
		DataRecordProvider.getDataRecordWithDataGroup(dataGroup);
		assertTrue(starter.startWasCalled);
	}

	@Test
	public void testNonExceptionThrowingStartupForDataGroupAsLink() throws Exception {
		DataRecordModuleStarterSpy starter = startDataRecordModuleInitializerWithStarterSpy();
		DataRecordProvider.getDataRecordWithDataGroup(dataGroup);
		assertTrue(starter.startWasCalled);
	}

	private DataRecordModuleStarterSpy startDataRecordModuleInitializerWithStarterSpy() {
		DataRecordModuleStarter starter = new DataRecordModuleStarterSpy();
		DataRecordProvider.setStarter(starter);
		return (DataRecordModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataRecordModuleStarter starter = DataRecordProvider.getStarter();
		assertStarterIsDataGroupModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataRecordProvider.getDataRecordWithDataGroup(dataGroup);
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataRecordFactory");
	}

	private void assertStarterIsDataGroupModuleStarter(DataRecordModuleStarter starter) {
		assertTrue(starter instanceof DataRecordModuleStarterImp);
	}

}
