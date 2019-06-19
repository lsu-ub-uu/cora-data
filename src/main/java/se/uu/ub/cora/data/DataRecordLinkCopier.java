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

public class DataRecordLinkCopier implements DataCopier {

	private DataElement dataElement;

	public DataRecordLinkCopier(DataElement dataElement) {
		this.dataElement = dataElement;
	}

	@Override
	public DataRecordLink copy() {
		DataRecordLink orignialDataRecordLink = (DataRecordLink) dataElement;
		DataRecordLink recordLinkCopy = DataRecordLink.withNameInData(dataElement.getNameInData());

		copyAndAddChild(orignialDataRecordLink, recordLinkCopy, "linkedRecordType");
		copyAndAddChild(orignialDataRecordLink, recordLinkCopy, "linkedRecordId");
		return recordLinkCopy;
	}

	private void copyAndAddChild(DataRecordLink orignialDataRecordLink,
			DataRecordLink recordLinkCopy, String childNameInData) {
		DataElement linkedRecordTypeCopy = copyChildFromOriginalLinkUsingChildNameInData(
				orignialDataRecordLink, childNameInData);
		recordLinkCopy.addChild(linkedRecordTypeCopy);
	}

	private DataElement copyChildFromOriginalLinkUsingChildNameInData(
			DataRecordLink orignialDataRecordLink, String childNameInData) {
		DataAtomicCopier atomicCopier = DataAtomicCopier.usingDataAtomic(
				orignialDataRecordLink.getFirstChildWithNameInData(childNameInData));
		return atomicCopier.copy();
	}

}
