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
 * DataPart defines the minimum required functionality for any part of data that the system handles
 */
public interface DataPart {
	/**
	 * getNameInData returns the name this piece of information should have when it is reprented as
	 * data.
	 * 
	 * @return A String with the name to use when this DataPart is used in some sort of a block of
	 *         data
	 */
	String getNameInData();

}
