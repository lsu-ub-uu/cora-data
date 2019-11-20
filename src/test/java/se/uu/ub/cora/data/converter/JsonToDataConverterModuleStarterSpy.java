package se.uu.ub.cora.data.converter;

import se.uu.ub.cora.data.starter.JsonToDataConverterModuleStarter;

public class JsonToDataConverterModuleStarterSpy implements JsonToDataConverterModuleStarter {

	public boolean startWasCalled = false;

	@Override
	public void startUsingConverterFactoryImplementations(
			Iterable<JsonToDataConverterFactory> converterFactoryImplementations) {
		startWasCalled = true;
	}

	@Override
	public JsonToDataConverterFactory getJsonToDataConverterFactory() {
		return new JsonToDataConverterFactorySpy();
	}

}
