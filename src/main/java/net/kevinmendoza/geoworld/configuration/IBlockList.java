package net.kevinmendoza.geoworld.configuration;

import java.util.List;
import java.util.Set;

import net.kevinmendoza.geoworld.configuration.blocks.BlockDefault;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

public interface IBlockList {

	List<IBlockDefault> getBlockList();

}
