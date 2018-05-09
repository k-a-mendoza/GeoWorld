/*  GeoWorlds: a geology-based procedural terrain generation mod for Minecraft.
    Copyright (C) 2015 Kevin A. Mendoza. kevinmendoza@icloud.como

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.IBlockStateCreator;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldPlugin;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldRockTransformer;

public class OverWorldModifier extends GeoWorldGeneratorModifier implements WorldGeneratorModifier {

	@Override
	public String getId()   { return "geoworld"; }
	@Override
	public String getName() { return "geoworld"; }
	
	public OverWorldModifier(){
		super();
	}
	
	@Override
	public void modifyWorldGenerator(WorldProperties world,
			DataContainer settings, WorldGenerator worldGenerator) {
		removeVanillaBehaviors(worldGenerator);
		GenerationPopulator geoWorldBasePopulator = createGeoWorldPopulator(world,
										settings,worldGenerator);
		
		worldGenerator.setBaseGenerationPopulator(geoWorldBasePopulator);
	}
	
	private GenerationPopulator createGeoWorldPopulator(WorldProperties world, 
			DataContainer settings, WorldGenerator worldGenerator) {
		pluginConnections.connectPlugins();
		GenerationPopulator 	 defaultBasePopulator 
									= worldGenerator.getBaseGenerationPopulator();
		List<IGeoWorldPlugin> 	 plugins		 
									= getGenerationPlugins();
		IGeoWorldRockTransformer transformer 
									= getTransformer();
		
		IBlockStateCreator creator 	= getBlockCreator(world,transformer);
		List<IGeology>  geologies  	= getGeologyList(world,plugins);
		IRockVolumeExtractor map 		   	= getVolumeExtractor(settings,defaultBasePopulator);
		
		return new GeoWorldPopulator.Builder()
				.setBlockCreator(creator)
				.setGeologyList(geologies)
				.setVolumeExtractor(map).build();

	}
	
	private IRockVolumeExtractor getVolumeExtractor(DataContainer settings, GenerationPopulator defaultBasePopulator) {
		return new VolumeExtractor.Builder()
				.setWorldGenerator(defaultBasePopulator)
				.build();
	}
	
	private List<IGeology> getGeologyList(WorldProperties world,List<IGeoWorldPlugin> plugins) {
		List<IGeology> geologyList = new ArrayList<>();
		long seed = world.getSeed();
		for(IGeoWorldPlugin plugin : plugins) {
			geologyList.add(plugin.getGeology(seed, true));
		}
		return geologyList;
	}
	
	private IBlockStateCreator getBlockCreator(WorldProperties world,
			IGeoWorldRockTransformer transformer) {
		long seed = world.getSeed();
		return transformer.getBlockCreator(seed, false);
	}
	
}
