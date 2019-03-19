/*
 * Copyright 2016 Olov McKie
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

import java.io.InputStream;

public final class SpiderInputStream {

	public final String name;
	public final long size;
	public final InputStream stream;
	public final String mimeType;

	private SpiderInputStream(String name, long size, String mimeType, InputStream stream) {
		this.name = name;
		this.size = size;
		this.mimeType = mimeType;
		this.stream = stream;
	}

	public static SpiderInputStream withNameSizeInputStream(String name, long size,
			String mimeType, InputStream stream) {
		return new SpiderInputStream(name, size, mimeType, stream);
	}

}
