package net.kevinmendoza.geoworld.geology.region;

import java.util.List;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectBuilderGetInterface;

public interface GeologicRegionBuilderGetInterface extends GeologicObjectBuilderGetInterface{
	
	public List<GeologicRegionPrecursor> getSubRegionList();

}
