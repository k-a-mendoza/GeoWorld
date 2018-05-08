package net.kevinmendoza.geoworld.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.biome.BiomeTypes;

import com.google.common.reflect.TypeToken;
import com.google.inject.PrivateBinder;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class GeneratorDefaults implements IGeneratorDefaults {
	static final TypeToken<GeneratorDefaults> type = TypeToken.of(GeneratorDefaults.class);
	
	private String[] worldGeneratorSettingKeys = {
			"Clear_Generation_Populators", "Clear_Populators", "Clear_Generation_Ores"
		};
	
	private Collection<BiomeType> biomeTypes;
	
	@Setting
	private List<String> geoworldGeneratorOrder;
	@Setting
	private HashMap<String, Boolean> worldGenerationSettings;
	@Setting
	private HashMap<String, Boolean> biomeCoverLayerSettings;
	@Setting
	private HashMap<String, Boolean> biomeGeneratorPopulatorSettings;
	@Setting
	private HashMap<String, Boolean> biomePopulatorSettings;
	@Setting
	private HashMap<String, Boolean> biomeOreSettings;

	
	public GeneratorDefaults() {
		biomeTypes = Sponge.getGame().getRegistry().getAllOf(BiomeType.class);
		initGeneratorOrder();
		initWorldGenerationSettings();
		initBiomeCoverLayerSettings();
		initBiomeGeneratorPopulatorSettings();
		initBiomePopulatorSettings();
		initBiomeOreSettings();
	}
	
	
	private void initGeneratorOrder() {
		geoworldGeneratorOrder = new ArrayList<>();
		geoworldGeneratorOrder.add("sedimentarysequences");
		geoworldGeneratorOrder.add("igneouspack");
	}


	private void initBiomeOreSettings() {
		biomeOreSettings = new HashMap<>();
		for(BiomeType biomeType : biomeTypes) {
			biomeOreSettings.put(biomeType.getName(), true);
		}
	}
	private void initBiomePopulatorSettings() {
		biomePopulatorSettings = new HashMap<>();
		for(BiomeType biomeType : biomeTypes) {
			biomePopulatorSettings.put(biomeType.getName(), true);
		}
	}
	private void initBiomeGeneratorPopulatorSettings() {
		biomeGeneratorPopulatorSettings = new HashMap<>();
		for(BiomeType biomeType : biomeTypes) {
			biomeGeneratorPopulatorSettings.put(biomeType.getName(), true);
		}
	}
	private void initBiomeCoverLayerSettings() {
		biomeCoverLayerSettings = new HashMap<>();
		for(BiomeType biomeType : biomeTypes) {
			biomeCoverLayerSettings.put(biomeType.getName(), true);
		}
	}

	private void initWorldGenerationSettings() {
		worldGenerationSettings = new HashMap<>();
		for(String setting : worldGeneratorSettingKeys) {
			worldGenerationSettings.put(setting, true);
		}
	}
	
	public List<String> getIDOrder() 
					{ return geoworldGeneratorOrder; }
	public boolean clearBiomeCoverLayers(BiomeType type) 
					{ return biomeCoverLayerSettings.get(type.getName());}
	public boolean clearBiomeGeneratorPopulators(BiomeType type) 
					{ return biomeGeneratorPopulatorSettings.get(type.getName());}
	public boolean clearBiomePopulators(BiomeType type) 
					{ return biomePopulatorSettings.get(type.getName());}
	public boolean clearBiomeOres(BiomeType type) 
					{ return biomeOreSettings.get(type.getName());}
	
	public boolean clearGenerationPopulators() 
					{ return worldGenerationSettings.get(worldGeneratorSettingKeys[0]);}
	public boolean clearPopulators() 
					{ return worldGenerationSettings.get(worldGeneratorSettingKeys[1]);}
	public boolean clearGenerationOres() 
					{ return worldGenerationSettings.get(worldGeneratorSettingKeys[2]);}


	
	
	

}
