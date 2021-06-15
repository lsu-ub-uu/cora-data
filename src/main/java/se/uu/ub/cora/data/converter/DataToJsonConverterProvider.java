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

import java.util.ServiceLoader;

import se.uu.ub.cora.data.Convertible;
import se.uu.ub.cora.data.starter.DataToJsonConverterModuleStarter;
import se.uu.ub.cora.data.starter.DataToJsonConverterModuleStarterImp;
import se.uu.ub.cora.json.builder.JsonBuilderFactory;
import se.uu.ub.cora.json.builder.org.OrgJsonBuilderFactoryAdapter;

public class DataToJsonConverterProvider {

	private static DataToJsonConverterFactory dataToJsonConverterFactory;
	private static DataToJsonConverterModuleStarter jsonToDataConverterModuleStarter = new DataToJsonConverterModuleStarterImp();

	private DataToJsonConverterProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	// not tested
	// TODO: implement like this..
	// public static DataToJsonConverterFactory getDataToJsonConverterFactory() {
	// ensureConverterFactoryIsSet();
	// DataToJsonConverterFactory factory =
	// dataToJsonConverterFactoryFactory.createFactoryWithoutUrl();
	// return factory;
	// }
	// public static DataToJsonConverterFactory getDataToJsonConverterFactoryForUrl(String baseUrl)
	// {
	// ensureConverterFactoryIsSet();
	// DataToJsonConverterFactory factory =
	// dataToJsonConverterFactoryFactory.createFactoryWithUrl(baseUrl);
	// factory.factor(null)
	// return factory;
	// }
	//
	// private static void getConverterFactoryImpUsingModuleStarter() {
	// Iterable<DataToJsonConverterFactory> dataToJsonConverterFactoryImplementations =
	// ServiceLoader
	// .load(DataToJsonConverterFactoryFactory.class);
	// jsonToDataConverterModuleStarter.startUsingConverterFactoryImplementations(
	// dataToJsonConverterFactoryImplementations);
	// dataToJsonConverterFactory = jsonToDataConverterModuleStarter
	// .getDataToJsonConverterFactory();
	// }
	// end not tested

	public static DataToJsonConverter getConverterUsingDataPart(Convertible convertible) {
		ensureConverterFactoryIsSet();
		JsonBuilderFactory jsonBuilderFactory = new OrgJsonBuilderFactoryAdapter();
		return dataToJsonConverterFactory.factor(convertible);
	}

	private static synchronized void ensureConverterFactoryIsSet() {
		if (null == dataToJsonConverterFactory) {
			getConverterFactoryImpUsingModuleStarter();
		}
	}

	private static void getConverterFactoryImpUsingModuleStarter() {
		Iterable<DataToJsonConverterFactory> dataToJsonConverterFactoryImplementations = ServiceLoader
				.load(DataToJsonConverterFactory.class);
		jsonToDataConverterModuleStarter.startUsingConverterFactoryImplementations(
				dataToJsonConverterFactoryImplementations);
		dataToJsonConverterFactory = jsonToDataConverterModuleStarter
				.getDataToJsonConverterFactory();
	}

	/**
	 * Sets a DataToJsonConverterFactory that will be used to factor jsonToDataConverters for
	 * Classes. This possibility to set a DataToJsonConverterFactory is provided to enable testing
	 * of logging in other classes and is not intented to be used in production. The
	 * DataGroupFactory to use should be provided through an implementation of
	 * DataToJsonConverterFactory in a seperate java module.
	 * 
	 * @param dataToJsonConverterFactory
	 *            A DataToJsonConverterFactory to use to create jsonToDataConverters for testing
	 */
	public static void setDataToJsonConverterFactory(
			DataToJsonConverterFactory dataToJsonConverterFactory) {
		DataToJsonConverterProvider.dataToJsonConverterFactory = dataToJsonConverterFactory;

	}

	static DataToJsonConverterModuleStarter getStarter() {
		return jsonToDataConverterModuleStarter;
	}

	static void setStarter(DataToJsonConverterModuleStarter starter) {
		jsonToDataConverterModuleStarter = starter;
	}
}
