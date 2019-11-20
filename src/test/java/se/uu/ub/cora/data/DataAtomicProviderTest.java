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

import se.uu.ub.cora.data.starter.DataAtomicModuleStarter;
import se.uu.ub.cora.data.starter.DataAtomicModuleStarterImp;
import se.uu.ub.cora.data.starter.DataInitializationException;

public class DataAtomicProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataAtomicProvider.setDataAtomicFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataAtomicProvider> constructor = DataAtomicProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataAtomicProvider> constructor = DataAtomicProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testDataAtomicProviderUsesExistingDataAtomicFactory() throws Exception {
		DataAtomicFactorySpy dataAtomicFactorySpy = new DataAtomicFactorySpy();
		DataAtomicProvider.setDataAtomicFactory(dataAtomicFactorySpy);
		String nameInData = "someNameInData";
		String value = "someValue";
		DataAtomic dataAtomic = DataAtomicProvider.getDataAtomicUsingNameInDataAndValue(nameInData,
				value);

		assertTrue(dataAtomicFactorySpy.withNameInDataAndValueWasCalled);
		assertEquals(dataAtomicFactorySpy.nameInData, nameInData);
		assertEquals(dataAtomicFactorySpy.value, value);
		assertSame(dataAtomic, dataAtomicFactorySpy.returnedDataAtomic);
	}

	@Test
	public void testStartingOfDataAtomicFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataAtomicProvider.class
				.getDeclaredMethod("ensureDataAtomicFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataAtomicModuleStarterSpy starter = startDataAtomicModuleInitializerWithStarterSpy();
		DataAtomicProvider.getDataAtomicUsingNameInDataAndValue("someNameInData", "someValue");
		assertTrue(starter.startWasCalled);
	}

	private DataAtomicModuleStarterSpy startDataAtomicModuleInitializerWithStarterSpy() {
		DataAtomicModuleStarter starter = new DataAtomicModuleStarterSpy();
		DataAtomicProvider.setStarter(starter);
		return (DataAtomicModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataAtomicModuleStarter starter = DataAtomicProvider.getStarter();
		assertStarterIsDataAtomicModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataAtomicProvider.getDataAtomicUsingNameInDataAndValue("someNameInData", "someValue");
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataAtomicFactory");
	}

	private void assertStarterIsDataAtomicModuleStarter(DataAtomicModuleStarter starter) {
		assertTrue(starter instanceof DataAtomicModuleStarterImp);
	}

}
