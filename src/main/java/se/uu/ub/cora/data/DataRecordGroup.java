/*
 * Copyright 2021 Uppsala University Library
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
package se.uu.ub.cora.data;

import java.util.Collection;
import java.util.Optional;

import se.uu.ub.cora.data.ability.DataCharacteristic;
import se.uu.ub.cora.data.ability.DataPart;

/**
 * 
 * DataRecordGroup contains all data for a record. It has a metainformation about the record it
 * represents known as recordInfo, in a child DataGroup, with the name "recordInfo".
 * </p>
 * As this class holds all information about a {@link Record} except links and permissions, are
 * there a number of utility methods added to this class to manipulate the metainformation about the
 * record found in recordInfo, such as type, id, dataDivider, createdBy, updated, tsCreated, etc.
 * 
 * </p>
 * There are a few usecases when it is of lesser importance if the class beening handled is a
 * {@link DataRecordGroup} or a {@link DataGroup}, such as when converting to other formats. The
 * recomeded way to handle these usecases is to use the common parent class {@link DataParent}.
 * </p>
 * {@link DataProvider} has methods to turn a DataRecordGroup into a DataGroup and vice versa. See:
 * {@link DataProvider#createRecordGroupFromDataGroup(DataGroup)} and
 * {@link DataProvider#createGroupFromRecordGroup(DataRecordGroup)}
 * </p>
 * <b>DataRecordGroup is work in progress, more methods needs to be added</b>
 *
 */
