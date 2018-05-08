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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;

class OverWorldModifier extends GeoWorldGeneratorModifier implements WorldGeneratorModifier {

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
		/*
		 * 
		 * 
		 * 
		 */
		//GeoWorldMain.PluginMain.getLog().info("modififying world generator");
		//removeDefaultOres(worldGenerator);
		//GeoWorldMain.PluginMain.getLog().info("modifying base populator");
		GenerationPopulator defaultBasePopulator = worldGenerator.getBaseGenerationPopulator();
		try {
			Method[] methods = defaultBasePopulator.getClass().getMethods();
			Field[] fields   = defaultBasePopulator.getClass().getFields();
			for(Method method : methods){
			    System.out.println("method = " + method.getName());
			}
			for(Field field : fields){
			    System.out.println("fields = " + field.getName());
			}
         }
         catch (Throwable e) {
            System.err.println(e);
         }
		//List<IGeoWorldPlugin> plugins		 = getGenerationPlugins();
		//IGeoWorldRockTransformer transformer = getTransformer();
		//GeoWorldMain.PluginMain.getLog().info("got plugins");
		/**GeoWorldPopulator pop = GeoWorldPopulatorFactory.getPopulator(
				defaultBasePopulator, plugins, transformer, world.getSeed());
		worldGenerator.setBaseGenerationPopulator(pop);
		**/
		
	}
	
	
}
