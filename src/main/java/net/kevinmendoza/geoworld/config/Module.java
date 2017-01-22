package net.kevinmendoza.geoworld.config;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetypes;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.config.Debug;
import net.kevinmendoza.geoworld.config.GeoWorldPluginInterface;
import net.kevinmendoza.geoworld.config.MainDefaults;
import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.geoworld.spongehooks.OverWorldModifier;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

abstract class Module implements GeoWorldPluginInterface {

	protected static GeoWorldPluginInterface module;
	private Debug debug;
	protected MainDefaults defaults;
	public PluginContainer container;
	
	public Module(Logger logger, File config,ConfigurationLoader<CommentedConfigurationNode> configLoader,
			PluginContainer container) {
		this.container = container;
		debug    = new Debug(logger);
		defaults = new MainDefaults(configLoader,config,"geoworld.conf",debug);
	}
	
	public static GeoWorldPluginInterface GetStaticInstance() 	 { return module;       	  }
	public GeoWorldPluginInterface GetInstance() 	 { return module;       	  }
	public ConfigurationNode getConfig()    		 { return defaults.getNode(); }
	public Debug getDebugger() 				 		 { return debug;              }
	
}
