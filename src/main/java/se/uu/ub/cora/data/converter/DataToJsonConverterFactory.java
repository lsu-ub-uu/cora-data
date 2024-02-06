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
 * <p>
 * By default SHOULD implementations generate converters that creates json without action links. To
 * get converters to generate action links call the method
 * {@link #switchToActionLinkGeneratingModeUsingBaseUrl(String)} with the base url to use in the
 * links.
 */

public interface DataToJsonConverterFactory {

	/**
	 * factorUsingConvertible creates a {@link DataToJsonConverter} for the provided
	 * {@link Convertible}
	 * 
	 * @param convertible
	 *            A {@link Convertible} to create a converter for
	 * 
	 * @return returns a {@link DataToJsonConverter} capable of converting the {@link Convertible}
	 *         to a json String.
	 */
	DataToJsonConverter factorUsingConvertible(Convertible convertible);

	/**
	 * factorUsingBaseUrlAndConvertible creates a {@link DataToJsonConverter} for the provided
	 * {@link Convertible} using the provided baseUrl.
	 * 
	 * @param convertible
	 *            A {@link Convertible} to create a converter for
	 * @param externalUrls
	 *            A data holder object with external rule such as baseUrl and iiifUrl
	 * @return
	 */
	DataToJsonConverter factorUsingConvertibleAndExternalUrls(Convertible convertible,
			ExternalUrls externalUrls);

	/**
	 * factorUsingRecordUrlAndConvertible creates a {@link DataToJsonConverter} for the provided
	 * {@link Convertible} using the provided recordUrl when creating action links.
	 * 
	 * @param baseUrl
	 *            The baseUrl related to the convertible
	 * @param recordUrl
	 *            The recordUrl related to the convertible
	 * @param convertible
	 *            A {@link Convertible} to create a converter for
	 * 
	 * @return
	 */
	DataToJsonConverter factorUsingBaseUrlAndRecordUrlAndConvertible(String baseUrl,
			String recordUrl, Convertible convertible);

}
