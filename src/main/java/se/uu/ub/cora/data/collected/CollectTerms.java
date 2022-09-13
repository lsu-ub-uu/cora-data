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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CollectTerms is a holder for all collected collectTerms for a record.
 * 
 */
public class CollectTerms {
	public Optional<String> recordId = Optional.empty();
	public Optional<String> recordType = Optional.empty();
	public List<PermissionTerm> permissionTerms = new ArrayList<>();
	public List<StorageTerm> storageTerms = new ArrayList<>();
	public List<IndexTerm> indexTerms = new ArrayList<>();

	public void addPermissionTerm(PermissionTerm term) {
		permissionTerms.add(term);
	}

	public void addStorageTerm(StorageTerm term) {
		storageTerms.add(term);
	}

	public void addIndexTerm(IndexTerm term) {
		indexTerms.add(term);
	}

}
