/*
 * Copyright 2018 Uppsala University Library
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

package se.uu.ub.cora.data.converter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.json.parser.JsonObject;
import se.uu.ub.cora.json.parser.JsonParseException;
import se.uu.ub.cora.json.parser.JsonParser;
import se.uu.ub.cora.json.parser.JsonValue;
import se.uu.ub.cora.json.parser.org.OrgJsonParser;

public class JsonToDataActionLinkConverterTest {
	private JsonParser jsonParser;
	private JsonToDataConverterFactorySpy factory;

	@BeforeMethod
	public void beforeMethod() {
		jsonParser = new OrgJsonParser();
	}

	@Test(expectedExceptions = JsonParseException.class, expectedExceptionsMessageRegExp = "Action link data must contain key: rel")
	public void testToClassWithNoAction() {
		String json = "{}";
		createClientDataActionLinkForJsonString(json);
	}

	private ActionLink createClientDataActionLinkForJsonString(String json) {
		factory = new JsonToDataConverterFactorySpy();
		JsonValue jsonValue = jsonParser.parseString(json);

		JsonToDataActionLinkConverter jsonToDataConverter = JsonToDataActionLinkConverterImp
				.forJsonObjectUsingFactory((JsonObject) jsonValue, factory);
		ClientData clientData = jsonToDataConverter.toInstance();
		return (ActionLink) clientData;
	}

	@Test(expectedExceptions = JsonParseException.class, expectedExceptionsMessageRegExp = "Action link data must contain key: url")
	public void testToClassWithNoURL() {
		String json = "{\"requestMethod\":\"GET\",\"rel\":\"read\"}";
		createClientDataActionLinkForJsonString(json);
	}

	@Test(expectedExceptions = JsonParseException.class, expectedExceptionsMessageRegExp = "Action link data must contain key: requestMethod")
	public void testToClassWithNoRequestMethod() {
		String json = "{\"rel\":\"read\",\"url\":\"https://cora.epc.ub.uu.se/systemone/rest/record/presentationGroup/loginFormNewPGroup\"}";
		createClientDataActionLinkForJsonString(json);
	}

	@Test
	public void testToClassWithNecessaryContent() {
		String json = "{\"requestMethod\":\"GET\",\"rel\":\"read\",\"url\":\"https://cora.epc.ub.uu.se/systemone/rest/record/presentationGroup/loginFormNewPGroup\"}";
		ActionLink clientDataActionLink = createClientDataActionLinkForJsonString(json);
		assertEquals(clientDataActionLink.getAction(), Action.READ);
		assertEquals(clientDataActionLink.getURL(),
				"https://cora.epc.ub.uu.se/systemone/rest/record/presentationGroup/loginFormNewPGroup");
		assertEquals(clientDataActionLink.getRequestMethod(), "GET");
		assertEquals(factory.numberOfTimesCalled, 0);
	}

	@Test
	public void testToClassWithExtraContent() {
		String json = "{\"requestMethod\":\"POST\",\"rel\":\"index\",\"body\":{\"children\":[{\"children\":[{\"name\":\"linkedRecordType\",\"value\":\"recordType\"},{\"name\":\"linkedRecordId\",\"value\":\"book\"}],\"name\":\"recordType\"},{\"name\":\"recordId\",\"value\":\"book:39921376484193\"},{\"name\":\"type\",\"value\":\"index\"}],\"name\":\"workOrder\"},\"contentType\":\"application/vnd.uub.record+json\",\"url\":\"https://cora.epc.ub.uu.se/systemone/rest/record/workOrder/\",\"accept\":\"application/vnd.uub.record+json\"}";
		ActionLink clientDataActionLink = createClientDataActionLinkForJsonString(json);
		assertEquals(clientDataActionLink.getAction(), Action.INDEX);
		assertEquals(clientDataActionLink.getURL(),
				"https://cora.epc.ub.uu.se/systemone/rest/record/workOrder/");
		assertEquals(clientDataActionLink.getRequestMethod(), "POST");
		assertEquals(clientDataActionLink.getAccept(), "application/vnd.uub.record+json");
		assertEquals(clientDataActionLink.getContentType(), "application/vnd.uub.record+json");
		assertEquals(factory.numberOfTimesCalled, 1);
		assertEquals(clientDataActionLink.getBody(),
				factory.factoredConverters.get(0).returnedElement);
	}
}
