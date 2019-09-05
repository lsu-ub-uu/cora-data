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

import java.util.Map;
import java.util.Map.Entry;

public class DataGroupCopier implements DataCopier {

	private DataElement dataElement;
	private DataCopierFactory copierFactory;
	private CoraDataGroup originalDataGroup;
	private CoraDataGroup dataGroupCopy;

	private DataGroupCopier(DataElement dataElement, DataCopierFactory copierFactory) {
		this.dataElement = dataElement;
		this.copierFactory = copierFactory;
	}

	public static DataGroupCopier usingDataGroupAndCopierFactory(DataElement dataElement,
			DataCopierFactory copierFactory) {
		return new DataGroupCopier(dataElement, copierFactory);
	}

	@Override
	public CoraDataGroup copy() {
		originalDataGroup = (CoraDataGroup) dataElement;
		dataGroupCopy = CoraDataGroup.withNameInData(originalDataGroup.getNameInData());
		copyChildren();
		possiblyCopyRepeatId();
		possiblyCopyAttributes();

		return dataGroupCopy;
	}

	private void copyChildren() {
		for (DataElement childElement : originalDataGroup.getChildren()) {
			DataCopier dataCopier = copierFactory.factorForDataElement(childElement);
			DataElement copiedElement = dataCopier.copy();
			dataGroupCopy.addChild(copiedElement);
		}
	}

	private void possiblyCopyRepeatId() {
		if (originalDataGroup.getRepeatId() != null) {
			dataGroupCopy.setRepeatId(originalDataGroup.getRepeatId());
		}
	}

	private void possiblyCopyAttributes() {
		Map<String, String> attributes = originalDataGroup.getAttributes();
		for (Entry<String, String> attribute : attributes.entrySet()) {
			dataGroupCopy.addAttributeByIdWithValue(attribute.getKey(), attribute.getValue());
		}
	}

	DataCopierFactory getCopierFactory() {
		return copierFactory;
	}

}
