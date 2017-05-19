package net.kevinmendoza.geoworld.spongehooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.plugin.PluginContainer;
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
import com.google.inject.Inject;

import net.kevinmendoza.geoworld.config.GeoWorldMain;
import net.kevinmendoza.geoworld.config.IGeneratorDefaults;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeoWorldGeneratorModifier  {

	@Inject
	IGeneratorDefaults generatorDefaults;

	private final List<PluginContainer> geologyContainers;

	public GeoWorldGeneratorModifier() {
		this.geologyContainers = GeoWorldMain.GetValidPluginContainers();
	}
	
	protected void removeDefaultOres(WorldGenerator generator) {
		if(generatorDefaults.removeVanillaOres()) {
			List<Populator> populators = generator.getPopulators();
			populators.removeIf(p -> p.getType() == PopulatorTypes.ORE);
			BiomeGenerationSettings settings;
			for (BiomeType type : Sponge.getRegistry().getAllOf(BiomeType.class)) {
				settings = generator.getBiomeSettings(type);
				settings.getPopulators()
				.removeIf(p -> p.getType() == PopulatorTypes.ORE);
			}
		}
	}
	
	protected List<IGeology> getIGeologyList(long seed){
		List<IGeology> geologyList = new ArrayList<>();
		for(PluginContainer container : geologyContainers) {
			GeoWorldPlugin geoWorldPlugin = (GeoWorldPlugin)container;
			geologyList.add(geoWorldPlugin.getGeology(seed));
		}
		return geologyList;
	}

	/*protected class GeoWorldPopulator implements GenerationPopulator {

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

	}*/
}

