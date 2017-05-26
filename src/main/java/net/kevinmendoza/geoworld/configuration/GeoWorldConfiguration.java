package net.kevinmendoza.geoworld.configuration;

import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class GeoWorldConfiguration {
	public static final TypeToken<GeoWorldConfiguration> type = TypeToken.of(GeoWorldConfiguration.class);
	@Setting
	GlobalDefaults globalDefaults;
	@Setting
	GeneratorDefaults generatorDefaults;

	public GeoWorldConfiguration() {
		globalDefaults 		= new GlobalDefaults();
		generatorDefaults   = new GeneratorDefaults();
	}

}
