/*
 * Copyright 2019, 2023 Uppsala University Library
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
 * DataResourceLink is a link to a (binary) resource stored in storage. It represents the link
 * between the {@link DataRecord} and the resource. DataResourceLinks only used in record type
 * <b>binary</b>.
 * <p>
 * Each ResourceLink points to a representation of the resource, according to metadata definition.
 */
public interface DataResourceLink extends DataLink, Convertible {

	/**
	 * setMimeType sets the mimeType for this link.
	 */
	void setMimeType(String mimeType);

	/**
	 * getMimeType returns the mimeType for this link.
	 * <p>
	 * This information is expected to be present, if this link does not have information about what
	 * the mimetype is, MUST a {@link DataMissingException} be thrown.
	 * 
	 * @return A String with the mimetype for this link.
	 */
	String getMimeType();
}
