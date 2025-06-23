/*

 * Copyright 2019, 2025 Uppsala University Library
 * Copyright 2022 Olov McKie
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
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataChildFilter;
import se.uu.ub.cora.data.DataFactory;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataList;
import se.uu.ub.cora.data.DataRecord;
import se.uu.ub.cora.data.DataRecordGroup;
import se.uu.ub.cora.data.DataRecordLink;
import se.uu.ub.cora.data.DataResourceLink;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;

public class DataFactorySpy implements DataFactory {
	public MethodCallRecorder MCR = new MethodCallRecorder();
	public DataGroup dataGroup;
	public DataRecordGroup dataRecordGroup;

	@Override
	public DataList factorListUsingNameOfDataType(String nameOfDataType) {
		MCR.addCall("nameOfDataType", nameOfDataType);
		DataList dataList = new DataListSpy();
		MCR.addReturned(dataList);
		return dataList;
	}

	@Override
	public DataRecord factorRecordUsingDataRecordGroup(DataRecordGroup dataRecordGroup) {
		MCR.addCall("dataGroup", dataRecordGroup);
		this.dataRecordGroup = dataRecordGroup;
		DataRecord dataRecordSpy = new DataRecordSpy();
		MCR.addReturned(dataRecordSpy);
		return dataRecordSpy;
	}

	@Override
	public DataRecordGroup factorRecordGroupUsingNameInData(String nameInData) {
		MCR.addCall("nameInData", nameInData);
		DataRecordGroup dataRecordGroupSpy = new DataRecordGroupSpy();
		MCR.addReturned(dataRecordGroupSpy);
		return dataRecordGroupSpy;
	}

	@Override
	public DataRecordGroup factorRecordGroupFromDataGroup(DataGroup dataGroup) {
		MCR.addCall("dataGroup", dataGroup);
		DataRecordGroup dataRecordGroupSpy = new DataRecordGroupSpy();
		MCR.addReturned(dataRecordGroupSpy);
		return dataRecordGroupSpy;
	}

	@Override
	public DataGroup factorGroupFromDataRecordGroup(DataRecordGroup dataRecordGroup) {
		MCR.addCall("dataRecordGroup", dataRecordGroup);
		DataGroup dataGroupSpy = new DataGroupSpy("nameInData");
		MCR.addReturned(dataGroupSpy);
		return dataGroupSpy;
	}

	@Override
	public DataGroup factorGroupUsingNameInData(String nameInData) {
		MCR.addCall("nameInData", nameInData);
		DataGroup dataGroupSpy = new DataGroupSpy(nameInData);
		MCR.addReturned(dataGroupSpy);
		return dataGroupSpy;
	}

	@Override
	public DataRecordLink factorRecordLinkUsingNameInData(String nameInData) {
		MCR.addCall("nameInData", nameInData);
		DataRecordLink dataLinkSpy = new DataRecordLinkSpy();
		MCR.addReturned(dataLinkSpy);
		return dataLinkSpy;
	}

	@Override
	public DataRecordLink factorRecordLinkUsingNameInDataAndTypeAndId(String nameInData,
			String type, String id) {
		MCR.addCall("nameInData", nameInData, "type", type, "id", id);
		DataRecordLink dataLinkSpy = new DataRecordLinkSpy();
		MCR.addReturned(dataLinkSpy);
		return dataLinkSpy;
	}

	@Override
	public DataResourceLink factorResourceLinkUsingNameInDataAndTypeAndIdAndMimeType(String nameInData,
			String recordType, String recordId, String mimeType) {
		MCR.addCall("nameInData", nameInData, "recordType", recordType, "recordId", recordId,
				"mimeType", mimeType);
		DataResourceLink dataLinkSpy = new DataResourceLinkSpy();
		MCR.addReturned(dataLinkSpy);
		return dataLinkSpy;
	}

	@Override
	public DataAtomic factorAtomicUsingNameInDataAndValue(String nameInData, String value) {
		MCR.addCall("nameInData", nameInData, "value", value);
		DataAtomic dataAtomic = new DataAtomicSpy();
		MCR.addReturned(dataAtomic);
		return dataAtomic;
	}

	@Override
	public DataAtomic factorAtomicUsingNameInDataAndValueAndRepeatId(String nameInData,
			String value, String repeatId) {
		MCR.addCall("nameInData", nameInData, "value", value, "repeatId", repeatId);
		DataAtomic dataAtomic = new DataAtomicSpy();
		MCR.addReturned(dataAtomic);
		return dataAtomic;
	}

	@Override
	public DataAttribute factorAttributeUsingNameInDataAndValue(String nameInData, String value) {
		MCR.addCall("nameInData", nameInData, "value", value);
		DataAttribute dataAttribute = new DataAttributeSpy();
		MCR.addReturned(dataAttribute);
		return dataAttribute;
	}

	@Override
	public DataChildFilter factorDataChildFilterUsingNameInData(String childNameInData) {
		MCR.addCall("childNameInData", childNameInData);
		DataChildFilter dataChildFilter = new DataChildFilterSpy();
		MCR.addReturned(dataChildFilter);
		return dataChildFilter;
	}

}
