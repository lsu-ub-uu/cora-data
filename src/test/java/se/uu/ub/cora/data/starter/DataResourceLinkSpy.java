package se.uu.ub.cora.data.starter;

import java.util.Collection;
import java.util.List;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataElement;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataResourceLink;

public class DataResourceLinkSpy implements DataResourceLink {

	@Override
	public void addAction(Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameInData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepeatId() {
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<DataElement> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRepeatId(String repeatId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAttributeByIdWithValue(String id, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public DataElement getFirstChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataAttribute getAttribute(String attributeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstChildWithNameInData(String childNameInData) {
		return true;
	}

	@Override
	public Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAttribute> getAttributes() {
		return null;
	}

	@Override
	public boolean removeAllChildrenWithNameInData(String childNameInData) {
		return true;
	}

	@Override
	public DataAtomic getFirstDataAtomicWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChildren(Collection<DataElement> dataElements) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DataElement> getAllChildrenWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllChildrenWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DataElement> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasReadAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}
}
