package net.kevinmendoza.geoworld.defaultrockgraph;

import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

interface IRock {

	IRock applyAlteration(AbstractAlteration alteration);
	
	IRock applyAlteration(AbstractAlteration alteration,IRock baseRock,int recursion);

	String getTexture(int variant);

	String getName();

}
