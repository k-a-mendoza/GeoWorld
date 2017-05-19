package net.kevinmendoza.geoworld.spongehooks;

import java.util.HashMap;

import org.slf4j.Logger;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworld.config.ConfigBind;
import net.kevinmendoza.geoworldlibrary.utilities.IDebug;

public final class GeneratorModifierAccess {

	private static OverWorldModifier overWorldModifier;
	private static IDebug debugger;
	private static Injector injector;

	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	
	public static WorldGeneratorModifier GetWorldGeneratorModifier() {
		if(overWorldModifier==null) {
			overWorldModifier = GetInjector().getInstance(OverWorldModifier.class);
		}
		return overWorldModifier;
	}

}
