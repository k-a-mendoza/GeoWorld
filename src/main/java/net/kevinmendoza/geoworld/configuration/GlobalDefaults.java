package net.kevinmendoza.geoworld.configuration;

import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class GlobalDefaults implements IGlobalDefaults {
	static final TypeToken<GlobalDefaults> type = TypeToken.of(GlobalDefaults.class);
	@Setting
	private List<String> generatorIDs;
	@Setting
	private List<String> transformerIDs;
	
	GlobalDefaults(){
		generatorIDs = new ArrayList<>();
		transformerIDs = new ArrayList<>();
		generatorIDs.add("igneouspack");
		generatorIDs.add("sedimentarysequences");
		transformerIDs.add("geology2minecraftclassic");
	}

	public List<String> getGeneratorIDs() {
		return generatorIDs;
	}

	public List<String> getTransformerIDs() {
		return transformerIDs;
	}
	

}
