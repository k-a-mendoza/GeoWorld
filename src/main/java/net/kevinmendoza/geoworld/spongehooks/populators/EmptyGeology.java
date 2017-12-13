package net.kevinmendoza.geoworld.spongehooks.populators;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;

final class EmptyGeology implements IGeology {

	private final AbstractAlteration alteration;
	private final AbstractRock rock;
	private final Surface surface;

	public EmptyGeology() {
		alteration = (AbstractAlteration)EmptyDataFactory.getEmptyDataObject(2);
		rock 	   = (AbstractRock)EmptyDataFactory.getEmptyDataObject(3);
		surface	   = (Surface)EmptyDataFactory.getEmptyDataObject(1);
	}
	@Override
	public void loadNearbyNodes(GenerationData metaData) {
	}

	@Override
	public void primeLoadedObjects(GenerationData metaData) {
	}

	@Override
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat, Vector2i query) {
		return testDat;
	}

	@Override
	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat, Vector3i query) {
		return testDat;
	}

	@Override
	public Order getOrder() {
		return Order.FIRST;
	}
	@Override
	public ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		return EmptyDataFactory.getEmptyDataObject(dataType.getID());
	}
	@Override
	public int getRGBDebugAtCoordinates(Vector3i query) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getLocationData(Vector3i globalVector) {
		return "";
	}


}
