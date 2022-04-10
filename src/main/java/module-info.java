module se.uu.ub.cora.data {
	requires transitive se.uu.ub.cora.json;

	exports se.uu.ub.cora.data;
	exports se.uu.ub.cora.data.converter;
	exports se.uu.ub.cora.data.copier;

	uses se.uu.ub.cora.data.DataFactory;

	uses se.uu.ub.cora.data.converter.JsonToDataConverterFactory;
	uses se.uu.ub.cora.data.converter.DataToJsonConverterFactoryCreator;
	uses se.uu.ub.cora.data.copier.DataCopierFactory;

}