package net.kevinmendoza.geoworld.configuration;

import com.google.inject.AbstractModule;

import net.kevinmendoza.geoworldlibrary.utilities.IDebug;
import net.kevinmendoza.geoworld.main.GeoWorldPluginConnections;
import net.kevinmendoza.geoworld.main.IPluginConnections;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGlobalDefaults.class).to(GlobalDefaults.class);
		bind(IGeneratorDefaults.class).to(GeneratorDefaults.class);
		bind(IDebug.class).to(Debug.class);
		bind(IPluginConnections.class).to(GeoWorldPluginConnections.class);
	}

}
