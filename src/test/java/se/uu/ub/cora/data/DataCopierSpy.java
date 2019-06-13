package se.uu.ub.cora.data;

public class DataCopierSpy implements DataCopier {

	public boolean copyWasCalled = false;
	public DataElement returnedElement;

	@Override
	public DataElement copy() {
		copyWasCalled = true;
		returnedElement = new DataElementSpy();
		return returnedElement;
	}

}
