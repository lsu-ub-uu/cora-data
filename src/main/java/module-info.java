module se.uu.ub.cora.data {
	requires transitive se.uu.ub.cora.json;

	exports se.uu.ub.cora.data;

	uses se.uu.ub.cora.data.DataGroupFactory;
	uses se.uu.ub.cora.data.DataAtomicFactory;

}