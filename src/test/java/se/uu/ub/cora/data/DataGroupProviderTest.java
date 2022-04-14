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

import se.uu.ub.cora.data.starter.DataGroupModuleStarter;
import se.uu.ub.cora.data.starter.DataGroupModuleStarterImp;
import se.uu.ub.cora.data.starter.DataInitializationException;

public class DataGroupProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataGroupProvider.setDataGroupFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataGroupProvider> constructor = DataGroupProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataGroupProvider> constructor = DataGroupProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataGroupUsesExistingDataGroupFactory() throws Exception {
		DataGroupFactorySpy dataGroupFactorySpy = new DataGroupFactorySpy();
		DataGroupProvider.setDataGroupFactory(dataGroupFactorySpy);
		String nameInData = "someNameInData";
		DataGroup dataGroup = DataGroupProvider.getDataGroupUsingNameInData(nameInData);

		assertTrue(dataGroupFactorySpy.withNameInDataWasCalled);
		assertEquals(dataGroupFactorySpy.nameInData, nameInData);
		assertSame(dataGroup, dataGroupFactorySpy.returnedDataGroup);
	}

	@Test
	public void testGetLinkAsDataGroupProviderUsesExistingDataGroupFactory() throws Exception {
		DataGroupFactorySpy dataGroupFactorySpy = new DataGroupFactorySpy();
		DataGroupProvider.setDataGroupFactory(dataGroupFactorySpy);
		String nameInData = "someNameInData";
		String recordType = "someRecordType";
		String recordId = "someRecordId";
		DataGroup dataGroup = DataGroupProvider
				.getDataGroupAsLinkUsingNameInDataTypeAndId(nameInData, recordType, recordId);

		assertTrue(dataGroupFactorySpy.asLinkWasCalled);
		assertEquals(dataGroupFactorySpy.nameInData, nameInData);
		assertEquals(dataGroupFactorySpy.recordType, recordType);
		assertEquals(dataGroupFactorySpy.recordId, recordId);
		assertSame(dataGroup, dataGroupFactorySpy.returnedDataGroup);
	}

	@Test
	public void testStartingOfDataGoupFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataGroupProvider.class
				.getDeclaredMethod("ensureDataGroupFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataGroupModuleStarterSpy starter = startDataGroupModuleInitializerWithStarterSpy();
		DataGroupProvider.getDataGroupUsingNameInData("someNameInData");
		assertTrue(starter.startWasCalled);
	}

	@Test
	public void testNonExceptionThrowingStartupForDataGroupAsLink() throws Exception {
		DataGroupModuleStarterSpy starter = startDataGroupModuleInitializerWithStarterSpy();
		DataGroupProvider.getDataGroupAsLinkUsingNameInDataTypeAndId("someNameInData", "someType",
				"someId");
		assertTrue(starter.startWasCalled);
	}

	private DataGroupModuleStarterSpy startDataGroupModuleInitializerWithStarterSpy() {
		DataGroupModuleStarter starter = new DataGroupModuleStarterSpy();
		DataGroupProvider.setStarter(starter);
		return (DataGroupModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataGroupModuleStarter starter = DataGroupProvider.getStarter();
		assertStarterIsDataGroupModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataGroupProvider.getDataGroupUsingNameInData("someNameInData");
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(), "No implementations found for DataGroupFactory");
	}

	private void assertStarterIsDataGroupModuleStarter(DataGroupModuleStarter starter) {
		assertTrue(starter instanceof DataGroupModuleStarterImp);
	}

}
