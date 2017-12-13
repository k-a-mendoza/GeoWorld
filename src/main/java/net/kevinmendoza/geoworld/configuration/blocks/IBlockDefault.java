package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;

public interface IBlockDefault {

	public String getName();

	public ITextureDefault getTextures();

	public IBehaviorDefaults getBehaviorDefaults(AlterationType type);

}
