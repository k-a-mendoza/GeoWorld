package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.extent.MutableBlockVolume;

class NonAirBlocks {

	private MutableBlockVolume relativeView;
	private final List<BlockType> nonRockBlocks;

	public NonAirBlocks(MutableBlockVolume buffer) {
		this.relativeView = buffer.getRelativeBlockView();
		nonRockBlocks = new ArrayList<>();
		nonRockBlocks.add(BlockTypes.AIR);
		nonRockBlocks.add(BlockTypes.WATER);
		nonRockBlocks.add(BlockTypes.LAVA);
		nonRockBlocks.add(BlockTypes.BEDROCK);
		nonRockBlocks.add(BlockTypes.LOG);
		nonRockBlocks.add(BlockTypes.LOG2);
		nonRockBlocks.add(BlockTypes.LEAVES);
		nonRockBlocks.add(BlockTypes.LEAVES2);
	}

	public boolean isValidPosition(int x, int y, int z) {
		BlockType type = relativeView.getBlockType(x, y, z);
		return !isContainedInList(type);
	}

	private boolean isContainedInList(BlockType type) {
		for(BlockType blockType : nonRockBlocks) {
			if(type.equals(blockType)) {
				return true;
			}
		}
		return false;
	}


}
