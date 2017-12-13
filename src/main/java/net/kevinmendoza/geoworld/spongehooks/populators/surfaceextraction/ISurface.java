package net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface ISurface {

	int getSurface(Vector2i query);

	int getSurface(int x, int z);

}
