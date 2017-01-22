package net.kevinmendoza.geoworld.geology.regionmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;

public class GeologicRegionMapCache<T extends GeologicObjectInterface>{

	private HashMap<Vector2i,T> geoMap;
	
	public GeologicRegionMapCache() {
		geoMap = new HashMap<>();
	}

	public List<Vector2i> checkKeys(List<Vector2i> centers) {
		List<Vector2i> potentialVectors = new ArrayList<>();
		for(Vector2i vec : centers) {
			if(!geoMap.containsKey(vec)){
				potentialVectors.add(vec);
			}
		}
		return potentialVectors;
	}
	
	public void addRegion(Vector2i vec, T region) {
		geoMap.put(vec, region);
	}

	public List<T> getNeighboringRegions(
			List<Vector2i> surroundingKeys) {
		List<T> regions = new ArrayList<>();
		T region;
		for(Vector2i vec : surroundingKeys) {
			region = geoMap.get(vec);
			if(region!=null) {
				regions.add(region);
			}
		}
		return regions;
	}

}
