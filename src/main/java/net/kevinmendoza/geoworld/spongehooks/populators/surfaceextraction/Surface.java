package net.kevinmendoza.geoworld.spongehooks.populators.surfaceextraction;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class Surface implements ISurface {

	private final int[][] surface;
	
	public Surface(int[][] surface) {
		this.surface = surface; 
	}
	
	public int getSurface(Vector2i query) { return surface[query.getX()][query.getY()];}

	@Override
	public int getSurface(int x, int z) { return surface[x][z]; }

}
