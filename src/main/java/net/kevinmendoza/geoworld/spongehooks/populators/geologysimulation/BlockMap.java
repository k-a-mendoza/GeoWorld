package net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation;

import java.util.HashMap;
import java.util.Set;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;

class BlockMap implements IBlockMap {

	private HashMap<Vector3i,AbstractAlteration> alterationMap;
	private HashMap<Vector3i,AbstractRock> rockMap;
	
	BlockMap(int capacitySize) {
		alterationMap = new HashMap<>(capacitySize);
		rockMap = new HashMap<>(capacitySize);
	}
	public void put(Vector3i queryVector, AbstractAlteration alt, AbstractRock r) {
		alterationMap.put(queryVector, alt);
		rockMap.put(queryVector, r);
	}
	@Override
	public AbstractRock getRock(Vector3i key) {
		AbstractRock rock = rockMap.get(key);
		rockMap.put(key, null);
		return rock;
	}
	@Override
	public AbstractAlteration getAlteration(Vector3i key) {
		AbstractAlteration alt = alterationMap.get(key);
		alterationMap.put(key, null);
		return alt;
	}

}
