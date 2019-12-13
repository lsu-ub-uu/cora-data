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

import se.uu.ub.cora.data.starter.DataListModuleStarter;
import se.uu.ub.cora.data.starter.DataListModuleStarterImp;

public class DataListProvider {

	private static DataListFactory dataListFactory;
	private static DataListModuleStarter dataListModuleStarter = new DataListModuleStarterImp();

	private DataListProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataList getDataListWithNameOfDataType(String nameOfDataType) {
		ensureDataListFactoryIsSet();
		return dataListFactory.factorWithContainDataOfType(nameOfDataType);
	}

	/**
	 * Sets a DataRecordFactory that will be used to factor dataRecords for Classes. This
	 * possibility to set a DataRecordFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataRecordFactory to use should be
	 * provided through an implementation of DataRecordFactory in a seperate java module.
	 * 
	 * @param dataListFactory
	 *            A DataRecordFactory to use to create dataRecords for testing
	 */
	public static void setDataListFactory(DataListFactory dataListFactory) {
		DataListProvider.dataListFactory = dataListFactory;

	}

	private static synchronized void ensureDataListFactoryIsSet() {
		if (null == dataListFactory) {
			getDataListFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataListFactoryImpUsingModuleStarter() {
		Iterable<DataListFactory> dataListFactoryImplementations = ServiceLoader
				.load(DataListFactory.class);
		dataListModuleStarter
				.startUsingDataListFactoryImplementations(dataListFactoryImplementations);
		dataListFactory = dataListModuleStarter.getDataListFactory();
	}

	static void setStarter(DataListModuleStarter starter) {
		dataListModuleStarter = starter;

	}

	static DataListModuleStarter getStarter() {
		return dataListModuleStarter;
	}
}
