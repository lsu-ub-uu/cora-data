/*
 * Copyright 2015, 2019, 2022 Uppsala University Library
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

import java.util.Collection;

/**
 * DataElement defines elements that can be added as children to a DataGroup.
 */
public interface DataElement extends DataPart {

	void setRepeatId(String repeatId);

	String getRepeatId();

	/**
	 * addAttributeByIdWithValue adds a DataAttribute with the specified nameInData and value. The
	 * implementation is expected to allow only one attribute with the specified nameInData.
	 */
	void addAttributeByIdWithValue(String id, String value);

	/**
	 * hasAttributes returns true if this DataElement has attributes else false is returned
	 * 
	 * @return true if this element has attributes else false
	 */
	boolean hasAttributes();

	/**
	 * getAttribute returns the DataAttribute with the specified nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no attribute exists with the specified
	 * nameInData.
	 */
	DataAttribute getAttribute(String nameInData);

	/**
	 * getAttributes returns the attributes that this dataElement has.
	 * 
	 * @return A Collection of this elements DataAttributes
	 */
	Collection<DataAttribute> getAttributes();

}
