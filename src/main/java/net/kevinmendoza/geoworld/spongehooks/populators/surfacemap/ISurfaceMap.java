package net.kevinmendoza.geoworld.spongehooks.populators.surfacemap;

import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.MutableBlockVolume;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;

public interface ISurfaceMap {

	void putSurface(Vector3i vectorKey, ISurface surf);

	ISurface popSurface(Vector3i vectorKey);

	boolean doesNotContain(Vector3i vectorKey);

}
