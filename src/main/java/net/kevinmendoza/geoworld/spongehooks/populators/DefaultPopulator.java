package net.kevinmendoza.geoworld.spongehooks.populators;

import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class DefaultPopulator extends AbstractGeologyPopulator  {
	
	public DefaultPopulator(GenerationPopulator defaultBasePopulator) {
		super(defaultBasePopulator);
	}
	
	protected void setSurfaceBlockFill(MutableBlockVolume buffer, Vector3i vec,ImmutableBiomeVolume biomes) {
		setSand(buffer,vec);
	}
	
	protected void setDeepBlockFill(MutableBlockVolume buffer, Vector3i vec,ImmutableBiomeVolume biomes) {
		setSandStone(buffer,vec);
	}
	
	protected int setTargetSurface(MutableBlockVolume buffer, ImmutableBiomeVolume biomes, Vector2i vec,int[][] surface) {
		if(surface[vec.getX()][vec.getY()] > 70) {
			for(int y = surface[vec.getX()][vec.getY()]; y>70;y--)
				setAir(buffer,new Vector3i(vec.getX(),y,vec.getY()));
		}
		else {
			for(int y = surface[vec.getX()][vec.getY()]; y<=70;y++)
				setStone(buffer,new Vector3i(vec.getX(),y,vec.getY()));
		}
		return 70;
	}

}
