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

/**
 * DataResourceLink contains information linking the {@link DataRecord} this link is a part of to a
 * resource such as an image. Currently are DataResourceLinks only used in record type binary or
 * children of binary.
 * <p>
 * RecordTypes other than binary, links to a record with the type binary which in turn contains
 * metainformation about the binary file, and can through ResourceLinks point to different versions
 * of the binary data. The different versions can for instance be a thumbnail, a scaled version or
 * the master version of an image.
 */
public interface DataResourceLink extends DataLink {
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
