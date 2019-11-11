package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataRecordFactory;

public interface DataRecordModuleStarter {

	void startUsingDataRecordFactoryImplementations(
			Iterable<DataRecordFactory> dataRecordFactoryImplementations);

	DataRecordFactory getDataRecordFactory();

}
