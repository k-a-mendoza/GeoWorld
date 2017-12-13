package net.kevinmendoza.geoworld.spongehooks.populators.surfacemap;

import java.util.HashMap;

import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.MutableBlockVolume;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;

class SurfaceMap implements ISurfaceMap {

	private HashMap<Vector3i,ISurface> map;

	SurfaceMap(){ map = new HashMap<>(); }
	
	public void putSurface(Vector3i vectorKey, ISurface surf) {
		map.put(vectorKey, surf);
	}

	public ISurface popSurface(Vector3i vectorKey) {
		ISurface surface = map.get(vectorKey);
		map.remove(vectorKey);
		return surface;
	}

	@Override
	public boolean doesNotContain(Vector3i vectorKey) {
		return !map.containsKey(vectorKey);
	}

}
