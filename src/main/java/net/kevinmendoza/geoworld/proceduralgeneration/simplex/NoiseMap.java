package net.kevinmendoza.geoworld.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public interface NoiseMap {

	public double getNoise(double x, double y);
	
	public double getNoise(double x, double y,double z);
	
	public double getNoise(Vector3i vec);
	
	public double getNoise(Vector2i vec);
	
	public double getNoise(Vector3d vec);
	
	public double getNoise(Vector2d vec);
}
