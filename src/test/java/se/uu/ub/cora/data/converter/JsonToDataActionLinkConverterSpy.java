package se.uu.ub.cora.data.converter;

import se.uu.ub.cora.data.Action;
import se.uu.ub.cora.data.ActionLink;
import se.uu.ub.cora.data.Data;
import se.uu.ub.cora.data.converter.JsonToDataActionLinkConverter;
import se.uu.ub.cora.json.parser.JsonObject;

public class JsonToDataActionLinkConverterSpy implements JsonToDataActionLinkConverter {

	public JsonObject jsonValue;
	public Data returnedElement;

	public JsonToDataActionLinkConverterSpy(JsonObject jsonValue) {
		this.jsonValue = jsonValue;
	}

	@Override
	public Data toInstance() {
		returnedElement = ActionLink.withAction(Action.READ);
		return returnedElement;
	}
}
