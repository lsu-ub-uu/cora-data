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

import java.util.ServiceLoader;

import se.uu.ub.cora.data.starter.DataAttributeModuleStarter;
import se.uu.ub.cora.data.starter.DataAttributeModuleStarterImp;

public class DataAttributeProvider {

	private static DataAttributeFactory dataAttributeFactory;
	private static DataAttributeModuleStarter dataAttributeModuleStarter = new DataAttributeModuleStarterImp();

	private DataAttributeProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataAttribute getDataAttributeUsingNameInDataAndValue(String nameInData,
			String value) {
		ensureDataAttributeFactoryIsSet();
		return dataAttributeFactory.factorUsingNameInDataAndValue(nameInData, value);
	}

	private static synchronized void ensureDataAttributeFactoryIsSet() {
		if (null == dataAttributeFactory) {
			getDataAttributeFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataAttributeFactoryImpUsingModuleStarter() {
		Iterable<DataAttributeFactory> dataGroupAttributeImplementations = ServiceLoader
				.load(DataAttributeFactory.class);
		dataAttributeModuleStarter
				.startUsingDataAttributeFactoryImplementations(dataGroupAttributeImplementations);
		dataAttributeFactory = dataAttributeModuleStarter.getDataAttributeFactory();
	}

	/**
	 * Sets a setDataAttributeFactory that will be used to factor dataAttributes for Classes. This
	 * possibility to set a DataAttributeFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataAttributeFactory to use should
	 * be provided through an implementation of DataAttributeFactory in a seperate java module.
	 * 
	 * @param dataAttributeFactory
	 *            A DataAttributeFactory to use to create dataAttributes for testing
	 */
	public static void setDataAttributeFactory(DataAttributeFactory dataAttributeFactory) {
		DataAttributeProvider.dataAttributeFactory = dataAttributeFactory;
	}

	static DataAttributeModuleStarter getStarter() {
		return dataAttributeModuleStarter;
	}

	static void setStarter(DataAttributeModuleStarter starter) {
		dataAttributeModuleStarter = starter;
	}
}
