package net.kevinmendoza.geoworld.spongehooks.generators;

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

import net.kevinmendoza.geoworld.configuration.IGeneratorDefaults;
import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeoWorldGeneratorModifier  {

	@Inject
	IGeneratorDefaults generatorDefaults;

	private List<PluginContainer> geologyContainers;

	public GeoWorldGeneratorModifier() {
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
		if(geologyContainers==null)
			this.geologyContainers = GeoWorldMain.PluginMain.getValidPluginContainers();
		List<IGeology> geologyList = new ArrayList<>();
		for(PluginContainer container : geologyContainers) {
			GeoWorldPlugin geoWorldPlugin = (GeoWorldPlugin)container;
			geologyList.add(geoWorldPlugin.getGeology(seed));
		}
		return geologyList;
	}

}

