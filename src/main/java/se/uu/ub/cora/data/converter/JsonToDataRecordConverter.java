package se.uu.ub.cora.data.converter;

import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecord;
import se.uu.ub.cora.json.parser.JsonObject;
import se.uu.ub.cora.json.parser.JsonParseException;

public class JsonToDataRecordConverter {

	private static final String ACTION_LINKS = "actionLinks";
	private static final int NUM_OF_ALLOWED_KEYS = 2;
	private JsonObject jsonObject;
	private JsonObject jsonObjectRecord;
	private JsonToDataConverterFactory factory;
	private DataRecord clientDataRecord;

	public JsonToDataRecordConverter(JsonObject jsonObject, JsonToDataConverterFactory factory) {
		this.jsonObject = jsonObject;
		this.factory = factory;
	}

	public static JsonToDataRecordConverter forJsonObjectUsingConverterFactory(
			JsonObject jsonObject, JsonToDataConverterFactory factory) {
		return new JsonToDataRecordConverter(jsonObject, factory);
	}

	public DataRecord toInstance() {
		try {
			return tryToInstanciate();
		} catch (Exception e) {
			throw new JsonParseException("Error parsing jsonRecord: " + e.getMessage(), e);
		}
	}

	private DataRecord tryToInstanciate() {
		validateOnlyRecordKeyAtTopLevel();
		jsonObjectRecord = jsonObject.getValueAsJsonObject("record");
		validateOnlyCorrectKeysAtSecondLevel();

		DataGroup clientDataGroup = convertDataGroup();

		clientDataRecord = DataRecord.withDataGroup(clientDataGroup);
		// possiblyAddActionLinks();
		return clientDataRecord;
	}

	private DataGroup convertDataGroup() {
		JsonObject jsonDataObject = jsonObjectRecord.getValueAsJsonObject("data");
		JsonToDataConverter converter = factory.createForJsonObject(jsonDataObject);
		return (DataGroup) converter.toInstance();
	}

	// private void possiblyAddActionLinks() {
	// JsonObject actionLinks = jsonObjectRecord.getValueAsJsonObject(ACTION_LINKS);
	// for (Map.Entry<String, JsonValue> actionLinkEntry : actionLinks.entrySet()) {
	// convertAndAddActionLink(actionLinkEntry);
	// }
	// }

	// private void convertAndAddActionLink(Map.Entry<String, JsonValue> actionLinkEntry) {
	// JsonToDataActionLinkConverter actionLinkConverter = factory
	// .createJsonToDataActionLinkConverterForJsonObject(actionLinkEntry.getValue());
	// ActionLink actionLink = (ActionLink) actionLinkConverter.toInstance();
	// clientDataRecord.addActionLink(actionLinkEntry.getKey(), actionLink);
	// }

	private void validateOnlyRecordKeyAtTopLevel() {
		if (!jsonObject.containsKey("record")) {
			throw new JsonParseException("Record data must contain key: record");
		}
		if (jsonObject.keySet().size() != 1) {
			throw new JsonParseException("Record data must contain only key: record");
		}
	}

	private void validateOnlyCorrectKeysAtSecondLevel() {

		if (!jsonObjectRecord.containsKey("data")) {
			throw new JsonParseException("Record data must contain child with key: data");
		}
		if (!jsonObjectRecord.containsKey(ACTION_LINKS)) {
			throw new JsonParseException("Record data must contain child with key: actionLinks");
		}
		if (jsonObjectRecord.keySet().size() != NUM_OF_ALLOWED_KEYS) {
			throw new JsonParseException(
					"Record data must contain only keys: data and actionLinks");
		}
	}

}
