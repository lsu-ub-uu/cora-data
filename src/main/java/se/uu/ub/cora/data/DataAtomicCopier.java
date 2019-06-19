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
package se.uu.ub.cora.data;

public class DataAtomicCopier implements DataCopier {

	private DataElement dataElement;

	private DataAtomicCopier(DataElement dataElement) {
		this.dataElement = dataElement;
	}

	public static DataAtomicCopier usingDataAtomic(DataElement dataElement) {
		return new DataAtomicCopier(dataElement);
	}

	@Override
	public DataElement copy() {
		DataAtomic dataAtomic = (DataAtomic) dataElement;
		DataAtomic dataAtomicCopy = DataAtomic.withNameInDataAndValue(dataAtomic.getNameInData(),
				dataAtomic.getValue());
		possiblySetRepeatId(dataAtomic, dataAtomicCopy);
		return dataAtomicCopy;
	}

	private void possiblySetRepeatId(DataAtomic dataAtomic, DataAtomic dataAtomicCopy) {
		if (dataAtomic.getRepeatId() != null) {
			dataAtomicCopy.setRepeatId(dataAtomic.getRepeatId());
		}
	}

}
