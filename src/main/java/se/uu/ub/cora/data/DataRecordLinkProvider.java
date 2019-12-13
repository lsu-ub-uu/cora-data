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

public class DataRecordLinkProvider {

	private static DataRecordLinkFactory dataGroupFactory;
	private static DataRecordLinkModuleStarter dataGroupModuleStarter = new DataRecordLinkModuleStarterImp();

	private DataRecordLinkProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataRecordLink getDataRecordLinkUsingNameInData(String nameInData) {
		ensureDataRecordLinkFactoryIsSet();
		return dataGroupFactory.factorUsingNameInData(nameInData);
	}

	private static synchronized void ensureDataRecordLinkFactoryIsSet() {
		if (null == dataGroupFactory) {
			getDataRecordLinkFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataRecordLinkFactoryImpUsingModuleStarter() {
		Iterable<DataRecordLinkFactory> dataGroupFactoryImplementations = ServiceLoader
				.load(DataRecordLinkFactory.class);
		dataGroupModuleStarter
				.startUsingDataRecordLinkFactoryImplementations(dataGroupFactoryImplementations);
		dataGroupFactory = dataGroupModuleStarter.getDataRecordLinkFactory();
	}

	public static DataRecordLink getDataRecordLinkAsLinkUsingNameInDataTypeAndId(String nameInData,
			String recordType, String recordId) {
		ensureDataRecordLinkFactoryIsSet();
		return dataGroupFactory.factorAsLinkWithNameInDataTypeAndId(nameInData, recordType,
				recordId);
	}

	/**
	 * Sets a DataRecordLinkFactory that will be used to factor dataGroups for Classes. This
	 * possibility to set a DataRecordLinkFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataRecordLinkFactory to use should
	 * be provided through an implementation of DataRecordLinkFactory in a seperate java module.
	 * 
	 * @param dataGroupFactory
	 *            A DataRecordLinkFactory to use to create dataGroups for testing
	 */
	public static void setDataRecordLinkFactory(DataRecordLinkFactory dataGroupFactory) {
		DataRecordLinkProvider.dataGroupFactory = dataGroupFactory;

	}

	static DataRecordLinkModuleStarter getStarter() {
		return dataGroupModuleStarter;
	}

	static void setStarter(DataRecordLinkModuleStarter starter) {
		dataGroupModuleStarter = starter;
	}

}
