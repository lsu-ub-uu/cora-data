package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataRecordFactory;

public class DataRecordModuleStarterImp implements DataRecordModuleStarter {

	private DataRecordFactory dataRecordFactory;

	@Override
	public void startUsingDataRecordFactoryImplementations(
			Iterable<DataRecordFactory> dataRecordFactoryImplementations) {
		dataRecordFactory = getImplementationThrowErrorIfNoneOrMoreThanOne(
				dataRecordFactoryImplementations, "DataRecordFactory");

	}

	private <T extends Object> T getImplementationThrowErrorIfNoneOrMoreThanOne(
			Iterable<T> implementations, String interfaceClassName) {
		T implementation = null;
		int noOfImplementationsFound = 0;
		for (T currentImplementation : implementations) {
			noOfImplementationsFound++;
			implementation = currentImplementation;
		}
		throwErrorIfNone(noOfImplementationsFound, interfaceClassName);
		throwErrorIfMoreThanOne(noOfImplementationsFound, interfaceClassName);
		return implementation;
	}

	private void throwErrorIfNone(int noOfImplementationsFound, String interfaceClassName) {
		if (noOfImplementationsFound == 0) {
			throw new DataInitializationException(
					"No implementations found for " + interfaceClassName);
		}
	}

	private void throwErrorIfMoreThanOne(int noOfImplementationsFound, String interfaceClassName) {
		if (noOfImplementationsFound > 1) {
			throw new DataInitializationException(
					"More than one implementation found for " + interfaceClassName);
		}
	}

	@Override
	public DataRecordFactory getDataRecordFactory() {
		return dataRecordFactory;
	}

}
