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
import java.net.URL;

import org.spongepowered.api.config.ConfigManager;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class MainDefaults {

	private ConfigurationNode confNode;
	private ConfigurationLoader<CommentedConfigurationNode> manager;
	private File configFile;
	private String resourceName;
	private Debug debug;

	public MainDefaults(ConfigurationLoader<CommentedConfigurationNode> manager,
			File configFile,
			String resourceName,
			Debug debug) {
		
		this.debug = debug;
		this.manager = manager;
		this.configFile = configFile;
		this.resourceName = resourceName;
	}
	
	public ConfigurationNode getNode() { return confNode; }

	public void onLoad(String version) {
		if (configFile != null
				&& configFile.exists()) {
			ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader
					.builder()
					.setFile(configFile).build();
			try {
				ConfigurationNode editedNode = loader.load();
				String configVersion = editedNode.getNode("ConfigVersion")
						.getString("0");
				if (configVersion != version) {
					debug.warn(
							"conf file version number does not match GeoWorld Version. Will attempt to merge");
					merge(editedNode);
				} else {
					confNode = editedNode;
				}
			} catch (IOException e) {
				debug.warn(
						"Could not load edited Config file. Will create one from Plugin");
				makeDefaultConfig();
			}
		} else {
			makeDefaultConfig();
		}

	}
	
	private void makeDefaultConfig() {
		try {

			URL inputStream = this.getClass().getClassLoader()
					.getResource(resourceName);
			HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
					.setURL(inputStream).build();
			confNode = loader.load();
			debug.warn(
					"Please Restart Server and Change \'use mod\' to true to enable GeoWorld plugin");
			this.manager.save(confNode);
		} catch (IOException e) {
			debug.warn(
					"Could not make Default config file. Is plugin corrupted?"
							+ e);
		}
	}

	private void merge(ConfigurationNode editedNode) {
		// TODO Auto-generated method stub
		switch (editedNode.getNode("version").getInt()) {
		case 1: {
			debug.log("how do you have a pre-alpha release???");
		}
		case 2: {
			debug.log(
					"this shouldn't happen. You might just have to go prospecting IRL");
		}
			/// THIS ISNT DONE YET!!!
		}
		try {
			manager.save(editedNode);
		} catch (IOException e) {
			debug.warn("couldn't save merged to file.");
		}
	}
}
