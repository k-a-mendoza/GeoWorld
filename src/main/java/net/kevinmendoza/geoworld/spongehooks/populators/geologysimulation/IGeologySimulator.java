package net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation;

import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;

public interface IGeologySimulator {

	IBlockMap getBlockMap(Vector3i vectorKey, ISurface surface,
			ImmutableBiomeVolume relativeBiome);

}
