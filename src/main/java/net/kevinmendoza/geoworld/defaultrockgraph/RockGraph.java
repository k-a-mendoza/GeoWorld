package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.configuration.IBlockList;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;

class RockGraph {

	private HashMap<String,IRock> rockMap;
	@Inject
	private IBlockList blockList;
	
	public RockGraph(){
		rockMap = new HashMap<>();
	}
	
	void populateRockMap() {
		List<IBlockDefault> blocks = blockList.getBlockList();
		IRock rock;
		for(IBlockDefault block : blocks) {
			rock = RockFactory.buildRock(block);
			rockMap.put(rock.getName(), rock);
		}
		rockMap.put("STONE",RockFactory.buildDefaultStone());
	}

	public List<IRock> getRockList(){
		List<IRock> rockList = new ArrayList<>();
		rockList.addAll(rockMap.values());
		return rockList;
	}

	public IRock getRockTexture(AbstractAlteration alteration, AbstractRock emplacedRock) {
		if(!rockMap.containsKey(emplacedRock.getPreEvaluatedRock())){
			return rockMap.get("STONE");
		}
		IRock startingRock = rockMap.get(emplacedRock.getPreEvaluatedRock());
		IRock endingRock = startingRock.applyAlteration(alteration);
		return endingRock;
	}

	public IRock getRock(String key) {
		if(!rockMap.containsKey(key))
			return rockMap.get("STONE");
		else
			return rockMap.get(key);
	}
}
