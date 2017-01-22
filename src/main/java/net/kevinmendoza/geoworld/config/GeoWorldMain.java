
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
package net.kevinmendoza.geoworld.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetypes;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.geoworld.spongehooks.OverWorldModifier;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(
id=GeoWorldMain.ID,
name=GeoWorldMain.NAME,
version=GeoWorldMain.VERSION,
url="http://www.kevinmendoza.net/geoworld-a-minecraft-geology-addon/",
authors = {"El_Minadero"},
description = "A Geologic Minecraft Mod")
public class GeoWorldMain extends Module {

	public static final String ID = "geoworld";
	public static final String NAME = "GeoWorld";
	public static final String VERSION = "1.0.2a";
	public static final String[] GEOWORLD_IDS = {"igneouspack"};
	
	private OverWorldModifier modifier;
	private WorldArchetype worldArchetype;

	@Inject
	public GeoWorldMain(Logger logger, @DefaultConfig(sharedRoot = true) File config, 
			@DefaultConfig(sharedRoot = true)ConfigurationLoader<CommentedConfigurationNode> configLoader,
			PluginContainer container) {
		super(logger, config,configLoader, container);
		defaults.onLoad(VERSION);
		module = this;
	}
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) {
		module 	 = this;
	}
	
	@Listener
	public void onGameStartingServerEvent(GameStartingServerEvent event) {
		try {
			final WorldProperties properties = Sponge.getServer().createWorldProperties("geoworld", worldArchetype);
			Sponge.getServer().loadWorld(properties);
		} catch (IOException ex) {
		}
	}
	
	@Listener
	public void onGameInitialization(GameInitializationEvent event) {
		List<GeologicContainer> geologyContainers = new ArrayList<>();
		for(String ids: GEOWORLD_IDS) {
			Optional<PluginContainer> pluginOptional = Sponge.getPluginManager().getPlugin(ids);
			if(pluginOptional.isPresent()) {
				PluginContainer container = pluginOptional.get();
				GeoWorldPluginInterface geoworldModule = (GeoWorldPluginInterface)container.getInstance().get();
				geologyContainers.addAll(geoworldModule.getGeologicContainers());
			}
		}
		modifier = new OverWorldModifier(geologyContainers,getDebugger());
		Sponge.getRegistry().register(WorldGeneratorModifier.class , modifier);
		worldArchetype = WorldArchetype.builder()
				.from(WorldArchetypes.OVERWORLD)
				.generatorModifiers(modifier)
				.build("geoworld", "Geo World");
	}

	@Override
	public List<GeologicContainer> getGeologicContainers() {
		return null;
	}
	
	
}