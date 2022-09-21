/*
 * Copyright 2019, 2020 Uppsala University Library
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
 * DataGroup is a container for related {@link DataChild} elements.
 * </p>
 * DataGroup has a sibling class {@link DataRecordGroup} that is intended to be used when handling
 * the top most dataGroup for a record, as it has extra utility methods to handle the info found in
 * recordInfo.
 * </p>
 * There are a few usecases when it is of lesser importance if the class beening handled is a
 * {@link DataRecordGroup} or a {@link DataGroup}, such as when converting to other formats. The
 * recomeded way to handle these usecases is to use the common parent class {@link DataParent}.
 * </p>
 * {@link DataProvider} has methods to turn a DataRecordGroup into a DataGroup and vice versa. See:
 * {@link DataProvider#createRecordGroupFromDataGroup(DataGroup)} and
 * {@link DataProvider#createGroupFromRecordGroup(DataRecordGroup)}
 * </p>
 */
public interface DataGroup extends DataParent, DataChild, Data, Convertible, ExternallyConvertible {

}
