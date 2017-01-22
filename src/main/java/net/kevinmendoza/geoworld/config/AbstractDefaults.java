package net.kevinmendoza.geoworld.config;

import ninja.leaping.configurate.ConfigurationNode;

public abstract class AbstractDefaults {

	private final String nodeName;
	private final GeoWorldPluginInterface plugin;
	
	public AbstractDefaults(String nodeName,GeoWorldPluginInterface plugin) {
		this.nodeName = nodeName;
		this.plugin = plugin;
	}
	
	public ConfigurationNode getNode(String valName) {
		return plugin.getConfig().getNode(nodeName,valName);
	}
}
