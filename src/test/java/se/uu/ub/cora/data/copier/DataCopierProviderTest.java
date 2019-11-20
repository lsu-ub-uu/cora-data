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
package se.uu.ub.cora.data.copier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAtomicSpy;
import se.uu.ub.cora.data.starter.DataCopierFactorySpy;
import se.uu.ub.cora.data.starter.DataCopierModuleStarter;
import se.uu.ub.cora.data.starter.DataCopierModuleStarterImp;
import se.uu.ub.cora.data.starter.DataInitializationException;

public class DataCopierProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataCopierProvider.setDataCopierFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataCopierProvider> constructor = DataCopierProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataCopierProvider> constructor = DataCopierProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetCopierUsesExistingCopierFactory() throws Exception {
		DataCopierFactorySpy copierFactorySpy = new DataCopierFactorySpy();
		DataCopierProvider.setDataCopierFactory(copierFactorySpy);
		DataAtomic dataAtomic = new DataAtomicSpy();
		DataCopier copier = DataCopierProvider.getDataCopierUsingDataElement(dataAtomic);

		assertTrue(copierFactorySpy.factorForDataElementCalled);
		assertSame(copierFactorySpy.dataElement, dataAtomic);
		assertSame(copierFactorySpy.copierReturnedFromFactory, copier);
	}

	@Test
	public void testStartingOfCopierFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataCopierProvider.class
				.getDeclaredMethod("ensureDataCopierFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	@Test
	public void testNonExceptionThrowingStartup() throws Exception {
		DataCopierModuleStarterSpy starter = startDataGroupModuleInitializerWithStarterSpy();
		DataAtomic dataAtomic = new DataAtomicSpy();
		DataCopierProvider.getDataCopierUsingDataElement(dataAtomic);
		assertTrue(starter.startWasCalled);
	}

	private DataCopierModuleStarterSpy startDataGroupModuleInitializerWithStarterSpy() {
		DataCopierModuleStarter starter = new DataCopierModuleStarterSpy();
		DataCopierProvider.setStarter(starter);
		return (DataCopierModuleStarterSpy) starter;
	}

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataCopierModuleStarter starter = DataCopierProvider.getStarter();
		assertStarterIsDataCopierModuleStarter(starter);
	}

	private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
		Exception caughtException = null;
		try {
			DataAtomic dataAtomic = new DataAtomicSpy();
			DataCopierProvider.getDataCopierUsingDataElement(dataAtomic);
		} catch (Exception e) {
			caughtException = e;
		}
		assertTrue(caughtException instanceof DataInitializationException);
		assertEquals(caughtException.getMessage(),
				"No implementations found for DataCopierFactory");
	}

	private void assertStarterIsDataCopierModuleStarter(DataCopierModuleStarter starter) {
		assertTrue(starter instanceof DataCopierModuleStarterImp);
	}
}
