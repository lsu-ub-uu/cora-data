package se.uu.ub.cora.data.converter;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataPart;
import se.uu.ub.cora.json.parser.JsonObject;

public class JsonToDataConverterSpy implements JsonToDataConverter {

	public JsonObject jsonValue;
	public DataPart returnedElement;

	public JsonToDataConverterSpy(JsonObject jsonValue) {
		this.jsonValue = jsonValue;
	}

	@Override
	public DataPart toInstance() {
		if (jsonValue.containsKey("name") && jsonValue.containsKey("value")) {
			returnedElement = DataAtomic.withNameInDataAndValue("atomicNameInData", "atomicValue");
		} else {
			String nameInData = jsonValue.getValueAsJsonString("name").getStringValue();
			returnedElement = DataGroup.withNameInData(nameInData);
		}
		return returnedElement;
	}
}
