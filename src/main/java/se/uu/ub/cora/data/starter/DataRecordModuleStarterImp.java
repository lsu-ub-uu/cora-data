package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataRecordFactory;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public class DataRecordModuleStarterImp extends ModuleStarter implements DataRecordModuleStarter {

	private DataRecordFactory dataRecordFactory;

	@Override
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public void startUsingDataRecordFactoryImplementations(
			Iterable<DataRecordFactory> dataRecordFactoryImplementations) {
		dataRecordFactory = getImplementationThrowErrorIfNoneOrMoreThanOne(
				dataRecordFactoryImplementations, "DataRecordFactory");

	}

	@Override
	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	public DataRecordFactory getDataRecordFactory() {
		return dataRecordFactory;
	}

}
