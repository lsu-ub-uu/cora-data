package se.uu.ub.cora.data;

import se.uu.ub.cora.data.DataAtomicFactory;
import se.uu.ub.cora.data.DataGroupFactory;
import se.uu.ub.cora.data.DataRecordFactory;
import se.uu.ub.cora.data.DataRecordLinkFactory;

public interface DataFactoryProvider {

	DataRecordFactory getDataRecordFactory();

	DataGroupFactory getDataGroupFactory();

	DataRecordLinkFactory getDataRecordLinkFactory();

	DataAtomicFactory getDataAtomicFactory();

}
