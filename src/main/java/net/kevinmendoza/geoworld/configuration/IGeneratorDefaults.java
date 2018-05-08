package net.kevinmendoza.geoworld.configuration;

import java.util.List;

import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;

public interface IGeneratorDefaults {

	boolean clearBiomeCoverLayers(BiomeType type);

	boolean clearBiomeGeneratorPopulators(BiomeType type);

	boolean clearBiomePopulators(BiomeType type);
	
	boolean clearBiomeOres(BiomeType type);

	boolean clearGenerationPopulators();

	boolean clearPopulators();

	boolean clearGenerationOres();

	List<String> getIDOrder();
	
}
