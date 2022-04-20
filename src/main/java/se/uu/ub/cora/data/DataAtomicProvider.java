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

import se.uu.ub.cora.data.starter.DataAtomicModuleStarter;
import se.uu.ub.cora.data.starter.DataAtomicModuleStarterImp;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public class DataAtomicProvider {
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	private static DataAtomicFactory dataAtomicFactory;
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	private static DataAtomicModuleStarter dataAtomicModuleStarter = new DataAtomicModuleStarterImp();

	private DataAtomicProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataAtomic getDataAtomicUsingNameInDataAndValue(String nameInData, String value) {
		ensureDataAtomicFactoryIsSet();
		return dataAtomicFactory.factorUsingNameInDataAndValue(nameInData, value);
	}

	private static synchronized void ensureDataAtomicFactoryIsSet() {
		if (null == dataAtomicFactory) {
			getDataAtomicFactoryImpUsingModuleStarter();
		}
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static DataAtomic getDataAtomicUsingNameInDataAndValueAndRepeatId(String nameInData,
			String value, String repeatId) {
		ensureDataAtomicFactoryIsSet();
		return dataAtomicFactory.factorUsingNameInDataAndValueAndRepeatId(nameInData, value,
				repeatId);
	}

	private static void getDataAtomicFactoryImpUsingModuleStarter() {
		Iterable<DataAtomicFactory> dataAtomicFactoryImplementations = ServiceLoader
				.load(DataAtomicFactory.class);
		dataAtomicModuleStarter
				.startUsingDataAtomicFactoryImplementations(dataAtomicFactoryImplementations);
		dataAtomicFactory = dataAtomicModuleStarter.getDataAtomicFactory();
	}

	/**
	 * Sets a DataAtomicFactory that will be used to factor dataAtomics for Classes. This
	 * possibility to set a DataAtomicFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataAtomicFactory to use should be
	 * provided through an implementation of DataAtomicFactory in a seperate java module.
	 * 
	 * @param dataAtomicFactory
	 *            A DataAtomicFactory to use to create dataAtomics for testing
	 */
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public static void setDataAtomicFactory(DataAtomicFactory dataAtomicFactory) {
		DataAtomicProvider.dataAtomicFactory = dataAtomicFactory;

	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static DataAtomicModuleStarter getStarter() {
		return dataAtomicModuleStarter;
	}

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	static void setStarter(DataAtomicModuleStarter starter) {
		dataAtomicModuleStarter = starter;
	}

}
