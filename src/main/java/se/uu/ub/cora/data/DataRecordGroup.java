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
import se.uu.ub.cora.data.ability.DataParent;
import se.uu.ub.cora.data.ability.DataPart;

/**
 * 
 * DataRecordGroup contains all data for a record. It has a metainformation about the record it
 * represents known as recordInfo in a child DataGroup, with the name recordInfo. DataRecordGroups
 * main difference from a DataGroup is that it is known to be the one for the entire record.
 * </p>
 * This difference makes it possible to directly handle information that is known to exist in
 * recordInfo such as type, id, dataDivider, createdBy, updated, tsCreated, etc. And to add utility
 * methods to handle changes to this data.
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
	 * 
	 * @throws DataMissingException
	 *             if the records type is unknown.
	 * @implSpec If the records type is unknown SHOULD a {@link DataMissingException} be thrown with
	 *           information about why the type can not be determined.
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
	 * 
	 * @throws DataMissingException
	 *             if the records id is unknown.
	 * @implSpec If the records id is unknown SHOULD a {@link DataMissingException} be thrown with
	 *           information about why the id can not be determined.
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
	 * the linkedRecordId for the {@link DataRecordLink} with nameInData "DataDivider" found in the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * 
	 * @throws DataMissingException
	 *             if the records dataDivider is unknown.
	 * @implSpec If the records dataDivider is unknown SHOULD a {@link DataMissingException} be
	 *           thrown with information about why the dataDivider can not be determined.
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
}
