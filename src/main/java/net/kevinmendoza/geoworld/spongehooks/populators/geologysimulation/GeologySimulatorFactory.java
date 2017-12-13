package net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeologySimulatorFactory {

	private static HashSet<Vector3i> keySet;
	private static BlockMap blockMap;
	
	public static IGeologySimulator getSimulator(
			HashMap<String, GeoWorldPlugin> plugins, long seed) {
		return new GeologySimulator.GeologySimulatorBuilder()
				.setSeed(seed)
				.setPlugins(plugins)
				.build();
	}
	
	static BlockMap getBlockMap() {
		if(blockMap==null) {
			blockMap = new BlockMap(16*16*300);
		}
		return blockMap;
	}
	
}
