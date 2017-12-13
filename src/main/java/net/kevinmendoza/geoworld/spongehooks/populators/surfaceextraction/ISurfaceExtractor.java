package net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;

public interface ISurfaceExtractor {

	ISurface getSurfaceObject(MutableBlockVolume mutableRelativeBuffer,
			ImmutableBiomeVolume relativeBiomes);

}
