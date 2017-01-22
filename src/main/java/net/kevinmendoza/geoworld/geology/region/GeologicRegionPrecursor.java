package net.kevinmendoza.geoworld.geology.region;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;

public class GeologicRegionPrecursor {
	private final String type;
	private final Region region;
	public GeologicRegionPrecursor(String type, Region region) {
		this.type = type;
		this.region = region;
	}
	public boolean isInside(Vector2i vec) { return region.isInside(vec); }
	public String getType() { return type; }
	public Region getRegion() { return region; };
}
