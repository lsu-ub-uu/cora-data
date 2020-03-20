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

import java.util.Collections;
import java.util.Map;

/**
 * DataElement defines DataElements that can be added as children to a DataGroup.
 */
public interface DataElement extends DataPart {

	void setRepeatId(String repeatId);

	String getRepeatId();

	/**
	 * getAttributes returns the attributes that this dataElement has.<br>
	 * <br>
	 * Note that this interface currently has a default implementation, awaiting that the system
	 * gets support for attributes for DataAtomic as well. Once the system has support for
	 * attributes for DataAtomics will this default implementation be removed.
	 * 
	 * @return A Map
	 */
	default Map<String, String> getAttributes() {
		return Collections.emptyMap();
	}

}
