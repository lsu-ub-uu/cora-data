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
package se.uu.ub.cora.data.copier;

import java.util.ServiceLoader;

import se.uu.ub.cora.data.DataElement;
import se.uu.ub.cora.data.starter.DataCopierModuleStarter;
import se.uu.ub.cora.data.starter.DataCopierModuleStarterImp;

public class DataCopierProvider {
	private static DataCopierFactory dataAtomicFactory;
	private static DataCopierModuleStarter dataCopierModuleStarter = new DataCopierModuleStarterImp();

	private DataCopierProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataCopier getDataCopierUsingDataElement(DataElement dataElement) {
		ensureDataCopierFactoryIsSet();
		return dataAtomicFactory.factorForDataElement(dataElement);
	}

	private static synchronized void ensureDataCopierFactoryIsSet() {
		if (null == dataAtomicFactory) {
			getDataCopierFactoryImpUsingModuleStarter();
		}
	}

	private static void getDataCopierFactoryImpUsingModuleStarter() {
		Iterable<DataCopierFactory> dataAtomicFactoryImplementations = ServiceLoader
				.load(DataCopierFactory.class);
		dataCopierModuleStarter
				.startUsingDataCopierFactoryImplementations(dataAtomicFactoryImplementations);
		dataAtomicFactory = dataCopierModuleStarter.getDataCopierFactory();
	}

	/**
	 * Sets a DataCopierFactory that will be used to factor dataAtomics for Classes. This
	 * possibility to set a DataCopierFactory is provided to enable testing of logging in other
	 * classes and is not intented to be used in production. The DataCopierFactory to use should be
	 * provided through an implementation of DataCopierFactory in a seperate java module.
	 * 
	 * @param dataAtomicFactory
	 *            A DataCopierFactory to use to create dataAtomics for testing
	 */
	public static void setDataCopierFactory(DataCopierFactory dataAtomicFactory) {
		DataCopierProvider.dataAtomicFactory = dataAtomicFactory;

	}

	static DataCopierModuleStarter getStarter() {
		return dataCopierModuleStarter;
	}

	static void setStarter(DataCopierModuleStarter starter) {
		dataCopierModuleStarter = starter;
	}
}
