/*
 * Copyright 2015, 2019 Uppsala University Library
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
import java.util.Collections;

/**
 * DataElement defines elements that can be added as children to a DataGroup.
 */
public interface DataElement extends DataPart {

	void setRepeatId(String repeatId);

	String getRepeatId();

	/**
	 * addAttributeByIdWithValue adds a DataAttribute with the specified nameInData and value. The
	 * implementation is expected to allow only one attribute with the specified nameInData.
	 * <p>
	 * Note that this interface currently has a default implementation, awaiting that the system
	 * gets support for attributes for all DataElements (DataAtomic) as well. Once the system has
	 * support for attributes for all DataElements will this default implementation be removed.
	 */
	default void addAttributeByIdWithValue(String id, String value) {
		// does nothing by design, see note in javadoc
	}

	/**
	 * hasAttributes returns true if this DataElement has attributes else false is returned
	 * <p>
	 * Note that this interface currently has a default implementation, awaiting that the system
	 * gets support for attributes for all DataElements (DataAtomic) as well. Once the system has
	 * support for attributes for all DataElements will this default implementation be removed.
	 * 
	 * @return true if this element has attributes else false
	 */
	default boolean hasAttributes() {
		return false;
	}

	/**
	 * getAttribute returns the DataAttribute with the specified nameInData.
	 * <p>
	 * A {@link DataMissingException} SHOULD be thrown if no attribute exists with the specified
	 * nameInData.
	 * <p>
	 * Note that this interface currently has a default implementation, awaiting that the system
	 * gets support for attributes for all DataElements (DataAtomic) as well. Once the system has
	 * support for attributes for all DataElements will this default implementation be removed.
	 */
	default DataAttribute getAttribute(String nameInData) {
		throw new DataMissingException("This class has not implemented getAttribute.");
	}

	/**
	 * getAttributes returns the attributes that this dataElement has.
	 * <p>
	 * Note that this interface currently has a default implementation, awaiting that the system
	 * gets support for attributes for all DataElements (DataAtomic) as well. Once the system has
	 * support for attributes for all DataElements will this default implementation be removed.
	 * 
	 * @return A Collection of this elements DataAttributes
	 */
	default Collection<DataAttribute> getAttributes() {
		return Collections.emptySet();
	}

}
