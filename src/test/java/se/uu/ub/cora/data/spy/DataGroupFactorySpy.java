package se.uu.ub.cora.data.spy;

import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataGroupFactory;

public class DataGroupFactorySpy implements DataGroupFactory {

	public boolean withNameInDataWasCalled = false;
	public boolean asLinkWasCalled = false;
	public String nameInData;
	public DataGroup returnedDataGroup;
	public String recordType;
	public String recordId;

	@Override
	public DataGroup factorUsingNameInData(String nameInData) {
		withNameInDataWasCalled = true;
		this.nameInData = nameInData;
		returnedDataGroup = new DataGroupSpy("nameInData");
		return returnedDataGroup;
	}

	@Override
	public DataGroup factorAsLinkWithNameInDataTypeAndId(String nameInData, String recordType,
			String recordId) {
		asLinkWasCalled = true;
		this.nameInData = nameInData;
		this.recordType = recordType;
		this.recordId = recordId;
		returnedDataGroup = new DataGroupSpy("nameInData");
		return returnedDataGroup;
	}

}
