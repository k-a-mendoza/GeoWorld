package net.kevinmendoza.geoworld.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class GeoWorldConfiguration {
	static final TypeToken<GeoWorldConfiguration> type = TypeToken.of(GeoWorldConfiguration.class);
	@Setting
	IGlobalDefaults globalDefaults;
	@Setting
	IGeneratorDefaults generatorDefaults;

	public GeoWorldConfiguration() {
		globalDefaults 		= new GlobalDefaults();
		generatorDefaults   = new GeneratorDefaults();
	}

}
