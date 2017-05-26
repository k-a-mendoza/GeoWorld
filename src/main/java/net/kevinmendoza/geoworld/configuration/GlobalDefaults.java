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
	private List<String> pluginIDs;
	
	GlobalDefaults(){
		pluginIDs = new ArrayList<>();
		pluginIDs.add("igneouspack");
		
	}
	public boolean isValidPluginID(String string) { return pluginIDs.contains(string); }

}
