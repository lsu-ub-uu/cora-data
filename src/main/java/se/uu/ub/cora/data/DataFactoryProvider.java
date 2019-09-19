package se.uu.ub.cora.data;

public interface DataFactoryProvider {

	DataRecordFactory getDataRecordFactory();

	DataGroupFactory getDataGroupFactory();

	DataRecordLinkFactory getDataRecordLinkFactory();

	DataAtomicFactory getDataAtomicFactory();

}