public interface DataRecordGroup
		extends Data, ExternallyConvertible, DataPart, DataCharacteristic, DataParent {
	/**
	 * getType returns the records type for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "type" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records type is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the type can not be determined.
	 * 
	 * @return A String with the type of this DataRecordGroup
	 */
	String getType();

	/**
	 * setType sets the records type for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "type" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} type, or the {@link DataGroup} recordInfo is missing, should
	 * they be automatically added, and the links linkedRecordId set to the provided value. If the
	 * link must be created should its "linkedRecordType" be set to the value "recordType".
	 * 
	 * @param type
	 *            A String with the type of this DataRecordGroup
	 */
	void setType(String type);

	/**
	 * getId returns the records id for this DataRecordGroup. This information is the value from the
	 * child {@link DataAtomic} with nameInData "id" found in the {@link DataGroup} with nameInData
	 * "recordInfo".
	 * </p>
	 * If the records id is unknown SHOULD a {@link DataMissingException} be thrown with information
	 * about why the id can not be determined.
	 * 
	 * @return A String with the id of this DataRecordGroup
	 */
	String getId();

	/**
	 * setId sets the records id for this DataRecordGroup. This information is the value of the
	 * {@link DataAtomic} with nameInData "id" found in the child {@link DataGroup} with nameInData
	 * "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} id, or the {@link DataGroup} recordInfo is missing, should they be
	 * automatically added, and the atomics value set to the provided value.
	 * 
	 * @param id
	 *            A String with the id of this DataRecordGroup
	 */
	void setId(String id);

	/**
	 * getDataDivider returns the records dataDivider for this DataRecordGroup. This information is
	 * the linkedRecordId for the {@link DataRecordLink} with nameInData "dataDivider" found in the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records dataDivider is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the dataDivider can not be determined.
	 * 
	 * @return A String with the dataDivider of this DataRecordGroup
	 */
	String getDataDivider();

	/**
	 * setDataDivider sets the records dataDivider for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "dataDivider" found in the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} dataDivider, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the links linkedRecordId set to the provided value.
	 * If the link must be created should its "linkedRecordType" be set to the value "system".
	 * 
	 * @param dataDivider
	 *            A String with the dataDivider of this DataRecordGroup
	 */
	void setDataDivider(String dataDivider);

	/**
	 * getValidationType returns the records validationType for this DataRecordGroup. This
	 * information is the linkedRecordId for the {@link DataRecordLink} with nameInData
	 * "validationType" found in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records validationType is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the validationType can not be determined.
	 * 
	 * @return A String with the validationType of this DataRecordGroup
	 */
	String getValidationType();

	/**
	 * setValidationType sets the records validationType for this DataRecordGroup. This information
	 * is the linkedRecordId for the {@link DataRecordLink} with nameInData "validationType" found
	 * in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} validationType, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the links linkedRecordId set to the provided value.
	 * If the link must be created should its "linkedRecordType" be set to the value
	 * "validationType".
	 * 
	 * @param validationType
	 *            A String with the validationType of this DataRecordGroup
	 */
	void setValidationType(String validationType);

	/**
	 * getCreatedBy returns the userId of the user that created this DataRecordGroup. This
	 * information is the linkedRecordId for the {@link DataRecordLink} with nameInData "createdBy"
	 * found in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records createdBy is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the createdBy can not be determined.
	 * 
	 * @return A String with the userId of the user that created this DataRecordGroup
	 */
	String getCreatedBy();

	/**
	 * setCreatedBy sets the records createdBy for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "createdBy" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataRecordLink} createdBy, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the links linkedRecordId set to the provided value.
	 * If the link must be created should its "linkedRecordType" be set to the value "user".
	 * 
	 * @param userId
	 *            A String with the userId of the user that created this DataRecordGroup
	 */
	void setCreatedBy(String userId);

	/**
	 * getTsCreated returns the timestamp of when the record for this DataRecordGroup was created.
	 * This information is the value from the child {@link DataAtomic} with nameInData "tsCreated"
	 * found in the {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records tsCreated is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the tsCreated can not be determined.
	 * 
	 * @return A String with the timestamp of when the record for this DataRecordGroup was created
	 */
	String getTsCreated();

	/**
	 * setTsCreated sets the the timestamp of when the record for this DataRecordGroup was created.
	 * This information is the value of the {@link DataAtomic} with nameInData "tsCreated" found in
	 * the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} tsCreated, or the {@link DataGroup} recordInfo is missing, should
	 * they be automatically added, and the atomics value set to the provided value.
	 * 
	 * @param tsCreated
	 *            A String with the timestamp of when the record for this DataRecordGroup was
	 *            created
	 */
	void setTsCreated(String tsCreated);

	/**
	 * setTsCreatedToNow sets the the timestamp of when the record for this DataRecordGroup was
	 * created to now. This information is the value of the {@link DataAtomic} with nameInData
	 * "tsCreated" found in the child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} tsCreated, or the {@link DataGroup} recordInfo is missing, should
	 * they be automatically added, and the atomics value set to now using format ISO-8601 with 6
	 * fractional digits (yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ).
	 */
	void setTsCreatedToNow();

	/**
	 * getLatestUpdatedBy returns the userId of the last user that updated this DataRecordGroup.
	 * This information is the linkedRecordId for the {@link DataRecordLink} with nameInData
	 * "updatedBy" found in the last child {@link DataGroup} with nameInData "updated" inside the
	 * child {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records last updatedBy is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the last updatedBy can not be determined.
	 * 
	 * @return A String with the userId of the user that last updated this DataRecordGroup
	 */
	String getLatestUpdatedBy();

	/**
	 * getLatestTsUpdated returns the timestamp of when the record for this DataRecordGroup was last
	 * updated. This information is the value from the child {@link DataAtomic} with nameInData
	 * "tsUpdated" found in the last {@link DataGroup} with nameInData "updated" in the
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records last tsUpdated is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the last tsUpdated can not be determined.
	 * 
	 * @return A String with the timestamp of when this DataRecordGroup was last updated
	 */
	String getLatestTsUpdated();

	/**
	 * addUpdatedUsingUserIdAndTs creates and adds a new {@link DataGroup} with nameInData "updated"
	 * to recordInfo. The updated group consists of two parts, "updatedBy" a DataRecordLink with
	 * nameInData "updatedBy", and linkedRecordType "user" and linkedRecordId the provide userId and
	 * "tsUpdated" a DataAtomic with nameInData "tsUpdated" and the provided value. A repeatId is
	 * also automatically set to the updated group, with 0 or one higher than the previous highest
	 * only number repeatId.
	 * </p>
	 * If the {@link DataGroup} recordInfo is missing, should it be automatically added, and the
	 * created updated group added to it.
	 * 
	 * @param userId
	 *            A String with the userId of the user that last updated this DataRecordGroup
	 * @param tsUpdated
	 *            A String with the timestamp of when the record for this DataRecordGroup was last
	 *            updated
	 */
	void addUpdatedUsingUserIdAndTs(String userId, String tsUpdated);

	/**
	 * addUpdatedUsingNowAndUserId creates and adds a new {@link DataGroup} with nameInData
	 * "updated" to recordInfo. The updated group consists of two parts, "updatedBy" a
	 * DataRecordLink with nameInData "updatedBy", and linkedRecordType "user" and linkedRecordId
	 * the provide userId and "tsUpdated" a DataAtomic with nameInData "tsUpdated" and the timestamp
	 * set to now. A repeatId is also automatically set to the updated group, with 0 or one higher
	 * than the previous highest only number repeatId.
	 * </p>
	 * If the {@link DataGroup} recordInfo is missing, should it be automatically added, and the
	 * created updated group added to it.
	 * 
	 * @param userId
	 *            A String with the userId of the user that last updated this DataRecordGroup
	 */
	void addUpdatedUsingUserIdAndTsNow(String userId);

	/**
	 * getAllUpdated gets all {@link DataGroup} with nameInData "updated" from the recordInfo.
	 * </p>
	 * If no "updated" group exists in recordInfo SHOULD a {@link DataMissingException} be thrown
	 * with information about why the updated groups can not be determined.
	 */
	Collection<DataChild> getAllUpdated();

	/**
	 * setAllUpdated adds the provided updated dataChildren to this DataRecordGroups recordInfo.
	 * </p>
	 * Any child with nameInData "updated" that exist since before in recordInfo, is removed before
	 * the provided update children are added.
	 * </p>
	 * If the {@link DataGroup} recordInfo is missing, should it be automatically added, and the
	 * update children added to it.
	 * 
	 * @param updated
	 *            A {@link Collection} containing {@link DataChild} with updated info
	 */
	void setAllUpdated(Collection<DataChild> updated);

	/**
	 * overwriteProtectionShouldBeEnforced returns true if overwrite protection should be enforced
	 * for this DataRecordGroup. This information is derrived from the value of the
	 * {@link DataAtomic} with nameInData "ignoreOverwriteProtection" found in the child
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the records ignoreOverwriteProtection is unknown should the answer to this metod be true
	 * 
	 * @return A boolean that is false if and only if the "ignoreOverwriteProtection" in the
	 *         recordInfo group is present and set to true, else is true returned.
	 */
	boolean overwriteProtectionShouldBeEnforced();

	/**
	 * removeOverwriteProtection removes the atomic with nameInData "ignoreOverwriteProtection" from
	 * recordInfo.
	 */
	void removeOverwriteProtection();

	/**
	 * getTsVisibility returns an Optional with the timestamp of when the visibility of this record
	 * for this DataRecordGroup was last updated. This information is the value from the child
	 * {@link DataAtomic} with nameInData "tsVisibility" found in the {@link DataGroup} with
	 * nameInData "recordInfo".
	 * </p>
	 * If the records tsVisibility is unknown SHOULD a empty Optional be returned.
	 * 
	 * @return An Optional with the timestamp of when the record for this DataRecordGroup was
	 *         created as a String
	 */
	Optional<String> getTsVisibility();

	/**
	 * Returns the current value of the visibility status.
	 */
	Optional<String> getVisibility();

	/**
	 * setTsVisibilityNow sets the the timestamp of when the visibiltiy of the record for this
	 * DataRecordGroup was last updated to now. This information is the value of the
	 * {@link DataAtomic} with nameInData "tsVisibility" found in the child {@link DataGroup} with
	 * nameInData "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} tsVisibility, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the atomics value set to the provided value.
	 */
	void setTsVisibilityNow();

	/**
	 * setTsVisibility sets the the timestamp of when the visibiltiy of the record for this
	 * DataRecordGroup was last updated. This information is the value of the {@link DataAtomic}
	 * with nameInData "tsVisibility" found in the child {@link DataGroup} with nameInData
	 * "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} tsVisibility, or the {@link DataGroup} recordInfo is missing,
	 * should they be automatically added, and the atomics value set to the provided value.
	 * 
	 * @param tsCreated
	 *            A String with the timestamp of when the visibility of the record was last updated
	 */
	void setTsVisibility(String tsVisibility);

	/**
	 * setVisibility sets the the visibiltiy of the record for this DataRecordGroup, there are three
	 * possible values, published, unpublished and hidden. This information is the value of the
	 * {@link DataAtomic} with nameInData "visibility" found in the child {@link DataGroup} with
	 * nameInData "recordInfo".
	 * </p>
	 * If the {@link DataAtomic} visibility, or the {@link DataGroup} recordInfo is missing, should
	 * they be automatically added, and the atomics value set to the provided value.
	 * 
	 * 
	 * @param tsCreated
	 *            A String with the value of the visibility for the record
	 */
	void setVisibility(String visibility);

	/**
	 * getPermissionUnit returns the permission unit for this DataRecordGroup, if exists. This
	 * information is the linkedRecordId for the {@link DataRecordLink} with nameInData
	 * "permissionUnit" found in the {@link DataGroup} with nameInData "recordInfo".
	 * 
	 * @return A Optional of String with the permission unit of this DataRecordGroup. If it does not
	 *         exist then an empty optional is returned.
	 */
	Optional<String> getPermissionUnit();

	/**
	 * setPermissionUnit sets the permission unit for this DataRecordGroup. This information is the
	 * linkedRecordId for the {@link DataRecordLink} with nameInData "permissionUnit" found in the
	 * {@link DataGroup} with nameInData "recordInfo".
	 * </p>
	 * If the permission unit is unknown SHOULD a {@link DataMissingException} be thrown with
	 * information about why the permission unit can not be determined.
	 * 
	 * @param permissionUnit
	 *            A String with the permission unit to set for this DataRecordGroup.
	 */
	void setPermissionUnit(String permissionUnit);
}
