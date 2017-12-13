package net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction;

import java.util.HashMap;

import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.blockconversion.BlockStateCreatorAccess;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.SurfaceExtractor.SurfaceExtractorBuilder;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class SurfaceExtractorFactory {

	public static ISurfaceExtractor createSurfaceExtractor(
			HashMap<String, GeoWorldPlugin> plugins, long seed) {
		SurfaceExtractorBuilder builder = new SurfaceExtractorBuilder()
				.setBlockStateIdentifier(BlockStateCreatorAccess.GetBlockStateIdentifier())
				.setPlugins(plugins)
				.setSeed(seed);
		return builder.build();
	}

	static ISurface createISurface(int[][] surface) {
		return new Surface(surface);
	}

}
