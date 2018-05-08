package net.kevinmendoza.geoworld.main;

import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldPlugin;
import net.kevinmendoza.geoworldlibrary.utilities.IGeoWorldRockTransformer;

public interface IPluginConnections {

	IGeoWorldPlugin get(String generatorOrder);

	IGeoWorldRockTransformer getRockTransformer();

}
