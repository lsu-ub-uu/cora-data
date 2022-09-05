package se.uu.ub.cora.data.spy;

import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecord;
import se.uu.ub.cora.data.DataRecordFactory;

public class DataRecordFactorySpy implements DataRecordFactory {

	public DataGroup dataGroup;

	@Override
	public DataRecord factorUsingDataGroup(DataGroup dataGroup) {
		this.dataGroup = dataGroup;
		return new DataRecordSpy();
	}

}
