package net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators;

import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.IBlockMap;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;

public interface IBlockStateCalculator {

	void applyMapToVolume(Vector3i vectorKey, ISurface surf, IBlockMap map, MutableBlockVolume mutableRelativeBuffer, boolean dEBUG);

}
