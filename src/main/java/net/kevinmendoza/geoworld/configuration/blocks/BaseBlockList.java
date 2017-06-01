package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.kevinmendoza.geoworld.configuration.blocks.BlockConstants.Rock;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BaseBlockList {

	@Setting
	private HashMap<String,BaseBlock> blockList = new HashMap<>();
	
	public BaseBlockList() {
		BlockConstants constants = new BlockConstants();
		HashMap<String,Rock> blocks = constants.getRocks();
		for(String key : blocks.keySet()) {
			blockList.put(key, blocks.get(key).getBaseBlock());
		}
	}

	
	
}
