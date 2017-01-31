package net.kevinmendoza.geoworld.geology.regionmap;

import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.geology.SeedFactory;

public interface GeologicRegionMapBuilderGetInterface {

	double getSpacing();
	double getFrequency();
	SeedFactory getFactory();
	LithogenicOrder getOrder();
}
