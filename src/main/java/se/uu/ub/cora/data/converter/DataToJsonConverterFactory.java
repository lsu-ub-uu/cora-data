/*
 * Copyright 2015 Uppsala University Library
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

package se.uu.ub.cora.data.converter;

import se.uu.ub.cora.data.Convertible;

/**
 * DataToJsonConverterFactory is a factory that creates new instances of {@link DataToJsonConverter}
 * for provided {@link Convertible}s
 */

public interface DataToJsonConverterFactory {
	/**
	 * factor creates a {@link DataToJsonConverter} for the provided {@link Convertible}
	 * 
	 * @param convertible
	 *            A {@link Convertible} to create a converter for
	 * @return returns a {@link DataToJsonConverter} x of converting the {@link Convertible} to a
	 *         json String.
	 */
	DataToJsonConverter factor(Convertible convertible);

}
