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

import se.uu.ub.cora.data.starter.DataGroupModuleStarter;
import se.uu.ub.cora.data.starter.DataGroupModuleStarterImp;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public class DataGroupProvider {

	private static DataGroupFactory dataGroupFactory;
	private static DataGroupModuleStarter dataGroupModuleStarter = new DataGroupModuleStarterImp();

	private DataGroupProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataGroup getDataGroupUsingNameInData(String nameInData) {
		ensureDataGroupFactoryIsSet();
		return dataGroupFactory.factorUsingNameInData(nameInData);
	}

	private static synchronized void ensureDataGroupFactoryIsSet() {
		if (null == dataGroupFactory) {
			getDataGroupFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataGroupFactoryImpUsingModuleStarter() {
		Iterable<DataGroupFactory> dataGroupFactoryImplementations = ServiceLoader
				.load(DataGroupFactory.class);
		dataGroupModuleStarter
				.startUsingDataGroupFactoryImplementations(dataGroupFactoryImplementations);
		dataGroupFactory = dataGroupModuleStarter.getDataGroupFactory();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataGroup getDataGroupAsLinkUsingNameInDataTypeAndId(String nameInData,
			String recordType, String recordId) {
		ensureDataGroupFactoryIsSet();
		return dataGroupFactory.factorAsLinkWithNameInDataTypeAndId(nameInData, recordType,
				recordId);
	}

	/**
	 * Sets a DataGroupFactory that will be used to factor dataGroups for Classes. This possibility
	 * to set a DataGroupFactory is provided to enable testing of logging in other classes and is
	 * not intented to be used in production. The DataGroupFactory to use should be provided through
	 * an implementation of DataGroupFactory in a seperate java module.
	 * 
	 * @param dataGroupFactory
	 *            A DataGroupFactory to use to create dataGroups for testing
	 */
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static void setDataGroupFactory(DataGroupFactory dataGroupFactory) {
		DataGroupProvider.dataGroupFactory = dataGroupFactory;

	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static DataGroupModuleStarter getStarter() {
		return dataGroupModuleStarter;
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static void setStarter(DataGroupModuleStarter starter) {
		dataGroupModuleStarter = starter;
	}

}
