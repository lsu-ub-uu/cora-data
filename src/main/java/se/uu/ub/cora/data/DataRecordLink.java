/*
 * Copyright 2019, 2025 Uppsala University Library
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

import java.util.Optional;

/**
 * DataRecordLink contains information linking the {@link DataRecord} this link is a part of to
 * another {@link DataRecord}
 */
public interface DataRecordLink extends DataLink, Convertible {
	/**
	 * getLinkedRecordId returns the record id of the record this link refers to
	 * <p>
	 * This information is expected to be present, if this link does not have information about what
	 * the linked record id is, MUST a {@link DataMissingException} be thrown.
	 * 
	 * @return A String with the id of the record type that this link refers to.
	 */
	public String getLinkedRecordId();

	/**
	 * getLinkedRecordType returns the record type of the record this link refers to.
	 * <p>
	 * This information is expected to be present, if this link does not have information about what
	 * the linked record type is, MUST a {@link DataMissingException} be thrown.
	 * 
	 * @return A String with the record type that this link refers to.
	 */
	public String getLinkedRecordType();

	/**
	 * setLinkedRecord sets the linked record, as group, for this link.
	 * <p>
	 * This method is intended to be used only when decorating a record.
	 * 
	 * @param group
	 *            The {@link DataGroup} that should be set as the linked record for this link.
	 */
	public void setLinkedRecord(DataGroup group);

	/**
	 * getLinkedRecord returns the linked record for this link.
	 * <p>
	 * This method is intended to be used only when decorating a record and convertiong to external
	 * format.
	 * 
	 * @return The {@link DataGroup} that is set as the linked record, as group, for this link.
	 */
	public Optional<DataGroup> getLinkedRecord();

}
