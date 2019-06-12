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

public class DataGroupCopier implements DataCopier {

	private DataGroup dataGroup;
	private DataCopierFactory copierFactory;

	private DataGroupCopier(DataGroup dataGroup, DataCopierFactory copierFactory) {
		this.dataGroup = dataGroup;
		this.copierFactory = copierFactory;
	}

	public static DataGroupCopier usingDataGroupAndCopierFactory(DataGroup dataGroup,
			DataCopierFactory copierFactory) {
		return new DataGroupCopier(dataGroup, copierFactory);
	}

	@Override
	public DataGroup copy() {
		DataGroup dataGroupCopy = DataGroup.withNameInData(dataGroup.getNameInData());
		copierFactory.factorForDataElement(dataGroup.getChildren().get(0));
		return dataGroupCopy;
	}

}
