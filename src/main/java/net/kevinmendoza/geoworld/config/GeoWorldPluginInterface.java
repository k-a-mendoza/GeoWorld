package net.kevinmendoza.geoworld.config;

import java.util.List;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import ninja.leaping.configurate.ConfigurationNode;

public interface GeoWorldPluginInterface {

	
	public GeoWorldPluginInterface GetInstance();
	public Debug getDebugger();
	
	public List<GeologicContainer> getGeologicContainers();
}
