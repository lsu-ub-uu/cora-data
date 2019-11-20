package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataRecordFactory;

public class DataRecordModuleStarterImp extends ModuleStarter implements DataRecordModuleStarter {

	private DataRecordFactory dataRecordFactory;

	@Override
	public void startUsingDataRecordFactoryImplementations(
			Iterable<DataRecordFactory> dataRecordFactoryImplementations) {
		dataRecordFactory = getImplementationThrowErrorIfNoneOrMoreThanOne(
				dataRecordFactoryImplementations, "DataRecordFactory");

	}

	@Override
	public DataRecordFactory getDataRecordFactory() {
		return dataRecordFactory;
	}

}
