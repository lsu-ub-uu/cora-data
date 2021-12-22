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
package se.uu.ub.cora.data;

/**
 * 
 * TopDataGroup contains data for a record. It is a {@link DataGroup} that has a meta information
 * about the record it represents known as recordInfo in a child DataGroup. TopDataGroups main
 * difference from a DataGroup is that it is known to be the top one.
 * <p>
 * <b>TopDataGroup is work in progress</b>
 * <p>
 * <u>THE GOAL</u>
 * <p>
 * TopDataGroup is intended to have methods such as getId and getType, and other methods to do with
 * the fact that it is the topDataGroup and therefor has the recordInfo group as a child. These
 * methods can unfortunately not be added until we have switched to using TopDataGroup, in
 * DataRecord, DataList and other places where the DataGroup used today really is a TopDataGroup.
 * <p>
 * Finally dataGroup will be replaced with TopDataGroup in all places where a DataGroup is used as a
 * top level (TopDataGroup), ie. the top DataGroup in a record. This work must start where
 * DataGroups are created at the edges of the system, the API, storage and search.
 *
 */
public interface TopDataGroup extends Data, DataGroup {

}
