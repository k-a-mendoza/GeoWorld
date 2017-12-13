package net.kevinmendoza.geoworld.configuration;

import com.google.inject.AbstractModule;

import net.kevinmendoza.geoworldlibrary.utilities.IDebug;
import net.kevinmendoza.geoworld.configuration.blocks.BaseBlockList;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGlobalDefaults.class).to(GlobalDefaults.class);
		bind(IGeneratorDefaults.class).to(GeneratorDefaults.class);
		bind(IDebug.class).to(Debug.class);
		bind(IBlockList.class).to(BaseBlockList.class);
	}

}
