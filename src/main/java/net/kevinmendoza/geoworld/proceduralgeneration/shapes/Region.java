package net.kevinmendoza.geoworld.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;

public interface Region {

	public boolean isInside(Vector2i vec);
	
	public double distanceToEdge(Vector2i vec);
	
	public int hashCode();
	
	public Vector2i getRandPoint();

}
