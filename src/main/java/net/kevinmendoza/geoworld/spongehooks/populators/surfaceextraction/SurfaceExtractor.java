package net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.spongehooks.blockconversion.IBlockStateIdentifier;
import net.kevinmendoza.geoworld.spongehooks.populators.AbstractGeologySimulator;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

class SurfaceExtractor extends AbstractGeologySimulator implements ISurfaceExtractor {

	private final List<String> extremeBiomeKeys;
	private final List<IGeology> geologyList;
	private final IBlockStateIdentifier identifier;

	private SurfaceExtractor(SurfaceExtractorBuilder builder) {
		identifier = builder.getIdentifier();
		geologyList = setUpGeologyList(builder.getGeologyPlugins(),builder.getSeed());
		extremeBiomeKeys = new ArrayList<String>();
		extremeBiomeKeys.add("HILLS");extremeBiomeKeys.add("MOUNTAINS");extremeBiomeKeys.add("EXTREME");
		extremeBiomeKeys.add("FOREST");extremeBiomeKeys.add("MESA");
	}
	
	@Override
	public ISurface getSurfaceObject(MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) {
		int[][] surface = extractSurface(buffer,biomes);
		surface = applyGeologyToSurface(surface, buffer,biomes);
		return SurfaceExtractorFactory.createISurface(surface);
	}
	
	private int[][] applyGeologyToSurface(int[][] surface, MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) { return surface; }

	private int[][] extractSurface(MutableBlockVolume buffer,ImmutableBiomeVolume biomes) {
		int[][] surface = new int[16][16];
		boolean conductBinarySurfaceSearch = shouldExtractSurfaceWithBinarySearch(biomes);
		for(int x = 0;x<16;x++) {
			for(int z = 0;z<16;z++) {
				if(conductBinarySurfaceSearch)
					surface[x][z]=binarySurfaceSearch(buffer,x,z);
				else
					surface[x][z]=linearSurfaceSearch(buffer,x,z);
			}
		}
		return surface;
	}
	
	private boolean shouldExtractSurfaceWithBinarySearch(ImmutableBiomeVolume biomes) {
		String currentBiomeID = biomes.getBiome(0,0,0).getName().toUpperCase();
		for(String biomeKey : extremeBiomeKeys) {
			if(currentBiomeID.contains(biomeKey))
				return false;//flip these
		}
		return true;
	}
	
	private int linearSurfaceSearch(MutableBlockVolume buffer, int x, int z) {
		int y = 0;
		for(int i = 255; i>0;i--) {
			if(isEarth(buffer,new Vector3i(x,i,z))) {
				y=i;
				break;
			}
		}
		return y;
	}
	
	private int binarySurfaceSearch(MutableBlockVolume buffer,int x, int z) {
		int a=255,b=0,mid=a/2;
		while(a-b>2) {
			Vector3i vec = new Vector3i(x,mid,z);
			if(!isEarth(buffer,vec)) {
				a = mid;
				mid = (a+b)/2;
			}
			else {
				b = mid;
				mid = (a+b)/2;
			}
		}
		return mid;
	}
	
	private boolean isEarth(MutableBlockVolume buffer, Vector3i vector3i) {
		return identifier.isEarthState(buffer.getBlock(vector3i));
	}
	
	static class SurfaceExtractorBuilder {
		
		private HashMap<String,GeoWorldPlugin> geoList;
		private IBlockStateIdentifier identifier;
		private long seed;
		
		private HashMap<String, GeoWorldPlugin> getGeologyPlugins() { return geoList; }
		private long getSeed() { return seed; }
		private IBlockStateIdentifier getIdentifier() { return identifier; }
		
		public SurfaceExtractorBuilder setPlugins(HashMap<String, GeoWorldPlugin> plugins) 
		{ this.geoList = plugins; return this; }
		public SurfaceExtractorBuilder setSeed(long seed) 
		{ this.seed = seed; return this; }
		public SurfaceExtractorBuilder setBlockStateIdentifier(IBlockStateIdentifier identifier)
		{ this.identifier = identifier; return this; }
		
		public ISurfaceExtractor build() {
			return new SurfaceExtractor(this);
		}
	}

}
