/*
 * Copyright 2022 Uppsala University Library
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
package se.uu.ub.cora.data.collected;

/**
 * StorageTerm holds information about one collected storageTerm.
 * 
 * @param storageTermId
 *            String with the id of the storageTerm that deffines the metadata for this storageTerm.
 * @param storageKey
 *            String with the key of the storageTerm, the value will be stored under this key.
 * @param value
 *            String with the collected value for the storageTerm, picked out from the record
 *            currently being stored.
 */
public record StorageTerm(String storageTermId, String storageKey, String value) {

}
