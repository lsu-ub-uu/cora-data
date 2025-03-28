/*
 * Copyright 2022, 2024 Olov McKie
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
package se.uu.ub.cora.data.spy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataChildFilter;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecordGroup;

public class DataRecordGroupSpy implements DataRecordGroup {

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addChild(DataChild dataElement) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addChildren(Collection<DataChild> dataElements) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<DataChild> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataChild> getAllChildrenWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataChild> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataChild getFirstChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataAtomic> getAllDataAtomicsWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGroup getFirstGroupWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllChildrenWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataAtomic getFirstDataAtomicWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttributeByIdWithValue(String nameInData, String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataAttribute getAttribute(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAttribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameInData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAtomic> getAllDataAtomicsWithNameInDataAndAttributes(
			String childNameInData, DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataChild> getAllChildrenMatchingFilter(DataChildFilter childFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAllChildrenMatchingFilter(DataChildFilter childFilter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getDataDivider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDataDivider(String dataDivider) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getValidationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValidationType(String validationType) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T> boolean containsChildOfTypeAndName(Class<T> type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends DataChild> T getFirstChildOfTypeAndName(Class<T> type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends DataChild> List<T> getChildrenOfTypeAndName(Class<T> type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends DataChild> boolean removeFirstChildWithTypeAndName(Class<T> type,
			String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends DataChild> boolean removeChildrenWithTypeAndName(Class<T> type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<String> getAttributeValue(String nameInData) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public String getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedBy(String userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getTsCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTsCreated(String tsCreated) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setTsCreatedToNow() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getLatestUpdatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLatestTsUpdated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUpdatedUsingUserIdAndTs(String userId, String tsUpdated) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addUpdatedUsingUserIdAndTsNow(String userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean overwriteProtectionShouldBeEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeOverwriteProtection() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<DataChild> getAllUpdated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllUpdated(Collection<DataChild> updated) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<String> getTsVisibility() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<String> getVisibility() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void setTsVisibilityNow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTsVisibility(String tsVisibility) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVisibility(String visibility) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<String> getPermissionUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPermissionUnit(String permissionUnit) {
		// TODO Auto-generated method stub

	}

}
