package net.kevinmendoza.geoworld.spongehooks.generators;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.storage.WorldProperties;

public interface IRockVolumeExtractor {

	IRockVolume getSurface(World world, MutableBlockVolume buffer, ImmutableBiomeVolume
			biomes);

}
