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
 * DataRecordGroup contains all data for a record. It is a {@link DataGroup} that has a meta
 * information about the record it represents known as recordInfo in a child DataGroup, with the
 * name recordInfo. DataRecordGroups main difference from a DataGroup is that it is known to be the
 * one for the entire record.
 * <p>
 * This difference makes it possible to directly handle information that is known to exist in
 * recordInfo such as type, id, dataDivider, createdBy, updated, tsCreated, etc. And to add utility
 * methods to handle changes to this data.
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
 *
 */
public interface DataRecordGroup
		extends Data, ExternallyConvertible, DataPart, DataCharacteristic, DataParent {

}
