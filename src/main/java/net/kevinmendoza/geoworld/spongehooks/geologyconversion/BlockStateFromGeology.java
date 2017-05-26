package net.kevinmendoza.geoworld.spongehooks.geologyconversion;

import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworld.configuration.ConfigBind;
import net.kevinmendoza.geoworldlibrary.utilities.IDebug;

public final class BlockStateFromGeology {
	
	private HashSet<BlockContainer> blockContainerIndex;
	
	private static IDebug debugger;
	private static Injector injector;

	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}

}
