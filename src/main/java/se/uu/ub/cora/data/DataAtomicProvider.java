package se.uu.ub.cora.data;

import java.util.ServiceLoader;

import se.uu.ub.cora.data.starter.DataAtomicModuleStarter;
import se.uu.ub.cora.data.starter.DataAtomicModuleStarterImp;

public class DataAtomicProvider {

	private static DataAtomicFactory dataAtomicFactory;
	private static DataAtomicModuleStarter dataAtomicModuleStarter = new DataAtomicModuleStarterImp();

	private DataAtomicProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	public static DataAtomic getDataAtomicUsingNameInDataAndValue(String nameInData, String value) {
		ensureDataAtomicFactoryIsSet();
		return dataAtomicFactory.factorUsingNameInDataAndValue(nameInData, value);
	}

	private static synchronized void ensureDataAtomicFactoryIsSet() {
		if (null == dataAtomicFactory) {
			getDataAtomicFactoryImpUsingModuleStarter();
		}
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
	public static void setDataAtomicFactory(DataAtomicFactory dataAtomicFactory) {
		DataAtomicProvider.dataAtomicFactory = dataAtomicFactory;

	}

	static DataAtomicModuleStarter getStarter() {
		return dataAtomicModuleStarter;
	}

	static void setStarter(DataAtomicModuleStarter starter) {
		dataAtomicModuleStarter = starter;
	}

}
