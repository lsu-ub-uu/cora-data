/*
 * Copyright 2019 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.data.starter;

import se.uu.ub.cora.data.DataResourceLink;
import se.uu.ub.cora.data.DataResourceLinkFactory;
import se.uu.ub.cora.data.spy.DataResourceLinkSpy;

public class DataResourceLinkFactorySpy implements DataResourceLinkFactory {

	public boolean withNameInDataWasCalled = false;
	public DataResourceLinkSpy factoredResourceLink;
	public String nameInData;

	@Override
	public DataResourceLink factorUsingNameInData(String nameInData) {
		this.nameInData = nameInData;
		withNameInDataWasCalled = true;
		factoredResourceLink = new DataResourceLinkSpy();
		return factoredResourceLink;
	}

}
