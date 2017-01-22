package net.kevinmendoza.geoworld.geology;

import com.flowpowered.math.vector.Vector2i;

public interface GeologicContainer {

	public void setSeed(long seed);

	public LithogenicOrder getOrder();
	
	public void primeGenerationAt(Vector2i vector);

	public StratigraphicColumn getColumnAt(Vector2i min);
}
