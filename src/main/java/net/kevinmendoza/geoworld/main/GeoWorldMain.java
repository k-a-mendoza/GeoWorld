
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
package net.kevinmendoza.geoworld.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.data.manipulator.mutable.entity.JoinData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetypes;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.gen.WorldGeneratorModifiers;
import org.spongepowered.api.world.storage.WorldProperties;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.configuration.GeoWorldConfiguration;
import net.kevinmendoza.geoworld.spongehooks.generators.GeneratorModifierAccess;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Plugin(
id=GeoWorldMain.ID,
name=GeoWorldMain.NAME,
version=GeoWorldMain.VERSION,
url="http://www.kevinmendoza.net/geoworld-a-minecraft-geology-addon/",
authors = {"El_Minadero"},
description = "A Geologic Minecraft Mod")
public class GeoWorldMain {

	public  static final String ID = "geoworld";
	public  static final String NAME = "GeoWorld";
	public  static final String VERSION = "1.0.2a";
	public  static final String[] GEOWORLD_IDS = {"igneouspack"};
	private static WorldArchetype GEOWORLD;
	public  static GeoWorldMain PluginMain;
	
	@Inject 
	@DefaultConfig(sharedRoot = true)
	ConfigurationLoader<CommentedConfigurationNode> loader;
	@Inject @DefaultConfig(sharedRoot = true) File config;
	@Inject 
	private PluginContainer container;
	@Inject 
	private Logger logger;
	private GeoWorldConfiguration defaults;
	private List<PluginContainer> validPluginContainers;
	
	public GeoWorldMain() {
		PluginMain = this;
	}
	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join event)  {
		Player player = event.getTargetEntity();
		if(player.get(JoinData.class).isPresent()) {
			World geoWorld = Sponge.getGame().getServer().getWorld("geoworld").get();
			player.setLocation(geoWorld.getSpawnLocation());
		}
		
	}
	
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) throws IOException, ObjectMappingException {
		createConfigs();
	}
	
	@Listener
	public void onGameStartingServerEvent(GameStartingServerEvent event) {
		try {
			GEOWORLD = WorldArchetype.builder()
	        		.from(WorldArchetypes.OVERWORLD)
	        		.generatorModifiers(GeneratorModifierAccess
	        				.GetWorldGeneratorModifier())
	                .build("geoworld", "GeoWorld");
			final WorldProperties properties = Sponge.getServer().createWorldProperties("geoworld",GEOWORLD);
			Sponge.getServer().loadWorld(properties);
		} catch (IOException ex) {
		}
	}
	
	@Listener
	public void onGameInitialization(GameInitializationEvent event) {
		validPluginContainers = new ArrayList<>();
		for(String ids: GEOWORLD_IDS) {
			Optional<PluginContainer> pluginOptional = Sponge.getPluginManager().getPlugin(ids);
			if(pluginOptional.isPresent()) {
				PluginContainer container = pluginOptional.get();
				validPluginContainers.add(container);
			}
		}
		
		if(!validPluginContainers.isEmpty()) {
		}
		Sponge.getRegistry().register(WorldGeneratorModifier.class , GeneratorModifierAccess.GetWorldGeneratorModifier());
	}
	
	@Listener
	public void onGameReload(GameReloadEvent event) throws IOException, ObjectMappingException {
		createConfigs();
	}

	private void createConfigs() throws IOException, ObjectMappingException {
		ConfigurationNode node = loader.createEmptyNode();
		node.setValue(GeoWorldConfiguration.type, defaults == null ? (defaults= new GeoWorldConfiguration()) : defaults);
		loader.save(node);
	}

	public Logger getLog() {
		return logger;
	}

	public List<PluginContainer> getValidPluginContainers() {
		return validPluginContainers;
	}
	
	public PluginContainer GetPluginContainer() {
		if(container==null)
			logger.info("isNull");
		else
			logger.debug("isNotNull");
		return container;
	}

}