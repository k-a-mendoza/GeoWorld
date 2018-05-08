package net.kevinmendoza.geoworld.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.configuration.IGlobalDefaults;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldPlugin;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldRockTransformer;

public class GeoWorldPluginConnections implements IPluginConnections {
	
	@Inject
	private IGlobalDefaults globalDefaults;
	
	private HashMap<String,IGeoWorldPlugin> geoWorldPlugins;
	private HashMap<String,IGeoWorldRockTransformer> rockTransformers;
	
	GeoWorldPluginConnections() {
		geoWorldPlugins 	= new HashMap<>();
		rockTransformers 	= new HashMap<>();
	}
	
	List<String> connectGeoWorldPluginSuite() {
		List<String> connected = new ArrayList<>();
		connected.addAll(connectGenerators());
		connected.addAll(connectTransformers());
		return connected;
	}
	
	private List<String> connectGenerators() {
		List<String> connected = new ArrayList<>();
		for(String id: globalDefaults.getGeneratorIDs()) {
			Optional<PluginContainer> pluginOptional = Sponge.getPluginManager().getPlugin(id);
			if(pluginOptional.isPresent()) {
				PluginContainer container = pluginOptional.get();
				Optional<?> pluginInstance = container.getInstance();
				Object o = pluginInstance.get();
				if(IGeoWorldPlugin.class.isAssignableFrom(o.getClass())) {
					IGeoWorldPlugin testPlugin = (IGeoWorldPlugin)o;
					connected.add(id);
					geoWorldPlugins.put(testPlugin.GetPluginID(), testPlugin);
				}
			}
		}
		return connected;
	}
	
	private List<String> connectTransformers() {
		List<String> connected = new ArrayList<>();
		for(String id: globalDefaults.getTransformerIDs()) {
			Optional<PluginContainer> pluginOptional = Sponge.getPluginManager().getPlugin(id);
			if(pluginOptional.isPresent()) {
				PluginContainer container = pluginOptional.get();
				Optional<?> pluginInstance = container.getInstance();
				Object o = pluginInstance.get();
				if(IGeoWorldRockTransformer.class.isAssignableFrom(o.getClass())) {
					IGeoWorldRockTransformer testPlugin = (IGeoWorldRockTransformer)o;
					connected.add(id);
					rockTransformers.put(testPlugin.GetPluginID(), testPlugin);
				}
			}
		}
		return connected;
	}

	@Override
	public IGeoWorldPlugin get(String generatorOrder) {
		return geoWorldPlugins.get(generatorOrder);
	}

	@Override
	public IGeoWorldRockTransformer getRockTransformer() {
		for(String key : rockTransformers.keySet()) {
			return rockTransformers.get(key);
		}
		return null;
	}
	
}
