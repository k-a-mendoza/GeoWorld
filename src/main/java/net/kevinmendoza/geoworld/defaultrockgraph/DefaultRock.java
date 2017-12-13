package net.kevinmendoza.geoworld.defaultrockgraph;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

public class DefaultRock implements IRock {

	private static final String NAME = "STONE";
	private static final String TEX = "STONE";
	
	public IRock applyAlteration(AbstractAlteration alteration) { return this; }
	public IRock applyAlteration(AbstractAlteration alteration, IRock baseRock,
			int recursion) { return this; }
	
	public String getTexture(int variant) { return TEX; }
	public String getName() { return NAME; }

}
