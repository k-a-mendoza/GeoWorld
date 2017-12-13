package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.spongepowered.api.block.BlockState;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BlockDefault implements IBlockDefault {

	@Setting
	private BehaviorMap behaviorMap;
	@Setting 
	private String name;
	@Setting
	private BlockTexture texture;
	
	BlockDefault(Builder builder){
		behaviorMap 	= builder.getBehaviors();
		name	 		= builder.getName();
		texture 		= builder.getTextures();
	}
	
	@Override
	public String getName() { return name; }

	@Override
	public ITextureDefault getTextures() { return texture; }

	@Override
	public IBehaviorDefaults getBehaviorDefaults(AlterationType type) { return behaviorMap.getBehavior(type); }
	
	public static class Builder {

		private BehaviorMap behaviors;
		private BlockTexture textures;
		private String name;
		
		private BehaviorMap getBehaviors() { return behaviors;	}
		private BlockTexture getTextures() { return textures; }
		private String getName() { return name; }
		
		public Builder setName(String n) {this.name = n; return this; }
		public Builder setTextures(BlockTexture s) {textures = s; return this; }
		public Builder setBehaviorMap(BehaviorMap map) {behaviors = map; return this; }
		
		public BlockDefault build() {
			return new BlockDefault(this);
		}
	}
}
