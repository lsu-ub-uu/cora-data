package se.uu.ub.cora.data.spy;

import se.uu.ub.cora.data.DataAtomicFactory;
import se.uu.ub.cora.data.starter.DataAtomicModuleStarter;

public class DataAtomicModuleStarterSpy implements DataAtomicModuleStarter {

	public boolean startWasCalled = false;
	public DataAtomicFactorySpy dataAtomicFactorySpy;

	@Override
	public void startUsingDataAtomicFactoryImplementations(
			Iterable<DataAtomicFactory> loggerFactoryImplementations) {
		startWasCalled = true;

	}

	@Override
	public DataAtomicFactory getDataAtomicFactory() {
		dataAtomicFactorySpy = new DataAtomicFactorySpy();
		return dataAtomicFactorySpy;
	}

}
