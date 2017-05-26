package net.kevinmendoza.geoworld.spongehooks.populators;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class GeologyPopulator extends AbstractGeologyPopulator {

	private GenerationPopulator defaultPopulator;
	
	public GeologyPopulator(GenerationPopulator defaultBasePopulator) {
		super(defaultBasePopulator);
		this.defaultPopulator = defaultBasePopulator;
	}

	@Override
	protected int setTargetSurface(MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes, Vector2i vec, int[][] surface) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setSurfaceBlockFill(MutableBlockVolume buffer, Vector3i vec,
			ImmutableBiomeVolume biomes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDeepBlockFill(MutableBlockVolume buffer, Vector3i vec,
			ImmutableBiomeVolume biomes) {
		// TODO Auto-generated method stub
		
	}

	

}
