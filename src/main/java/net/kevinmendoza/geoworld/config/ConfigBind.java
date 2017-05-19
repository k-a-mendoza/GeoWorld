package net.kevinmendoza.geoworld.config;

import com.google.inject.AbstractModule;

import net.kevinmendoza.geoworldlibrary.utilities.IDebug;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGlobalDefaults.class).to(GlobalDefaults.class);
		bind(IGeneratorDefaults.class).to(GeneratorDefaults.class);
		bind(IDebug.class).to(Debug.class);
	}

}
