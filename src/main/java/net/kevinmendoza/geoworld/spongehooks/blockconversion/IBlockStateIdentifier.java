package net.kevinmendoza.geoworld.spongehooks.blockconversion;

import org.spongepowered.api.block.BlockState;

public interface IBlockStateIdentifier {

	boolean isEarthState(BlockState state);
}
