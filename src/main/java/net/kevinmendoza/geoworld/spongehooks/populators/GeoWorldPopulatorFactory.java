package net.kevinmendoza.geoworld.spongehooks.populators;

import java.util.HashMap;

import org.spongepowered.api.world.gen.GenerationPopulator;

import net.kevinmendoza.geoworld.spongehooks.blockconversion.BlockStateCreatorAccess;
import net.kevinmendoza.geoworld.spongehooks.blockconversion.IBlockStateCreator;
import net.kevinmendoza.geoworld.spongehooks.populators.GeoWorldPopulator.GeoWorldPopulatorBuilder;
import net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators.BlockStateCalculatorFactory;
import net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators.IBlockStateCalculator;
import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.GeologySimulatorFactory;
import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.IGeologySimulator;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurfaceExtractor;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.SurfaceExtractorFactory;
import net.kevinmendoza.geoworld.spongehooks.populators.surfacemap.ISurfaceMap;
import net.kevinmendoza.geoworld.spongehooks.populators.surfacemap.SurfaceMapFactory;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeoWorldPopulatorFactory {

	
	public static GeoWorldPopulator getPopulator(
			GenerationPopulator populator, HashMap<String, GeoWorldPlugin> plugins, long seed) {
		
		ISurfaceMap map = SurfaceMapFactory.createMap();
		IGeologySimulator simulator = GeologySimulatorFactory.getSimulator(plugins,seed);
		IBlockStateCreator stateCreator = BlockStateCreatorAccess.GetBlockStateCreator();
		IBlockStateCalculator calculator = BlockStateCalculatorFactory.createStateCalculator(stateCreator);
		ISurfaceExtractor extractor = SurfaceExtractorFactory.createSurfaceExtractor(plugins,seed);

		GeoWorldPopulatorBuilder builder = new GeoWorldPopulatorBuilder()
				.setGenerationPopulator(populator)
				.setGeologySimulator(simulator)
				.setBlockStateCalculator(calculator)
				.setSurfaceExtractor(extractor)
				.setSurfaceMap(map);

		return builder.build();
	}
}
