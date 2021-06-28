/*
 * Copyright 2021 Uppsala University Library
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

/**
 * DataToJsonConverterFactoryCreator is an inteface used by implementing projects to create
 * factories of type {@link DataToJsonConverterFactory}. {@link DataToJsonConverterProvider} will
 * use ServiceLoader to find implementations of this class to provide a means to get instances of
 * DataToJsonConverterFactories.
 */
public interface DataToJsonConverterFactoryCreator {
	/**
	 * createFactory creates a new instance of {@link DataToJsonConverterFactory}.
	 * 
	 * @return A {@link DataToJsonConverterFactory}
	 */
	public DataToJsonConverterFactory createFactory();
}
