package net.kevinmendoza.geoworld.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class SimpleSimplexNoiseMap implements NoiseMap{

	private double frequency;
	private double weight;

	private OpenSimplexNoise simplexNoise;

	SimpleSimplexNoiseMap(double frequency, double weight,Long seed) {
		this.weight=weight/2;
		this.frequency = frequency;
		this.simplexNoise = new OpenSimplexNoise(seed);
	}
	
	public double getNoise(double x, double y, double z) {
		double sNoise = simplexNoise.eval(x / frequency,
				y/ frequency,z/ frequency);
		
		return this.weight*(sNoise +1.0);
	}

	@Override
	public double getNoise(double x, double y) {
		return getNoise(x,0,y);
	}
	
	@Override
	public double getNoise(Vector3i vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2i vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}

	@Override
	public double getNoise(Vector3d vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2d vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}

}
