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

import se.uu.ub.cora.data.starter.DataRecordModuleStarter;
import se.uu.ub.cora.data.starter.DataRecordModuleStarterImp;

public class DataRecordProvider {

	private static DataRecordFactory dataRecordFactory;
	private static DataRecordModuleStarter dataRecordModuleStarter = new DataRecordModuleStarterImp();

	private DataRecordProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataRecord getDataRecordWithDataGroup(DataGroup dataGroup) {
		ensureDataRecordFactoryIsSet();
		return dataRecordFactory.factorUsingDataGroup(dataGroup);
	}

	/**
	 * Sets a DataRecordFactory that will be used to factor dataRecords for Classes. This
	 * possibility to set a DataRecordFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataRecordFactory to use should be
	 * provided through an implementation of DataRecordFactory in a seperate java module.
	 * 
	 * @param dataRecordFactory
	 *            A DataRecordFactory to use to create dataRecords for testing
	 */
	public static void setDataRecordFactory(DataRecordFactory dataRecordFactory) {
		DataRecordProvider.dataRecordFactory = dataRecordFactory;

	}

	private static synchronized void ensureDataRecordFactoryIsSet() {
		if (null == dataRecordFactory) {
			getDataRecordFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataRecordFactoryImpUsingModuleStarter() {
		Iterable<DataRecordFactory> dataRecordFactoryImplementations = ServiceLoader
				.load(DataRecordFactory.class);
		dataRecordModuleStarter
				.startUsingDataRecordFactoryImplementations(dataRecordFactoryImplementations);
		dataRecordFactory = dataRecordModuleStarter.getDataRecordFactory();
	}

	public static void setStarter(DataRecordModuleStarter starter) {
		dataRecordModuleStarter = starter;

	}

	public static DataRecordModuleStarter getStarter() {
		return dataRecordModuleStarter;
	}
}
