package net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation;

import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.populators.AbstractGeologySimulator;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.PrimeData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

class GeologySimulator extends AbstractGeologySimulator implements IGeologySimulator {

	
	private List<IGeology> geologyList;
	private final int Y_CHECK = 0;
	private final ISingularGeologyData alteration;
	private final ISingularGeologyData rock;
	private GeologySimulator(GeologySimulatorBuilder builder) {
		geologyList = setUpGeologyList(builder.getPlugins(),builder.getSeed());
		alteration 	= EmptyDataFactory.getEmptyDataObject(2);
		rock		= EmptyDataFactory.getEmptyDataObject(3);
	}

	@Override
	public IBlockMap getBlockMap(Vector3i vectorKey, ISurface surface, ImmutableBiomeVolume immutableRelativeBiomeVolume) {
		int ystart; 
		BlockMap blockMap = GeologySimulatorFactory.getBlockMap();
		for(int x = 0;x<16;x++) {
			for(int z = 0;z<16;z++) {
				Vector2i chunkXZCoordinate = new Vector2i(x + vectorKey.getX(),z + vectorKey.getZ());
				ystart = surface.getSurface(x,z);
				primeObjects(chunkXZCoordinate
						,ystart
						,immutableRelativeBiomeVolume.getBiome(x, Y_CHECK, z));
				for(int y=ystart;y>0;y--) {
					Vector3i globalCoordinate = new Vector3i(
							chunkXZCoordinate.getX(),
							y,
							chunkXZCoordinate.getY());
					Vector3i relXYZCoordinate = new Vector3i(x,y,z);
					AbstractAlteration alt = (AbstractAlteration) getGeologyData(alteration,globalCoordinate);
					AbstractRock r = (AbstractRock) getGeologyData(rock,globalCoordinate);
					blockMap.put(relXYZCoordinate,alt,r);
				}
			}
		}
		return blockMap;
	}

	private void primeObjects(Vector2i query, int ystart, BiomeType biomeType) {
		PrimeData primeData = new PrimeData(query);
		primeData.setHeight(ystart);
		primeData.put("biomeData", biomeType);
		for(IGeology geology : geologyList) {
			geology.loadNearbyNodes(primeData);
			geology.primeLoadedObjects(primeData);
		}
	}

	private ISingularGeologyData getGeologyData(ISingularGeologyData dataType,
			Vector3i query) {
		ISingularGeologyData geoData=null;
		for(IGeology geology : geologyList) {
			if(geoData==null) {
				geoData = geology.getStartingData(dataType);
			}
			geoData.merge(geology.get3DGeologyData(dataType, query));
		}
		return geoData;
	}

	static class GeologySimulatorBuilder {

		private long seed;
		private HashMap<String, GeoWorldPlugin> plugins;

		public GeologySimulatorBuilder setSeed(long seed) 
		{ this.seed = seed; return this; }
		public GeologySimulatorBuilder setPlugins(HashMap<String, GeoWorldPlugin> plugins) 
		{ this.plugins=plugins; return this; }
		
		private HashMap<String, GeoWorldPlugin> getPlugins() { return plugins; }
		private long getSeed() { return seed; }
		
		public IGeologySimulator build() { return new GeologySimulator(this); }
		
	}

}
