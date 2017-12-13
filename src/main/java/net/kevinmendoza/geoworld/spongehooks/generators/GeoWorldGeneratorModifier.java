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
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeoWorldGeneratorModifier  {
	protected final boolean CLEAR_POPULATORS = true;
	private final boolean CLEAR_LAYERS = true;
	protected final boolean CLEAR_GENERATION_POPULATORS = false;
	@Inject
	IGeneratorDefaults generatorDefaults;
	@Inject
	IGlobalDefaults globalDefaults;

	public GeoWorldGeneratorModifier() {
	}
	
	protected void removeDefaultOres(WorldGenerator generator) {
		if(generatorDefaults.removeVanillaOres()) {
			debugLoop2(generator);
			List<Populator> populators = generator.getPopulators();
			populators.removeIf(p -> p.getType() == PopulatorTypes.ORE);
			BiomeGenerationSettings settings;
			for (BiomeType type : Sponge.getRegistry().getAllOf(BiomeType.class)) {
				settings = generator.getBiomeSettings(type);
				debugLoop(settings);
				settings.getPopulators().removeIf(p -> p.getType() == PopulatorTypes.ORE);
			}
		}
	}
	
	private void debugLoop2(WorldGenerator worldGenerator) {
		if(CLEAR_POPULATORS) {
			worldGenerator.getPopulators().clear();
		}
		if(CLEAR_LAYERS) {
			worldGenerator.getGenerationPopulators().clear();
		}
	}
	
	private void debugLoop(BiomeGenerationSettings settings) {
		if(CLEAR_POPULATORS) {
			settings.getPopulators().clear();
		}
		if(CLEAR_GENERATION_POPULATORS) {
			settings.getGenerationPopulators().clear();
		}
		if(CLEAR_LAYERS) {
			settings.getGroundCoverLayers().clear();
		}
	}
	
	protected HashMap<String, GeoWorldPlugin> getPlugins(){
		List<String> ids = globalDefaults.getPluginIDs();
		HashMap<String,GeoWorldPlugin> plugins = new HashMap<>();
		for(String id : ids) {
			if(!GeoWorldMain.PluginMain.pluginExists(id)) {
				GeoWorldMain.PluginMain.getLog().info("Could not find plugin " 
			    + id + " . GeoWorld will continue with existing plugins");
			}
			else {
				GeoWorldMain.PluginMain.getLog().info("Adding " 
					    + id + " to GeoWorld Simulation Loop");
				plugins.put(id,GeoWorldMain.PluginMain.getPlugin(id));
			}
		}
		return plugins;
	}

}

