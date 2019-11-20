package se.uu.ub.cora.data;

public class DataRecordFactorySpy implements DataRecordFactory {

	public DataGroup dataGroup;

	@Override
	public DataRecord factorUsingDataGroup(DataGroup dataGroup) {
		this.dataGroup = dataGroup;
		return new DataRecordSpy();
	}

}
