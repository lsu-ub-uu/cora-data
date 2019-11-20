package se.uu.ub.cora.data;

public class DataAtomicFactorySpy implements DataAtomicFactory {

	public boolean withNameInDataAndValueWasCalled = false;
	public String nameInData;
	public DataAtomic returnedDataAtomic;
	public String value;

	@Override
	public DataAtomic factorUsingNameInDataAndValue(String nameInData, String value) {
		withNameInDataAndValueWasCalled = true;
		this.nameInData = nameInData;
		this.value = value;
		returnedDataAtomic = new DataAtomicSpy();
		return returnedDataAtomic;
	}

	@Override
	public DataAtomic factorUsingNameInDataAndValueAndRepeatId(String nameInData, String value,
			String repeatId) {
		// TODO Auto-generated method stub
		return null;
	}

}
