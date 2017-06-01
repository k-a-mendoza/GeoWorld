package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.block.BlockState;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class BaseBlock {
	@Setting
	private List<String> textures = new ArrayList<>();
	@Setting
	private HashMap<String,ChangeConditions> behavior = new HashMap<>();
	
	
	void setConditions(String key, ChangeConditions conditions) {
		behavior.put(key, conditions);
	}
	
	void setTextures(String[] string) {
		for(int i = 0;i<string.length;i++)
			textures.add(string[i]);
	}
	
	
}
