package se.uu.ub.cora.data;

import java.util.ArrayList;
import java.util.List;

public class DataGroupSpy implements DataGroup {

	public String nameInData;
	public List<DataElement> children = new ArrayList<>();

	public DataGroupSpy(String nameInData) {
		this.nameInData = nameInData;
	}

	@Override
	public String getNameInData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGroup getFirstGroupWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(DataElement dataElement) {
		children.add(dataElement);

	}

	@Override
	public List<DataElement> getChildren() {
		return children;
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRepeatId() {
		// TODO Auto-generated method stub
		return null;
	}

}
