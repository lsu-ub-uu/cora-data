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

import se.uu.ub.cora.data.starter.DataRecordLinkModuleStarter;
import se.uu.ub.cora.data.starter.DataRecordLinkModuleStarterImp;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public class DataRecordLinkProvider {

	private static DataRecordLinkFactory dataRecordLinkFactory;
	private static DataRecordLinkModuleStarter dataRecordLinkModuleStarter = new DataRecordLinkModuleStarterImp();

	private DataRecordLinkProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataRecordLink getDataRecordLinkUsingNameInData(String nameInData) {
		ensureDataRecordLinkFactoryIsSet();
		return dataRecordLinkFactory.factorUsingNameInData(nameInData);
	}

	private static synchronized void ensureDataRecordLinkFactoryIsSet() {
		if (null == dataRecordLinkFactory) {
			getDataRecordLinkFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataRecordLinkFactoryImpUsingModuleStarter() {
		Iterable<DataRecordLinkFactory> dataRecordLinkFactoryImplementations = ServiceLoader
				.load(DataRecordLinkFactory.class);
		dataRecordLinkModuleStarter.startUsingDataRecordLinkFactoryImplementations(
				dataRecordLinkFactoryImplementations);
		dataRecordLinkFactory = dataRecordLinkModuleStarter.getDataRecordLinkFactory();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataRecordLink getDataRecordLinkAsLinkUsingNameInDataTypeAndId(String nameInData,
			String recordType, String recordId) {
		ensureDataRecordLinkFactoryIsSet();
		return dataRecordLinkFactory.factorAsLinkWithNameInDataTypeAndId(nameInData, recordType,
				recordId);
	}

	/**
	 * Sets a DataRecordLinkFactory that will be used to factor dataRecordLinks for Classes. This
	 * possibility to set a DataRecordLinkFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataRecordLinkFactory to use should
	 * be provided through an implementation of DataRecordLinkFactory in a seperate java module.
	 * 
	 * @param dataRecordLinkFactory
	 *            A DataRecordLinkFactory to use to create dataRecordlinks for testing
	 */
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static void setDataRecordLinkFactory(DataRecordLinkFactory dataRecordLinkFactory) {
		DataRecordLinkProvider.dataRecordLinkFactory = dataRecordLinkFactory;

	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static DataRecordLinkModuleStarter getStarter() {
		return dataRecordLinkModuleStarter;
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static void setStarter(DataRecordLinkModuleStarter starter) {
		dataRecordLinkModuleStarter = starter;
	}

}
