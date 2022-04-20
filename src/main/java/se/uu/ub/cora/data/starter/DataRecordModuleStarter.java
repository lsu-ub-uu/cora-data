package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataRecordFactory;

/**
 * @deprecated use DataProvider and DataFactory instead
 */
@Deprecated
public interface DataRecordModuleStarter {

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	void startUsingDataRecordFactoryImplementations(
			Iterable<DataRecordFactory> dataRecordFactoryImplementations);

	/**
	 * @deprecated use DataProvider and DataFactory instead
	 */
	@Deprecated
	DataRecordFactory getDataRecordFactory();

}
