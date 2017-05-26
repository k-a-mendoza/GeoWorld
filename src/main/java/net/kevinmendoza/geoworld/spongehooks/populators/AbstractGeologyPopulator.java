package net.kevinmendoza.geoworld.spongehooks.populators;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.main.GeoWorldMain;

abstract class AbstractGeologyPopulator implements GenerationPopulator  {
	
	private final List<BlockType> airBlocks;
	private Cause cause;
	private final GenerationPopulator defaultPopulator;
	
	AbstractGeologyPopulator(GenerationPopulator populator) {
		defaultPopulator = populator;
		cause = Cause.source(GeoWorldMain.PluginMain.GetPluginContainer()).build();
		airBlocks = new ArrayList<>();
		airBlocks.add(BlockTypes.WATER);airBlocks.add(BlockTypes.FLOWING_WATER);
		airBlocks.add(BlockTypes.LAVA);airBlocks.add(BlockTypes.FLOWING_LAVA);
		airBlocks.add(BlockTypes.AIR);
	}
	protected boolean isAir(MutableBlockVolume buffer, Vector3i vector3i) {
		BlockState state = buffer.getRelativeBlockView().getBlock(vector3i);
		return airBlocks.contains(state.getType());
	}
	protected void setStone(MutableBlockVolume buffer, Vector3i vector3i) {
		buffer.getRelativeBlockView().setBlockType(vector3i, BlockTypes.STONE, cause);
	}

	protected void setAir(MutableBlockVolume buffer, Vector3i vector3i) {
		buffer.getRelativeBlockView().setBlockType(vector3i, BlockTypes.AIR, cause);
	}
	
	protected void setSand(MutableBlockVolume buffer, Vector3i vector3i) {
		buffer.getRelativeBlockView().setBlockType(vector3i, BlockTypes.SAND, cause);
	}
	
	protected void setSandStone(MutableBlockVolume buffer, Vector3i vector3i) {
		buffer.getRelativeBlockView().setBlockType(vector3i, BlockTypes.SANDSTONE, cause);
	}
	@Override
	public final void populate(World world, MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) {
		//Acquire data from Server
		GeoWorldMain.PluginMain.getLog().debug("populating");
		defaultPopulator.populate(world, buffer, biomes);
		
		//Finalize target Data
		int[][] defaultSurface = extractSurface(buffer);
		int[][]	targetSurface  = targetSurface(buffer,biomes,defaultSurface);
		
		//Insert Target Data
		fillSurface(buffer,biomes,targetSurface);
		deepFill(buffer,biomes,targetSurface);
	}
	
	protected abstract int setTargetSurface(MutableBlockVolume buffer, 
			ImmutableBiomeVolume biomes, Vector2i vec,int[][] surface);
	
	protected abstract void setSurfaceBlockFill(MutableBlockVolume buffer, 
			Vector3i vec,ImmutableBiomeVolume biomes);
	
	protected abstract void setDeepBlockFill(MutableBlockVolume buffer, 
			Vector3i vec,ImmutableBiomeVolume biomes);
	
	private void fillSurface(MutableBlockVolume buffer,ImmutableBiomeVolume biomes, int[][] surface) {
		for(int x = 0;x<16;x++)
			for(int z = 0;z<16;z++)
				for(int y = surface[x][z]; y>60;y--)
					setSurfaceBlockFill(buffer,new Vector3i(x,y,z),biomes);
	}
	
	
	private void deepFill(MutableBlockVolume buffer,ImmutableBiomeVolume biomes, int[][] surface) {
		for(int x = 0;x<16;x++)
			for(int z = 0;z<16;z++)
				for(int y = 1; y<20;y++)
					setDeepBlockFill(buffer,new Vector3i(x,y,z),biomes);
	}
			
	private int[][] targetSurface(MutableBlockVolume buffer,ImmutableBiomeVolume biomes,int[][] surface) {
		int[][] target = new int[16][16];
		for(int x = 0;x<16;x++) {
			for(int z = 0;z<16;z++) {
				Vector2i vec = new Vector2i(x,z);
				target[x][z] = setTargetSurface(buffer,biomes, vec, surface);
			}
		}
		return target;
	}

	private int[][] extractSurface(MutableBlockVolume buffer) {
		int[][] surface = new int[16][16];
		BlockType block = BlockTypes.AIR;
		for(int x = 0;x<16;x++) {
			for(int z = 0;z<16;z++) {
				for(int y = 255; y>0;y--) {
					if(!isAir(buffer,new Vector3i(x,y,z))) {
						surface[x][z] = y+1;
						break;
					}
				}
			}
		}
		return surface;
	}
}
