/*
 * Copyright 2019, 2025 Uppsala University Library
 * Copyright 2022 Olov McKie
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

import java.util.ServiceLoader;

import se.uu.ub.cora.data.starter.DataModuleStarter;
import se.uu.ub.cora.data.starter.DataModuleStarterImp;

/**
 * DataProvider provides a means for other classes in the system to create instances of different
 * data classes while maintaing an easy possiblility to replace the implementing classes.
 * <p>
 * DataProvider uses javas module system to load an implementation of a DataFactory which is
 * expected to be implemented in a different module. Changing of implementations of data classes in
 * the entire system is then as easy as providing a different module implementing the data
 * interfaces.
 * <p>
 * DataProvider has a number of static methods that start with createX that is intended to be used
 * to create all instances of data classes in the system.
 * <p>
 * To help with testing is there a metod {@link DataProvider#onlyForTestSetDataFactory(DataFactory)}
 * that makes it possible to change the implementing data classes while testing.
 */
public class DataProvider {

	private static DataFactory dataFactory;
	private static DataModuleStarter dataModuleStarter = new DataModuleStarterImp();

	private DataProvider() {
		// not called
		throw new UnsupportedOperationException();
	}

	/**
	 * onlyForTestSetDataRecordFactory sets a DataFactory that will be used to factor data classes
	 * when other classes needs to create new instances of data classes. This possibility to set a
	 * DataRecordFactory is provided to enable testing of data creation in other classes and is not
	 * intented to be used in production.
	 * <p>
	 * The DataFactory to use in production should be provided through an implementation of
	 * DataFactory in a seperate java module.
	 * 
	 * @param dataFactory
	 *            A DataFactory to use to create data classes for testing
	 */
	public static void onlyForTestSetDataFactory(DataFactory dataFactory) {
		DataProvider.dataFactory = dataFactory;
	}

	private static void getDataFactoryImpUsingModuleStarter() {
		Iterable<DataFactory> dataFactoryImplementations = ServiceLoader.load(DataFactory.class);
		dataModuleStarter.startUsingDataFactoryImplementations(dataFactoryImplementations);
		dataFactory = dataModuleStarter.getDataFactory();
	}

	static void setStarter(DataModuleStarter starter) {
		dataModuleStarter = starter;
	}

	static DataModuleStarter getStarter() {
		return dataModuleStarter;
	}

	private static synchronized void ensureDataFactoryIsSet() {
		if (null == dataFactory) {
			getDataFactoryImpUsingModuleStarter();
		}
	}

	public static DataList createListWithNameOfDataType(String nameOfDataType) {
		ensureDataFactoryIsSet();
		return dataFactory.factorListUsingNameOfDataType(nameOfDataType);
	}

	public static DataRecord createRecordWithDataRecordGroup(DataRecordGroup dataRecordGroup) {
		ensureDataFactoryIsSet();
		return dataFactory.factorRecordUsingDataRecordGroup(dataRecordGroup);
	}

	public static DataRecordGroup createRecordGroupUsingNameInData(String nameInData) {
		ensureDataFactoryIsSet();
		return dataFactory.factorRecordGroupUsingNameInData(nameInData);
	}

	/**
	 * createRecordGroupFromDataGroup creates a {@link DataRecordGroup} from a {@link DataGroup}.
	 * 
	 * @param dataGroup
	 *            A DataGroup to turn into a DataRecordGroup.
	 * @return A DataRecordGroup with the same nameInData, attributes and children as the provided
	 *         DataGroup
	 */
	public static DataRecordGroup createRecordGroupFromDataGroup(DataGroup dataGroup) {
		ensureDataFactoryIsSet();
		return dataFactory.factorRecordGroupFromDataGroup(dataGroup);
	}

	/**
	 * createGroupFromRecordGroup creates a {@link DataGroup} from a {@link DataRecordGroup}.
	 * 
	 * @param dataRecordGroup
	 *            A DataRecordGroup to turn into a DataGroup.
	 * @return A DataGroup with the same nameInData, attributes and children as the provided
	 *         DataRecordGroup
	 */
	public static DataGroup createGroupFromRecordGroup(DataRecordGroup dataRecordGroup) {
		ensureDataFactoryIsSet();
		return dataFactory.factorGroupFromDataRecordGroup(dataRecordGroup);
	}

