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

public class DataRecordLinkCopier implements DataCopier {

	private DataElement dataElement;
	private DataRecordLink orignialDataRecordLink;
	private DataRecordLink dataRecordLinkCopy;

	public DataRecordLinkCopier(DataElement dataElement) {
		this.dataElement = dataElement;
	}

	@Override
	public DataRecordLink copy() {
		orignialDataRecordLink = (DataRecordLink) dataElement;
		dataRecordLinkCopy = DataRecordLink.withNameInData(dataElement.getNameInData());

		copyAndAddChildWithNameInData("linkedRecordType");
		copyAndAddChildWithNameInData("linkedRecordId");
		possiblyCopyRepeatId();
		possiblyCopyAttributes();
		return dataRecordLinkCopy;
	}

	private void copyAndAddChildWithNameInData(String childNameInData) {
		DataElement linkedRecordTypeCopy = copyChildFromOriginalLinkUsingChildNameInData(
				childNameInData);
		dataRecordLinkCopy.addChild(linkedRecordTypeCopy);
	}

	private DataElement copyChildFromOriginalLinkUsingChildNameInData(String childNameInData) {
		DataAtomicCopier atomicCopier = DataAtomicCopier.usingDataAtomic(
				(DataAtomic) orignialDataRecordLink.getFirstChildWithNameInData(childNameInData));
		return atomicCopier.copy();
	}

	private void possiblyCopyRepeatId() {
		if (orignialDataRecordLink.getRepeatId() != null) {
			dataRecordLinkCopy.setRepeatId(orignialDataRecordLink.getRepeatId());
		}
	}

	private void possiblyCopyAttributes() {
		Map<String, String> attributes = orignialDataRecordLink.getAttributes();
		for (Entry<String, String> attribute : attributes.entrySet()) {
			dataRecordLinkCopy.addAttributeByIdWithValue(attribute.getKey(), attribute.getValue());
		}
	}

}
