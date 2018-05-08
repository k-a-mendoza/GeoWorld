package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import net.kevinmendoza.geoworld.configuration.IGeneratorDefaults;
import net.kevinmendoza.geoworld.configuration.IGlobalDefaults;
import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworld.main.IPluginConnections;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldPlugin;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldRockTransformer;

public class GeoWorldGeneratorModifier  {

	@Inject
	protected IGeneratorDefaults generatorDefaults;
	@Inject
	protected IGlobalDefaults 	 globalDefaults;
	@Inject
	protected IPluginConnections pluginConnections;

	public GeoWorldGeneratorModifier() {
	}

	protected void removeDefaultOres(WorldGenerator generator) {
		applyGeneratorSettings(generator);
		List<Populator> populators = generator.getPopulators();
		populators.removeIf(p -> p.getType() == PopulatorTypes.ORE);
		BiomeGenerationSettings settings;
		for (BiomeType type : Sponge.getRegistry().getAllOf(BiomeType.class)) {
			settings = generator.getBiomeSettings(type);
			applyBiomeSettings(type,settings);
		}
	}

	private void applyGeneratorSettings(WorldGenerator worldGenerator) {
		if(generatorDefaults.clearPopulators()) {
			worldGenerator.getPopulators().clear();
		}
		if(generatorDefaults.clearGenerationOres()) {
			worldGenerator.getPopulators().removeIf(p -> p.getType() == PopulatorTypes.ORE);
		}
		if(generatorDefaults.clearGenerationPopulators()) {
			worldGenerator.getGenerationPopulators().clear();
		}
	}
	
	private void applyBiomeSettings(BiomeType type,BiomeGenerationSettings settings) {
		if(generatorDefaults.clearBiomePopulators(type)) {
			settings.getPopulators().clear();
		}
		if(generatorDefaults.clearBiomeGeneratorPopulators(type)) {
			settings.getGenerationPopulators().clear();
		}
		if(generatorDefaults.clearBiomeCoverLayers(type)) {
			settings.getGroundCoverLayers().clear();
		}
		if(generatorDefaults.clearBiomeOres(type)) {
			settings.getPopulators().removeIf(p -> p.getType() == PopulatorTypes.ORE);
		}
	}
	
	protected List<IGeoWorldPlugin> getGenerationPlugins() {
		List<IGeoWorldPlugin> pluginGenerators = new ArrayList<>();
		List<String> generators = globalDefaults.getGeneratorIDs();
		List<String> order = generatorDefaults.getIDOrder();
		for(String generatorOrder : order) {
			for(String generator : generators) {
				if(generatorOrder.equalsIgnoreCase(generator)) {
					pluginGenerators.add(pluginConnections.get(generatorOrder));
					break;
				}
			}
		}
		return pluginGenerators;
	}
	
	protected IGeoWorldRockTransformer getTransformer() {
		return pluginConnections.getRockTransformer();
	}

}

