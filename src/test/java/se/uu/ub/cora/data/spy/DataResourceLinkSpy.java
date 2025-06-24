/**Copyright 2019 Uppsala University Library*Copyright 2022 Olov McKie**This file is part of Cora.**Cora is free software:you can redistribute it and/or modify*it under the terms of the GNU General Public License as published by*the Free Software Foundation,either version 3 of the License,or*(at your option)any later version.**Cora is distributed in the hope that it will be useful,*but WITHOUT ANY WARRANTY;without even the implied warranty of*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the*GNU General Public License for more details.**You should have received a copy of the GNU General Public License*along with Cora.If not,see<http://www.gnu.org/licenses/>.
*/
package se.uu.ub.cora.data.spy;

import java.util.Collection;
import java.util.Optional;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataResourceLink;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;

public class DataResourceLinkSpy implements DataResourceLink {
	public MethodCallRecorder MCR = new MethodCallRecorder();

	@Override
	public void addAction(Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasReadAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRepeatId(String repeatId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRepeatId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameInData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttributeByIdWithValue(String nameInData, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataAttribute getAttribute(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAttribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMimeType(String mimeType) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<String> getAttributeValue(String nameInData) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean hasRepeatId() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
