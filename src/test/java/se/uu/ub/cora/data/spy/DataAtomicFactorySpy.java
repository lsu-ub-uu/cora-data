/*
 * Copyright 2019, 2020 Uppsala University Library
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
package se.uu.ub.cora.data.spy;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAtomicFactory;

public class DataAtomicFactorySpy implements DataAtomicFactory {

	public boolean withNameInDataAndValueWasCalled = false;
	public boolean withNameInDataAndValueAndRepeatIdWasCalled = false;
	public String nameInData;
	public DataAtomic returnedDataAtomic;
	public String value;
	public String repeatId;

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
		withNameInDataAndValueAndRepeatIdWasCalled = true;
		this.nameInData = nameInData;
		this.value = value;
		this.repeatId = repeatId;
		returnedDataAtomic = new DataAtomicSpy();
		return returnedDataAtomic;
	}

}
