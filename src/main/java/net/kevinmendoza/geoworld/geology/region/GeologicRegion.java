package net.kevinmendoza.geoworld.geology.region;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObject;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectBuilderGetInterface;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;


public abstract class GeologicRegion extends GeologicObject implements GeologicObjectInterface {

	private HashMap<Region,GeologicObjectInterface> subGeologicObjects;
	private HashMap<String, Integer> subRegions;
	
	public GeologicRegion(GeologicRegionBuilderGetInterface builder) {
		super(builder);
		subRegions = builder.getSubRegions();
		subGeologicObjects = new HashMap<>();
	}
	
	public boolean isInObject(Vector2i center) {
		boolean b = super.isInObject(center);
		if(b) {
			populateSubRegions();
		}
		return b;
	}
	
	private void populateSubRegions() {
		for(String key : subRegions.keySet()) {
			for(int i = 0;i<subRegions.get(key);i++) {
				GeologicObjectInterface newObject = makeSubRegion(key,super.getRegion().getRandPoint());
				subGeologicObjects.put(newObject.getRegion(), newObject);
			}
		}
	}

	protected abstract GeologicObjectInterface makeSubRegion(String region,Vector2i center);
	
	protected List<GeologicObjectInterface> getOverlappingObjects(Vector2i center) {
		Collection<GeologicObjectInterface> geoObj = subGeologicObjects.values();
		List<GeologicObjectInterface> regionsToMake = geoObj.parallelStream().
				filter(p -> p.isInObject(center)).collect(Collectors.toList());
		return regionsToMake;	
	}
}
