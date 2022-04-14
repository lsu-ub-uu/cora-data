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

import se.uu.ub.cora.data.starter.DataResourceLinkModuleStarter;
import se.uu.ub.cora.data.starter.DataResourceLinkModuleStarterImp;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public class DataResourceLinkProvider {

	private static DataResourceLinkFactory dataResourceLinkFactory;
	private static DataResourceLinkModuleStarter dataResourceLinkModuleStarter = new DataResourceLinkModuleStarterImp();

	private DataResourceLinkProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataResourceLink getDataResourceLinkUsingNameInData(String nameInData) {
		ensureDataResourceLinkFactoryIsSet();
		return dataResourceLinkFactory.factorUsingNameInData(nameInData);
	}

	private static synchronized void ensureDataResourceLinkFactoryIsSet() {
		if (null == dataResourceLinkFactory) {
			getDataResourceLinkFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataResourceLinkFactoryImpUsingModuleStarter() {
		Iterable<DataResourceLinkFactory> dataResourceLinkFactoryImplementations = ServiceLoader
				.load(DataResourceLinkFactory.class);
		dataResourceLinkModuleStarter.startUsingDataResourceLinkFactoryImplementations(
				dataResourceLinkFactoryImplementations);
		dataResourceLinkFactory = dataResourceLinkModuleStarter.getDataResourceLinkFactory();
	}

	/**
	 * Sets a DataResourceLinkFactory that will be used to factor dataResourceLinks for Classes.
	 * This possibility to set a DataResourceLinkFactory is provided to enable testing of logging in
	 * other classes and is not intented to be used in production. The DataResourceLinkFactory to
	 * use should be provided through an implementation of DataResourceLinkFactory in a seperate
	 * java module.
	 * 
	 * @param dataResourceLinkFactory
	 *            A DataResourceLinkFactory to use to create dataResourcelinks for testing
	 */
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static void setDataResourceLinkFactory(DataResourceLinkFactory dataResourceLinkFactory) {
		DataResourceLinkProvider.dataResourceLinkFactory = dataResourceLinkFactory;

	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static DataResourceLinkModuleStarter getStarter() {
		return dataResourceLinkModuleStarter;
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static void setStarter(DataResourceLinkModuleStarter starter) {
		dataResourceLinkModuleStarter = starter;
	}

}
