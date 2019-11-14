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
package se.uu.ub.cora.data.converter;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataAtomicSpy;
import se.uu.ub.cora.data.starter.DataToJsonConverterFactorySpy;
import se.uu.ub.cora.data.starter.DataToJsonConverterModuleStarter;
import se.uu.ub.cora.data.starter.DataToJsonConverterModuleStarterImp;
import se.uu.ub.cora.json.builder.org.OrgJsonBuilderFactoryAdapter;
import se.uu.ub.cora.json.parser.JsonValue;
import se.uu.ub.cora.json.parser.org.OrgJsonParser;

public class DataToJsonConverterProviderTest {

	@BeforeMethod
	public void beforeMethod() {
		DataToJsonConverterProvider.setDataToJsonConverterFactory(null);
	}

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<DataToJsonConverterProvider> constructor = DataToJsonConverterProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test(expectedExceptions = InvocationTargetException.class)
	public void testPrivateConstructorInvoke() throws Exception {
		Constructor<DataToJsonConverterProvider> constructor = DataToJsonConverterProvider.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGetConverterUsesExistingConverterFactory() throws Exception {
		DataToJsonConverterFactorySpy converterFactorySpy = new DataToJsonConverterFactorySpy();
		DataToJsonConverterProvider.setDataToJsonConverterFactory(converterFactorySpy);
		// JsonValue jsonValue = createJsonValue();
		DataAtomicSpy dataAtomicSpy = new DataAtomicSpy("someNameInData", "someValue");
		DataToJsonConverter converter = DataToJsonConverterProvider
				.getConverterUsingDataPart(dataAtomicSpy);

		assertTrue(converterFactorySpy.getConverterCalled);
		assertTrue(converterFactorySpy.factory instanceof OrgJsonBuilderFactoryAdapter);
		// assertSame(converter, converterFactorySpy.returnedConverter);
	}

	private JsonValue createJsonValue() {
		OrgJsonParser jsonParser = new OrgJsonParser();
		String json = "{\"name\":\"groupNameInData\", \"children\":[]}";
		JsonValue jsonValue = jsonParser.parseString(json);
		return jsonValue;
	}

	@Test
	public void testStartingOfConverterFactoryCanOnlyBeDoneByOneThreadAtATime() throws Exception {
		Method declaredMethod = DataToJsonConverterProvider.class
				.getDeclaredMethod("ensureConverterFactoryIsSet");
		assertTrue(Modifier.isSynchronized(declaredMethod.getModifiers()));
	}

	// @Test
	// public void testNonExceptionThrowingStartup() throws Exception {
	// DataToJsonConverterModuleStarterSpy starter =
	// startDataGroupModuleInitializerWithStarterSpy();
	// JsonValue jsonValue = createJsonValue();
	// DataToJsonConverterProvider.getConverterUsingDataPart(jsonValue);
	// assertTrue(starter.startWasCalled);
	// }
	//
	// private DataToJsonConverterModuleStarterSpy startDataGroupModuleInitializerWithStarterSpy() {
	// DataToJsonConverterModuleStarter starter = new DataToJsonConverterModuleStarterSpy();
	// DataToJsonConverterProvider.setStarter(starter);
	// return (DataToJsonConverterModuleStarterSpy) starter;
	// }

	@Test
	public void testInitUsesDefaultLoggerModuleStarter() throws Exception {
		makeSureErrorIsThrownAsNoImplementationsExistInThisModule();
		DataToJsonConverterModuleStarter starter = DataToJsonConverterProvider.getStarter();
		assertStarterIsDataGroupModuleStarter(starter);
	}

	// private void makeSureErrorIsThrownAsNoImplementationsExistInThisModule() {
	// Exception caughtException = null;
	// try {
	// DataToJsonConverterProvider.getConverterUsingDataPart(createJsonValue());
	// } catch (Exception e) {
	// caughtException = e;
	// }
	// assertTrue(caughtException instanceof DataInitializationException);
	// assertEquals(caughtException.getMessage(),
	// "No implementations found for DataToJsonConverterFactory");
	// }

	private void assertStarterIsDataGroupModuleStarter(DataToJsonConverterModuleStarter starter) {
		assertTrue(starter instanceof DataToJsonConverterModuleStarterImp);
	}
}
