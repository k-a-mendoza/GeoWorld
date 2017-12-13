package net.kevinmendoza.geoworld.spongehooks.populators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.PopulatorType;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public abstract class AbstractGeologySimulator {

	protected List<IGeology> setUpGeologyList(HashMap<String, GeoWorldPlugin> plugins,long seed) {
		List<IGeology>geology = new LinkedList<>();
		IGeology emptyGeology = new EmptyGeology();
		List<String> keySet = new ArrayList<>();
		keySet.addAll(plugins.keySet());
		for(String key : keySet) {
			geology.add(plugins.get(key).getGeology(seed));
		}
		geology =  insertSortByOrder(geology);
		geology.add(emptyGeology);
		return geology;
	}
	
	private List<IGeology> insertSortByOrder(List<IGeology> geology2) {
		List<IGeology> geology;
		if(geology2.size() >2) {
			int j;
			for(int i = 1; i< geology2.size();i++) {
				j = i;
				while(j >0 && 
						geology2.get(i-1).getOrder().ordinal() > geology2.get(j).getOrder().ordinal()) {
					IGeology one = geology2.get(j-1);
					IGeology two = geology2.get(j);
					geology2.set(j, one);
					geology2.set(j-1, two);
					j--;
				}
			}
			geology = geology2;
		}
		else {
			geology = geology2;
		}
		return geology;
	}

}
