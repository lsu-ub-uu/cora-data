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
import se.uu.ub.cora.data.starter.DataRecordLinkFactorySpy;
import se.uu.ub.cora.data.starter.DataRecordLinkModuleStarter;
import se.uu.ub.cora.data.starter.DataRecordLinkModuleStarterImp;

public class DataRecordLinkProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataGroupProvider.setDataGroupFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataRecordLinkProvider> constructor = DataRecordLinkProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataRecordLinkProvider> constructor = DataRecordLinkProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetDataRecordLinkUsesExistingDataRecordLinkFactory() throws Exception {
		DataRecordLinkFactorySpy dataGroupFactorySpy = new DataRecordLinkFactorySpy();
		DataRecordLinkProvider.setDataRecordLinkFactory(dataGroupFactorySpy);
		String nameInData = "someNameInData";
		DataRecordLink dataGroup = DataRecordLinkProvider
				.getDataRecordLinkUsingNameInData(nameInData);

		assertTrue(dataGroupFactorySpy.withNameInDataWasCalled);
		assertEquals(dataGroupFactorySpy.nameInData, nameInData);
		assertSame(dataGroup, dataGroupFactorySpy.returnedDataRecordLink);
	}

	@Test
	public void testGetLinkAsDataRecordLinkProviderUsesExistingDataRecordLinkFactory()
			throws Exception {
		DataRecordLinkFactorySpy dataGroupFactorySpy = new DataRecordLinkFactorySpy();
		DataRecordLinkProvider.setDataRecordLinkFactory(dataGroupFactorySpy);
		String nameInData = "someNameInData";
		String recordType = "someRecordType";
		String recordId = "someRecordId";
		DataRecordLink dataGroup = DataRecordLinkProvider
				.getDataRecordLinkAsLinkUsingNameInDataTypeAndId(nameInData, recordType, recordId);

		assertTrue(dataGroupFactorySpy.asLinkWasCalled);
		assertEquals(dataGroupFactorySpy.nameInData, nameInData);
		assertSame(dataGroup, dataGroupFactorySpy.returnedDataRecordLink);
	}

	@Test
	public void testStartingOfDataGoupFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataRecordLinkProvider.class
				.getDeclaredMethod("ensureDataRecordLinkFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataRecordLinkModuleStarterSpy starter = startDataRecordLinkModuleInitializerWithStarterSpy();
		DataRecordLinkProvider.getDataRecordLinkUsingNameInData("someNameInData");
		assertTrue(starter.startWasCalled);
	}

	@Test
	public void testNonExceptionThrowingStartupForDataRecordLinkAsLink() throws Exception {
		DataRecordLinkModuleStarterSpy starter = startDataRecordLinkModuleInitializerWithStarterSpy();
		DataRecordLinkProvider.getDataRecordLinkAsLinkUsingNameInDataTypeAndId("someNameInData",
				"someType", "someId");
		assertTrue(starter.startWasCalled);
	}

	private DataRecordLinkModuleStarterSpy startDataRecordLinkModuleInitializerWithStarterSpy() {
		DataRecordLinkModuleStarter starter = new DataRecordLinkModuleStarterSpy();
		DataRecordLinkProvider.setStarter(starter);
		return (DataRecordLinkModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataRecordLinkModuleStarter starter = DataRecordLinkProvider.getStarter();
		assertStarterIsDataRecordLinkModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataRecordLinkProvider.getDataRecordLinkUsingNameInData("someNameInData");
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataRecordLinkFactory");
	}

	private void assertStarterIsDataRecordLinkModuleStarter(DataRecordLinkModuleStarter starter) {
		assertTrue(starter instanceof DataRecordLinkModuleStarterImp);
	}

}