	/**
	 * createGroupUsingNameInData creates a {@link DataGroup} with the specified nameInData.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataGroup.
	 * @return A new DataGroup with the specified nameInData.
	 */
	public static DataGroup createGroupUsingNameInData(String nameInData) {
		ensureDataFactoryIsSet();
		return dataFactory.factorGroupUsingNameInData(nameInData);
	}

	/**
	 * createRecordLinkUsingNameInData creates a {@link DataRecordLink} with the specified
	 * nameInData.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataRecordLink.
	 * @return A new DataRecordLink with the specified nameInData.
	 */
	public static DataRecordLink createRecordLinkUsingNameInData(String nameInData) {
		ensureDataFactoryIsSet();
		return dataFactory.factorRecordLinkUsingNameInData(nameInData);
	}

	/**
	 * createRecordLinkUsingNameInDataAndTypeAndId creates a {@link DataRecordLink} with the
	 * specified nameInData, record type, and record ID.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataRecordLink.
	 * @param recordType
	 *            The type of the record to link to.
	 * @param recordId
	 *            The ID of the record to link to.
	 * @return A new DataRecordLink with the specified parameters.
	 */
	public static DataRecordLink createRecordLinkUsingNameInDataAndTypeAndId(String nameInData,
			String recordType, String recordId) {
		ensureDataFactoryIsSet();
		return dataFactory.factorRecordLinkUsingNameInDataAndTypeAndId(nameInData, recordType,
				recordId);
	}

	/**
	 * createResourceLinkUsingNameInDataAndTypeAndIdAndMimeType creates a {@link DataResourceLink}
	 * with the specified nameInData, record type, record ID, and MIME type.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataResourceLink.
	 * @param recordType
	 *            The type of the record to link to.
	 * @param recordId
	 *            The ID of the record to link to.
	 * @param mimeType
	 *            The MIME type of the resource.
	 * @return A new DataResourceLink with the specified parameters.
	 */
	public static DataResourceLink createResourceLinkUsingNameInDataAndTypeAndIdAndMimeType(
			String nameInData, String recordType, String recordId, String mimeType) {
		ensureDataFactoryIsSet();
		return dataFactory.factorResourceLinkUsingNameInDataAndTypeAndIdAndMimeType(nameInData, recordType,
				recordId, mimeType);
	}

	/**
	 * createAtomicUsingNameInDataAndValue creates a {@link DataAtomic} with the specified
	 * nameInData and value.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataAtomic.
	 * @param value
	 *            The value of the new DataAtomic.
	 * @return A new DataAtomic with the specified parameters.
	 */
	public static DataAtomic createAtomicUsingNameInDataAndValue(String nameInData, String value) {
		ensureDataFactoryIsSet();
		return dataFactory.factorAtomicUsingNameInDataAndValue(nameInData, value);
	}

	/**
	 * createAtomicUsingNameInDataAndValueAndRepeatId creates a {@link DataAtomic} with the
	 * specified nameInData, value, and repeat ID.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataAtomic.
	 * @param value
	 *            The value of the new DataAtomic.
	 * @param repeatId
	 *            The repeat ID of the new DataAtomic.
	 * @return A new DataAtomic with the specified parameters.
	 */
	public static DataAtomic createAtomicUsingNameInDataAndValueAndRepeatId(String nameInData,
			String value, String repeatId) {
		ensureDataFactoryIsSet();
		return dataFactory.factorAtomicUsingNameInDataAndValueAndRepeatId(nameInData, value,
				repeatId);
	}

	/**
	 * createAttributeUsingNameInDataAndValue creates a {@link DataAttribute} with the specified
	 * nameInData and value.
	 * 
	 * @param nameInData
	 *            The nameInData to use for the new DataAttribute.
	 * @param value
	 *            The value of the new DataAttribute.
	 * @return A new DataAttribute with the specified parameters.
	 */
	public static DataAttribute createAttributeUsingNameInDataAndValue(String nameInData,
			String value) {
		ensureDataFactoryIsSet();
		return dataFactory.factorAttributeUsingNameInDataAndValue(nameInData, value);
	}

	/**
	 * Creates a DataChildFilter using the specified child nameInData.
	 * 
	 * @param childNameInData
	 *            The child nameInData.
	 * @return A new DataChildFilter instance.
	 */
	public static DataChildFilter createDataChildFilterUsingChildNameInData(
			String childNameInData) {
		ensureDataFactoryIsSet();
		return dataFactory.factorDataChildFilterUsingNameInData(childNameInData);
	}

}
