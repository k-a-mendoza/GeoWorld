package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.HashMap;
import java.util.List;

import net.kevinmendoza.geoworld.configuration.blocks.BlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.IBehaviorDefaults;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;

class RockFactory {

	public static IRock buildRock(IBlockDefault block) {
		return new Rock.Builder()
				.setRockName(block.getName())
				.setBehaviorList(buildBehaviorList(block))
				.setTexture(buildTexture(block))
				.build();
	}

	private static ITexture buildTexture(
			IBlockDefault block) {
		return new Texture.Builder()
				.setTexture(block.getTextures().getBaseTexture())
				.build();
	}

	private static IAlterationBehavior buildBehavior(AlterationType type, IBehaviorDefaults behaviorDefaults) {
		if(behaviorDefaults.isNull()) {
			return new DefaultAlterationBehavior();
		}
		return new AlterationBehavior.Builder()
				.setCumulative(type.isCumulative())
				.setLowerThreshold(behaviorDefaults.getLowerThreshold())
				.setUpperThreshold(behaviorDefaults.getUpperThreshold())
				.setTarget(behaviorDefaults.getTarget())
				.build();
	}
	
	private static HashMap<AlterationType,IAlterationBehavior> buildBehaviorList(IBlockDefault block) {
		HashMap<AlterationType,IAlterationBehavior> behaviorList = new HashMap<>();
		for(AlterationType type: AlterationType.values()) {
			behaviorList.put(type, buildBehavior(type,block.getBehaviorDefaults(type)));
		}
		return behaviorList;
	}

	public static IRock buildDefaultStone() { return  new DefaultRock(); }
}
