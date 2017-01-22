package net.kevinmendoza.geoworld.geology.geologicobject;

import com.flowpowered.math.vector.Vector2i;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;

public abstract class GeologicObject implements GeologicObjectInterface {

	private final Region region;

	public GeologicObject(GeologicObjectBuilderGetInterface builder) {
		region = builder.getRegion();
	}
	
	@Override
	public boolean isInObject(Vector2i center) {
		return region.isInside(center);
	}

}
