package net.kevinmendoza.geoworld.geology.region;

import java.util.HashMap;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectBuilderGetInterface;

public interface GeologicRegionBuilderGetInterface extends GeologicObjectBuilderGetInterface{

	public HashMap<String,Integer> getSubRegions();
}
