package net.kevinmendoza.geoworld.spongehooks;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector3i;

public class GeoWorldPopulator implements GenerationPopulator {

	private GenerationPopulator defaultPopulator;
	
	public GeoWorldPopulator(GenerationPopulator defaultBasePopulator) {
		this.defaultPopulator = defaultBasePopulator;
	}

	@Override
	public void populate(World world, MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) {
		defaultPopulator.populate(world, buffer, biomes);
	/*
	 * step 1: extract surface
	 */
		int[][] surf = extractSurface(buffer);
	/*
	 * step 2: modify surface
	 */
		
	/*
	 * step 3: enter surface fill
	 */
	
	/*
	 * step 4: enter deep fill
	 */
	}

	private int[][] extractSurface(MutableBlockVolume buffer) {
		int[][] surface = new int[16][16];
		BlockType block = BlockTypes.AIR;
		for(int x = 0;x<16;x++) {
			for(int z = 0;z<16;z++) {
				for(int y = 255; y>0;y--) {
					if(isSurface(buffer,new Vector3i(x,y,z))) {
						surface[x][z] = y+1;
						break;
					}
				}
			}
		}
		
		return surface;
	}

	private boolean isSurface(MutableBlockVolume buffer, Vector3i vector3i) {
		return false;
	}

}
