module se.uu.ub.cora.data {
	requires transitive se.uu.ub.cora.json;

	exports se.uu.ub.cora.data;
	exports se.uu.ub.cora.data.converter;

	uses se.uu.ub.cora.data.DataGroupFactory;
	uses se.uu.ub.cora.data.DataAtomicFactory;
	uses se.uu.ub.cora.data.DataRecordFactory;
	uses se.uu.ub.cora.data.DataAttributeFactory;
	uses se.uu.ub.cora.data.converter.JsonToDataConverterFactory;
	uses se.uu.ub.cora.data.converter.DataToJsonConverterFactory;

}