package net.kevinmendoza.geoworld.spongehooks.blockconversion;

import java.util.Set;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;

import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

public interface IBlockStateCreator {

	BlockState createState(String string);

}
