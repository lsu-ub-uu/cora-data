/*
 * Copyright 2021 Uppsala University Library
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

import se.uu.ub.cora.data.ability.DataCharacteristic;
import se.uu.ub.cora.data.ability.DataPart;

/**
 * 
 * DataRecordGroup contains all data for a record. It has a metainformation about the record it
 * represents known as recordInfo, in a child DataGroup, with the name "recordInfo".
 * </p>
 * As this class holds all information about a {@link Record} except links and permissions, are
 * there a number of utility methods added to this class to manipulate the metainformation about the
 * record found in recordInfo, such as type, id, dataDivider, createdBy, updated, tsCreated, etc.
 * 
 * </p>
 * There are a few usecases when it is of lesser importance if the class beening handled is a
 * {@link DataRecordGroup} or a {@link DataGroup}, such as when converting to other formats. The
 * recomeded way to handle these usecases is to use the common parent class {@link DataParent}.
 * </p>
 * {@link DataProvider} has methods to turn a DataRecordGroup into a DataGroup and vice versa. See:
 * {@link DataProvider#createRecordGroupFromDataGroup(DataGroup)} and
 * {@link DataProvider#createGroupFromRecordGroup(DataRecordGroup)}
 * </p>
 * <b>DataRecordGroup is work in progress, more methods needs to be added</b>
 *
 */
public interface DataRecordGroup
		extends Data, ExternallyConvertible, DataPart, DataCharacteristic, DataParent {
	/**
	 * getType returns the records type for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "type" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records type is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the type can not be determined.
	 * 
	 * @return A String with the type of this DataRecordGroup
	 */
	String getType();

	/**
	 * setType sets the records type for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "type" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} type, or the {@link DataGroup} recordInfo is missing, should
	 * they be automatically added, and the links linkedRecordId set to the provided value. If the
	 * link must be created should its "linkedRecordType" be set to the value "recordType".
	 * 
	 * @param type
	 *            A String with the type of this DataRecordGroup
	 */
	void setType(String type);

	/**
	 * getId returns the records id for this DataRecordGroup. This information is the value from the
	 * child {@link DataAtomic} with nameInData "id" found in the {@link DataGroup} with nameInData
	 * "recordInfo".
	 * </p>
	 * If the records id is unknown SHOULD a {@link DataMissingException} be thrown with information
	 * about why the id can not be determined.
	 * 
	 * @return A String with the id of this DataRecordGroup
	 */
	String getId();

	/**
	 * setId sets the records id for this DataRecordGroup. This information is the value of the
	 * {@link DataAtomic} with nameInData "id" found in the child {@link DataGroup} with nameInData
	 * "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} id, or the {@link DataGroup} recordInfo is missing, should they be
	 * automatically added, and the atomics value set to the provided value.
	 * 
	 * @param id
	 *            A String with the id of this DataRecordGroup
	 */
	void setId(String id);

	/**
	 * getDataDivider returns the records dataDivider for this DataRecordGroup. This information is
	 * the linkedRecordId for the {@link DataRecordLink} with nameInData "dataDivider" found in the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records dataDivider is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the dataDivider can not be determined.
	 * 
	 * @return A String with the dataDivider of this DataRecordGroup
	 */
	String getDataDivider();

	/**
	 * setDataDivider sets the records dataDivider for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "dataDivider" found in the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} dataDivider, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the links linkedRecordId set to the provided value.
	 * If the link must be created should its "linkedRecordType" be set to the value "system".
	 * 
	 * @param dataDivider
	 *            A String with the dataDivider of this DataRecordGroup
	 */
	void setDataDivider(String dataDivider);

	/**
	 * getValidationType returns the records validationType for this DataRecordGroup. This
	 * information is the linkedRecordId for the {@link DataRecordLink} with nameInData
	 * "validationType" found in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records validationType is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the validationType can not be determined.
	 * 
	 * @return A String with the validationType of this DataRecordGroup
	 */
	String getValidationType();

	/**
	 * setValidationType sets the records validationType for this DataRecordGroup. This information
	 * is the linkedRecordId for the {@link DataRecordLink} with nameInData "validationType" found
	 * in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} validationType, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the links linkedRecordId set to the provided value.
	 * If the link must be created should its "linkedRecordType" be set to the value
	 * "validationType".
	 * 
	 * @param validationType
	 *            A String with the validationType of this DataRecordGroup
	 */
	void setValidationType(String validationType);
}
