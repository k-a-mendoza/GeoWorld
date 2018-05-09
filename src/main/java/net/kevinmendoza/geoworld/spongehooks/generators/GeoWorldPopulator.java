package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector3i;
import com.google.inject.PrivateBinder;

import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.utilities.IBlockStateCreator;

public class GeoWorldPopulator implements GenerationPopulator {

	private IRockVolumeExtractor 	volumeExtractor;
	private IBlockStateCreator 		blockStateCreator;
	private List<IGeology>     		geologyList;
	private Cause cause;
	
	public GeoWorldPopulator(Builder builder) {
		volumeExtractor = builder.getSurfaceMap();
		blockStateCreator = builder.getStateCreator();
		geologyList = builder.getGeologyList();
		cause = Cause.source( GeoWorldMain.GetPluginContainer() ).build();
	}


	@Override
	public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
		IRockVolume volume = volumeExtractor.getSurface(world,buffer,biomes);
		for(Vector3i vector3i : volume.volumeList()) {
			BlockState newState = setBlockState(vector3i);
			buffer.setBlock(vector3i, newState, cause);
		}
	}
	
	
	private BlockState setBlockState(Vector3i vector3i) {
		IData data = null;
		for(IGeology geology : geologyList) {
			if(data==null) {
				data = geology.getData(vector3i);
			}
			else {
				data.merge(geology.getData(vector3i));
			}
		}
		return blockStateCreator.getBlockState(data);
	}


	static class Builder {
		
		private IRockVolumeExtractor 	   surfaceMap;
		private IBlockStateCreator blockStateCreator;
		private List<IGeology>     geologyList;
		
		Builder setVolumeExtractor(IRockVolumeExtractor map) { surfaceMap=map; return this; }
		Builder setBlockCreator(IBlockStateCreator creator) { blockStateCreator=creator; return this; }
		Builder setGeologyList(List<IGeology> geologies) { geologyList=geologies; return this; }
		
		private List<IGeology> getGeologyList() { return geologyList; }
		private IBlockStateCreator getStateCreator() { return blockStateCreator; }
		private IRockVolumeExtractor getSurfaceMap() { return surfaceMap; }
		
		GenerationPopulator build() {
			return new GeoWorldPopulator(this);
		}
		
	}

}
