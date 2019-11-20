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

import se.uu.ub.cora.data.converter.JsonToDataConverterFactory;

public class JsonToDataConverterModuleStarterImp implements JsonToDataConverterModuleStarter {

	private JsonToDataConverterFactory jsonToDataConverterFactory;

	@Override
	public void startUsingConverterFactoryImplementations(
			Iterable<JsonToDataConverterFactory> converterFactoryImplementations) {
		jsonToDataConverterFactory = getImplementationThrowErrorIfNoneOrMoreThanOne(
				converterFactoryImplementations, "JsonToDataConverterFactory");

	}

	private <T extends Object> T getImplementationThrowErrorIfNoneOrMoreThanOne(
			Iterable<T> implementations, String interfaceClassName) {
		T implementation = null;
		int noOfImplementationsFound = 0;
		for (T currentImplementation : implementations) {
			noOfImplementationsFound++;
			implementation = currentImplementation;
		}
		throwErrorIfNone(noOfImplementationsFound, interfaceClassName);
		throwErrorIfMoreThanOne(noOfImplementationsFound, interfaceClassName);
		return implementation;
	}

	private void throwErrorIfNone(int noOfImplementationsFound, String interfaceClassName) {
		if (noOfImplementationsFound == 0) {
			throw new DataInitializationException(
					"No implementations found for " + interfaceClassName);
		}
	}

	private void throwErrorIfMoreThanOne(int noOfImplementationsFound, String interfaceClassName) {
		if (noOfImplementationsFound > 1) {
			throw new DataInitializationException(
					"More than one implementation found for " + interfaceClassName);
		}
	}

	@Override
	public JsonToDataConverterFactory getJsonToDataConverterFactory() {
		return jsonToDataConverterFactory;
	}

}
