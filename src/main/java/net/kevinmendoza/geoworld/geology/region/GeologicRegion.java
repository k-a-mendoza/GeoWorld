package net.kevinmendoza.geoworld.geology.region;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObject;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;


public abstract class GeologicRegion extends GeologicObject implements GeologicObjectInterface {

	private final List<GeologicRegionPrecursor> subRegions;
	private HashMap<Region,GeologicObjectInterface> subGeologicObjects;
	
	public GeologicRegion(GeologicRegionBuilderGetInterface builder) {
		super(builder);
		subRegions = builder.getSubRegionList();
		subGeologicObjects = new HashMap<>();
	}
	
	public boolean isInObject(Vector2i center) {
		return super.isInObject(center);
	}
	
	protected abstract GeologicObjectInterface buildSubGeologicRegion(GeologicRegionPrecursor region);

	protected List<GeologicObjectInterface> getOverlapProvinces(Vector2i center){
		List<GeologicRegionPrecursor> regionsToMake = subRegions.stream().
				filter(p -> p.isInside(center)).collect(Collectors.toList());
		
		List<GeologicObjectInterface> objList = new ArrayList<>();
		for(GeologicRegionPrecursor region : regionsToMake) {
			if(!subGeologicObjects.containsKey(region)) {
				GeologicObjectInterface subGeologicRegion = buildSubGeologicRegion(region);
				subGeologicObjects.put(region.getRegion(), subGeologicRegion);
			}
			objList.add(subGeologicObjects.get(region));
		}
		return objList;		
	}
	
}
