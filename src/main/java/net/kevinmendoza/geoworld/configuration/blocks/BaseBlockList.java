package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.kevinmendoza.geoworld.configuration.IBlockList;
import net.kevinmendoza.geoworld.configuration.blocks.BlockConstants.Rock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BaseBlockList implements IBlockList {

	@Setting
	private List<BlockDefault> blockList;
	
	public BaseBlockList() {
		blockList = new ArrayList<>();
		BlockConstants constants = new BlockConstants();
		HashMap<String,Rock> blocks = constants.getRocks();
		BlockDefault block;
		for(String key : blocks.keySet()) {
			block = blocks.get(key).getBaseBlock();
			blockList.add(block);
		}
	}

	@Override
	public List<IBlockDefault> getBlockList() { 
		List<IBlockDefault> blocks = new ArrayList<>();
		blocks.addAll(blockList);
		return blocks; 
	}

	
}
