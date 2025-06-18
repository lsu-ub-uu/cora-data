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
package se.uu.ub.cora.data;

/**
 * DataFactory is used to create instances of data classes in a Cora system.
 */
public interface DataFactory {

	/**
	 * Creates a DataList using the specified name of the data type.
	 * 
	 * @param nameOfDataType
	 *            the name of the data type
	 * @return a new DataList instance
	 */
	DataList factorListUsingNameOfDataType(String nameOfDataType);

	/**
	 * Creates a DataRecord using the specified DataRecordGroup.
	 * 
	 * @param dataRecordGroup
	 *            the DataRecordGroup to use
	 * @return a new DataRecord instance
	 */
	DataRecord factorRecordUsingDataRecordGroup(DataRecordGroup dataRecordGroup);

	/**
	 * Creates a DataRecordGroup using the specified nameInData.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @return a new DataRecordGroup instance
	 */
	DataRecordGroup factorRecordGroupUsingNameInData(String nameInData);

	/**
	 * Creates a DataRecordGroup from the specified DataGroup.
	 * 
	 * @param dataGroup
	 *            the DataGroup to use
	 * @return a new DataRecordGroup instance
	 */
	DataRecordGroup factorRecordGroupFromDataGroup(DataGroup dataGroup);

	/**
	 * Creates a DataGroup from the specified DataRecordGroup.
	 * 
	 * @param dataRecordGroup
	 *            the DataRecordGroup to use
	 * @return a new DataGroup instance
	 */
	DataGroup factorGroupFromDataRecordGroup(DataRecordGroup dataRecordGroup);

	/**
	 * Creates a DataGroup using the specified nameInData.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @return a new DataGroup instance
	 */
	DataGroup factorGroupUsingNameInData(String nameInData);

	/**
	 * Creates a DataRecordLink using the specified nameInData.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @return a new DataRecordLink instance
	 */
	DataRecordLink factorRecordLinkUsingNameInData(String nameInData);

	/**
	 * Creates a DataRecordLink using the specified nameInData, record type, and record ID.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @param recordType
	 *            the record type
	 * @param recordId
	 *            the record ID
	 * @return a new DataRecordLink instance
	 */
	DataRecordLink factorRecordLinkUsingNameInDataAndTypeAndId(String nameInData, String recordType,
			String recordId);

	/**
	 * Creates a DataResourceLink using the specified nameInData, record type, record ID, and MIME
	 * type.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @param recordType
	 *            the record type
	 * @param recordId
	 *            the record ID
	 * @param mimeType
	 *            the MIME type
	 * @return a new DataResourceLink instance
	 */
	DataResourceLink factorResourceLinkUsingNameInDataAndMimeType(String nameInData,
			String recordType, String recordId, String mimeType);

	/**
	 * Creates a DataAtomic using the specified nameInData and value.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @param value
	 *            the value
	 * @return a new DataAtomic instance
	 */
	DataAtomic factorAtomicUsingNameInDataAndValue(String nameInData, String value);

	/**
	 * Creates a DataAtomic using the specified nameInData, value, and repeat ID.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @param value
	 *            the value
	 * @param repeatId
	 *            the repeat ID
	 * @return a new DataAtomic instance
	 */
	DataAtomic factorAtomicUsingNameInDataAndValueAndRepeatId(String nameInData, String value,
			String repeatId);

	/**
	 * Creates a DataAttribute using the specified nameInData and value.
	 * 
	 * @param nameInData
	 *            the nameInData
	 * @param value
	 *            the value
	 * @return a new DataAttribute instance
	 */
	DataAttribute factorAttributeUsingNameInDataAndValue(String nameInData, String value);

	/**
	 * Creates a DataChildFilter using the specified child nameInData.
	 * 
	 * @param childNameInData
	 *            the child nameInData
	 * @return a new DataChildFilter instance
	 */
	DataChildFilter factorDataChildFilterUsingNameInData(String childNameInData);

}
