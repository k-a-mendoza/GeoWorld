package net.kevinmendoza.geoworld.spongehooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.PopulatorTypes;
import org.spongepowered.api.world.gen.WorldGenerator;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.geology.GeologicContainer;

public class GeoWorldGeneratorModifier  {

	private final List<GeologicContainer> geologyContainers;
	private Debug debug;

	public GeoWorldGeneratorModifier(List<GeologicContainer> geologyContainers,Debug debug) {
		this.debug = debug;
		Collections.sort(geologyContainers, 
				(g1,  g2) -> 
		        g1.getOrder().compareTo(g2.getOrder()));
		this.geologyContainers = geologyContainers;
	}
	
	protected void removeDefaultOres(WorldGenerator generator) {
		List<Populator> populators = generator.getPopulators();
		populators.removeIf(p -> p.getType() == PopulatorTypes.ORE);
		BiomeGenerationSettings settings;
		for (BiomeType type : Sponge.getRegistry().getAllOf(BiomeType.class)) {
			settings = generator.getBiomeSettings(type);
			settings.getPopulators()
					.removeIf(p -> p.getType() == PopulatorTypes.ORE);
		}
	}

	protected class GeoWorldPopulator implements GenerationPopulator {

		public GeoWorldPopulator() {}
		
		@Override
	    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
			Vector3i min = buffer.getBlockMin();
			Vector3i max = buffer.getBlockMax();
			long start = System.nanoTime();
			for(GeologicContainer container : geologyContainers) 
			{ container.primeGenerationAt(new Vector2i(min.getX(),min.getZ())); }
			for(GeologicContainer container : geologyContainers) 
			{ container.getColumnAt(new Vector2i(min.getX(), min.getZ())); }
			double end_time = System.nanoTime();
			double difference = (end_time - start)/1e6;
			debug.log("ms to generate:"+difference);
		}

	}
}

