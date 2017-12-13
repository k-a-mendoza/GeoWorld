package net.kevinmendoza.geoworld.spongehooks.blockconversion;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.block.ImmutableSandData;
import org.spongepowered.api.data.manipulator.immutable.block.ImmutableStoneData;
import org.spongepowered.api.data.manipulator.mutable.block.SandData;
import org.spongepowered.api.data.manipulator.mutable.block.StoneData;
import org.spongepowered.api.data.type.SandTypes;
import org.spongepowered.api.data.type.StoneTypes;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.configuration.IBlockList;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

class DefaultBlockStateCreator implements IBlockStateCreator,IBlockStateIdentifier {
	
	private HashMap<String,BlockState> stateConverter;

	public DefaultBlockStateCreator(){
		BlockState sand = BlockTypes.SAND.getDefaultState();
		BlockState stone = BlockTypes.STONE.getDefaultState();
		BlockState clay = BlockTypes.CLAY.getDefaultState();
		stateConverter= new HashMap<>();
		stateConverter.put("STONE",stone.copy());
		
		Optional<ImmutableSandData> sandStateData = sand.get(ImmutableSandData.class);
		SandData redSand = sandStateData.get().asMutable().set(Keys.SAND_TYPE,SandTypes.RED);
		stateConverter.put("SAND",sand.copy());
		stateConverter.put("RED_SAND",sand.copy().with(redSand.asImmutable()).get());
		
		stateConverter.put("SANDSTONE",BlockTypes.SANDSTONE.getDefaultState());
		stateConverter.put("RED_SANDSTONE",BlockTypes.RED_SANDSTONE.getDefaultState());
		
		stateConverter.put("COBBLESTONE",BlockTypes.COBBLESTONE.getDefaultState());
		stateConverter.put("GREEN_COBBLESTONE",BlockTypes.MOSSY_COBBLESTONE.getDefaultState());
		
		stateConverter.put("CLAY",clay.copy());
		stateConverter.put("HARDENED_CLAY",BlockTypes.HARDENED_CLAY.getDefaultState());
		
		stateConverter.put("GRAVEL",BlockTypes.GRAVEL.getDefaultState());
		stateConverter.put("DIRT",BlockTypes.DIRT.getDefaultState());
		
		Optional<ImmutableStoneData> stoneStateData = stone.get(ImmutableStoneData.class);
		StoneData granite = stoneStateData.get().asMutable().set(Keys.STONE_TYPE,StoneTypes.GRANITE);
		StoneData diorite = stoneStateData.get().asMutable().set(Keys.STONE_TYPE,StoneTypes.DIORITE);
		StoneData andesite = stoneStateData.get().asMutable().set(Keys.STONE_TYPE,StoneTypes.ANDESITE);
		stateConverter.put("GRANITE",stone.copy().with(granite.asImmutable()).get());
		stateConverter.put("ANDESITE",stone.copy().with(andesite.asImmutable()).get());
		stateConverter.put("DIORITE",stone.copy().with(diorite.asImmutable()).get());
		
		stateConverter.put("AIR",BlockTypes.AIR.getDefaultState());
		stateConverter.put("WATER",BlockTypes.WATER.getDefaultState());
		stateConverter.put("LAVA",BlockTypes.LAVA.getDefaultState());
	}
	
	@Override
	public BlockState createState(String texture) {
		if(stateConverter.containsKey(texture))
			return stateConverter.get(texture);
		return stateConverter.get("STONE");
	}

	@Override
	public boolean isEarthState(BlockState state) {
		BlockType type = state.getType();
		String name = type.getName().toLowerCase();
		if(name.contains("air") || name.contains("water"))
			return false;
		else if(name.contains("stone") || name.contains("dirt") || name.contains("gravel")) {
			return true;
		}
		else
			return false;
	}
	
}
