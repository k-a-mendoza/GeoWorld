package net.kevinmendoza.geoworld.spongehooks.populators;

import java.util.Random;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.PopulatorTypes;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators.IBlockStateCalculator;
import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.IBlockMap;
import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.IGeologySimulator;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurfaceExtractor;
import net.kevinmendoza.geoworld.spongehooks.populators.surfacemap.ISurfaceMap;

public class GeoWorldPopulator implements GenerationPopulator {

	private final boolean DEBUG = true;
	private IBlockStateCalculator blockStateCalculator;
	private ISurfaceExtractor surfaceExtractor;
	private IGeologySimulator geologySimulator;
	private GenerationPopulator generationPopulator;
	private ISurfaceMap surfaceMap;
	
	public GeoWorldPopulator(GeoWorldPopulatorBuilder builder) {
		generationPopulator = builder.getBaseGenerationPopulator();
		blockStateCalculator = builder.getBlockStateCalculator();
		surfaceExtractor = builder.getSurfaceExtractor();
		geologySimulator = builder.getGeologySimulator();
		surfaceMap = builder.getSurfaceMap();
	}

	public PopulatorType getType() { return PopulatorTypes.GENERIC_BLOCK; }
	
	@Override
	public final void populate(World world, MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) {
		Vector3i vectorKey = buffer.getBlockMin();
		MutableBlockVolume mutableRelativeBuffer = getMutableRelativeBuffer(buffer);
		ImmutableBiomeVolume relativeBiome  = getRelativeBiome(biomes);
		
		generationPopulator.populate(world, buffer, biomes);
		
		ISurface surf = surfaceExtractor.getSurfaceObject(mutableRelativeBuffer,relativeBiome);
		IBlockMap map = geologySimulator.getBlockMap(vectorKey,surf,relativeBiome);
		
		blockStateCalculator.applyMapToVolume(vectorKey,surf,map,mutableRelativeBuffer,DEBUG);
	}
	
	private MutableBlockVolume getMutableRelativeBuffer(MutableBlockVolume volume) {
		return volume.getRelativeBlockView();
	}

	private ImmutableBiomeVolume getRelativeBiome(ImmutableBiomeVolume volume) {
		return volume.getRelativeBiomeView();
	}


	static class GeoWorldPopulatorBuilder {

		private IBlockStateCalculator stateCalculator;
		private ISurfaceExtractor surfaceExtractor;
		private GenerationPopulator populator;
		private IGeologySimulator geologySimulator;
		private ISurfaceMap surfaceMap;
		
		public GeoWorldPopulatorBuilder setBlockStateCalculator(IBlockStateCalculator calculator) {
			this.stateCalculator = calculator; return this; 
		}
		public GeoWorldPopulatorBuilder setGenerationPopulator(GenerationPopulator populator) {
			this.populator = populator; return this; 
		}
		public GeoWorldPopulatorBuilder setSurfaceExtractor(ISurfaceExtractor extractor) {
			this.surfaceExtractor = extractor; return this; 
		}
		public GeoWorldPopulatorBuilder setGeologySimulator(IGeologySimulator simulator) {
			this.geologySimulator = simulator; return this; 
		}
		public GeoWorldPopulatorBuilder setSurfaceMap(ISurfaceMap surfaceMap) {
			this.surfaceMap = surfaceMap; return this; 
		}
		private GenerationPopulator getBaseGenerationPopulator() { return populator; }
		private IBlockStateCalculator getBlockStateCalculator() { return stateCalculator; }
		private ISurfaceExtractor getSurfaceExtractor() { return surfaceExtractor; }
		private IGeologySimulator getGeologySimulator() { return geologySimulator; }
		private ISurfaceMap		  getSurfaceMap() { return surfaceMap; }
		
		public GeoWorldPopulator build() { return new GeoWorldPopulator(this); }
	}
}
