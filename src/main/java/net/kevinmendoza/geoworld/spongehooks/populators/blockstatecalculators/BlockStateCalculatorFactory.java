package net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators;

import net.kevinmendoza.geoworld.spongehooks.blockconversion.IBlockStateCreator;

public class BlockStateCalculatorFactory {

	public static IBlockStateCalculator createStateCalculator(
			IBlockStateCreator stateCreator) {
		return new BlockStateCalculator();
	}

}
