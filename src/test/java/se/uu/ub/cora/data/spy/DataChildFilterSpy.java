package se.uu.ub.cora.data.spy;

import java.util.Set;

import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataChildFilter;

public class DataChildFilterSpy implements DataChildFilter {

	@Override
	public void addAttributeUsingNameInDataAndPossibleValues(String nameInData,
			Set<String> possibleValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean childMatches(DataChild child) {
		// TODO Auto-generated method stub
		return false;
	}

}
