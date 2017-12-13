package net.kevinmendoza.geoworld.spongehooks.blockconversion;

import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworld.configuration.ConfigBind;
import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworldlibrary.utilities.IDebug;

public class BlockStateCreatorAccess {
	private static DefaultBlockStateCreator blockStateCreator;
	private static IDebug debugger;
	private static Injector injector;

	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	
	public static IBlockStateCreator GetBlockStateCreator() {
		if(blockStateCreator==null) {
			blockStateCreator = GetInjector().getInstance(DefaultBlockStateCreator.class);
		}
		return blockStateCreator;
	}
	public static IBlockStateIdentifier GetBlockStateIdentifier() {
		if(blockStateCreator==null) {
			blockStateCreator = GetInjector().getInstance(DefaultBlockStateCreator.class);
		}
		return blockStateCreator;
	}
	

}
