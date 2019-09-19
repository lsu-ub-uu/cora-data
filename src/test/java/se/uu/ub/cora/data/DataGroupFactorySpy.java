package se.uu.ub.cora.data;

public class DataGroupFactorySpy implements DataGroupFactory {

	public boolean withNameInDataWasCalled = false;
	public String nameInData;
	public DataGroup returnedDataGroup;

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
		return null;
	}

}
