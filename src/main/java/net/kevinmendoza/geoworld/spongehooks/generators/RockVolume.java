package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.world.extent.MutableBlockVolume;

import com.flowpowered.math.vector.Vector3i;

class RockVolume implements IRockVolume {
	
	private MutableBlockVolume volume;
	private List<Vector3i> blocksToChange;
	
	 private RockVolume(Builder builder) {
		volume = builder.getVolume();
		calculateBlocksToChange();
	}

	private void calculateBlocksToChange() {
		Vector3i min = volume.getBlockMin();
		Vector3i max = volume.getBlockMax();
		
		NonAirBlocks nonAirBlocks = new NonAirBlocks(volume);
		
		Vector3i diff = max.sub(min);
		int x_max = diff.getX();
		int z_max = diff.getZ();
		int y_max = diff.getY();

		blocksToChange = createValidVector3iList(min, x_max, z_max, y_max, nonAirBlocks);
	}

	private List<Vector3i> createValidVector3iList(Vector3i min, int x_max, int z_max, int y_max,
													NonAirBlocks nonAirBlocks) {
		List<Vector3i> locations = new ArrayList<>();
		
		for(int x=0;x<x_max;x++) {
			for(int y=0;y<y_max;y++) {
				for(int z=0;z<z_max;z++) {
					 if(nonAirBlocks.isValidPosition(x,y,z)) {
						 locations.add(min.add(x,y,z));
					 }
				}
			}
		}
		return locations; 
	}
	
	public List<Vector3i> volumeList() { return blocksToChange; }
	
	public static class Builder {
		
		private MutableBlockVolume volume;
		
		private MutableBlockVolume getVolume() { return volume; }
		Builder setVolume(MutableBlockVolume buffer) { this.volume=buffer; return this;  }
		IRockVolume build() {
			return new RockVolume(this);
		}
	}

}
