package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworld.configuration.ConfigBind;
import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

public class RockGraphAccess {
 
	private static RockGraph rockGraph;
	private static Injector injector;
	
	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	
	static List<IRock> getRockList(){
		ensureRockGraphExists();
		return rockGraph.getRockList();
	}
	
	static IRock getRock(String key) {
		ensureRockGraphExists();
		return rockGraph.getRock(key);
	}
	
	private static void ensureRockGraphExists() {
		if(rockGraph==null) {
			rockGraph = GetInjector().getInstance(RockGraph.class);
			rockGraph.populateRockMap();
		}
	}
	
	public static String getFinalTexture(String rock, AbstractAlteration alteration,int variant) {
		return getRock(rock).applyAlteration(alteration).getTexture(variant);
	}
}
