package net.kevinmendoza.geoworld.spongehooks.populators.blockstatecalculators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.MutableBlockVolume;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworld.configuration.IBlockList;
import net.kevinmendoza.geoworld.configuration.blocks.BlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworld.defaultrockgraph.RockGraphAccess;
import net.kevinmendoza.geoworld.main.GeoWorldMain;
import net.kevinmendoza.geoworld.spongehooks.blockconversion.BlockStateCreatorAccess;
import net.kevinmendoza.geoworld.spongehooks.blockconversion.IBlockStateCreator;
import net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation.IBlockMap;
import net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction.ISurface;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;

public class BlockStateCalculator implements IBlockStateCalculator {
	
	private IBlockStateCreator stateCreator;
	private final Cause cause;
	private final List<BlockType> validReplacementTypes;
	
	public BlockStateCalculator(){
		validReplacementTypes = new ArrayList<>();
		validReplacementTypes.add(BlockTypes.STONE);
		validReplacementTypes.add(BlockTypes.GRAVEL);
		validReplacementTypes.add(BlockTypes.SANDSTONE);
		validReplacementTypes.add(BlockTypes.RED_SANDSTONE);
		validReplacementTypes.add(BlockTypes.STAINED_HARDENED_CLAY);
		validReplacementTypes.add(BlockTypes.HARDENED_CLAY);
		cause = Cause.source(GeoWorldMain.PluginMain.GetPluginContainer()).build();
		stateCreator = BlockStateCreatorAccess.GetBlockStateCreator();
	}
	
	public BlockState getBlockState(AbstractRock rock, AbstractAlteration alteration) {
		if(rock.isNullRock() || rock.getPreEvaluatedRock().equalsIgnoreCase("")) {
			return stateCreator.createState("STONE");
		}
		else {
			String rockName = rock.getEvaluatedRock();
			int variant=0;
			if(rockName.contains(":"))  {
				String[] rockvals = rockName.split(":");
				variant = Integer.parseInt(rockvals[1]);
				rockName = rockvals[0];
			}
			String texture = RockGraphAccess.getFinalTexture(rockName, alteration,variant);
			return stateCreator.createState(texture);
		}
	}

	private BlockState getBlockState(String string) {
		return stateCreator.createState(string);
	}

	@Override
	public void applyMapToVolume(Vector3i vectorKey, ISurface surface,IBlockMap map, MutableBlockVolume volume, boolean debug) {
		if(debug) {
			if((vectorKey.getX()/16)%3==0 || (vectorKey.getZ()/16)%3==0) {
				setVolumeToAir(surface,volume);
			}
			else {
				setBlockStates(surface,map,volume);
			}
		}
		else {
			setBlockStates(surface,map,volume);
		}
	}

	private void setBlockStates(ISurface surface, IBlockMap map,
			MutableBlockVolume volume) {
		 MutableBlockVolume volume2 = volume.getRelativeBlockView();
			for(int x=0;x<16;x++) {
				for(int z=0;z<16;z++) {
					int yStart = surface.getSurface(x,z);
					for(int y=yStart;y>0;y--) {
						if(isReplacable(volume2.getBlock(x,y,z))){
							Vector3i key = new Vector3i(x,y,z);
							BlockState state = getBlockState(map.getRock(key),map.getAlteration(key));
							volume2.setBlock(key, state, cause);
						}
					}
				}
			}
	}

	private boolean isReplacable(BlockState block) {
		BlockType type = block.getType();
		return validReplacementTypes.contains(type);
	}

	private void setVolumeToAir(ISurface surface,MutableBlockVolume volume) {
		for(int x=0;x<16;x++) {
			for(int z=0;z<16;z++) {
				int yStart = surface.getSurface(x,z);
				for(int y=yStart;y>0;y--) {
					BlockState air = getBlockState("AIR");
					volume.setBlock(x,y,z, air, cause);
				}
			}
		}
	}

}
