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
 * DataRecordGroup contains all data for a record. It has meta information about the record it
 * represents known as recordInfo in a child DataGroup, with the name recordInfo. DataRecordGroups
 * main differences from a DataGroup is that it can not have a repeatId, it can not be added as a
 * child to a DataParent and it is known to be the one for the entire record.
 * <p>
 * This difference makes it possible to directly handle information that is known to exist in
 * recordInfo such as type, id, dataDivider, createdBy, updated, tsCreated, etc. And to provide
 * utility methods to handle changes to this data.
 * <p>
 * <b>DataRecordGroup is work in progress</b>
 * <p>
 * <u>THE GOAL</u>
 * <p>
 * DataRecordGroup is intended to have methods such as getId and getType, and other methods to do
 * with the fact that it is the DataGroup for the entire record and therefor is known to have the
 * recordInfo group as a child. These methods can unfortunately not be added until we have switched
 * to using DataRecordGroup, in DataRecord, DataList and other places where the DataGroup used today
 * really is a DataRecordGroup.
 * <p>
 * Finally dataGroup will be replaced with DataRecordGroup in all places where a DataGroup is used
 * as a datagroup for the entire record (DataRecordGroup). This work must start where DataGroups are
 * created at the edges of the system, the API, storage and search.
 */
public interface DataRecordGroup
		extends Data, ExternallyConvertible, DataPart, DataCharacteristic, DataParent {

	// DataGroup asDataGroup();

	// link pointing to recordType
	void setType(String type);

	/**
	 * getType returns the record type for this record.
	 * <p>
	 * If the records type is unknown SHOULD a {@link DataMissingException} be thrown.
	 * 
	 * @return String with the type of this record
	 */
	String getType();

	// boolean hasType();

	void setId(String id);

	/**
	 * getId returns the record id for this record.
	 * <p>
	 * If the records id is unknown SHOULD a {@link DataMissingException} be thrown.
	 * 
	 * @return String with the id of this record
	 */
	String getId();

	// boolean hasId();

	// link pointing to system
	void setDataDivider(String dataDivider);

	String getDataDivider();

	// boolean hasDataDivider();

	// link pointing to user
	/**
	 * setCreatedInfoUsingUserIdAndTsCreated MUST be implemented so that
	 * <p>
	 * MUST also set a corresponding updated group
	 * 
	 * @param userId
	 * @param tsCreated
	 */
	void setCreatedInfoUsingUserIdAndTsCreated(String userId, String tsCreated);

	// void setCreatedBy(String tsCreated);

	String getCreatedBy();

	// boolean hasCreatedBy();

	// createdBy
	// tsCreated
	// void setTsCreated(String dataDivider);

	String getTsCreated();

	// boolean hasTsCreated();

	void addUpdatedInfoUsingUserIdAndTsUpdated(String userId, String tsUpdated);
	// updated
	//// link updatedBy pointing to user
	//// tsUpdated
	// "2018-11-29T13:55:55.827000Z"
}
