package net.kevinmendoza.geoworld.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

abstract class Shape {

	private final int PRIME = 41;
	private final Vector2i center;
	private final double cos;
	private final double sin;
	private final RegionTypes type;
	protected final Random rand;
	
	Shape(Vector2i center,double rotation,RegionTypes type){
		this.type = type;
		this.center = center;
		this.rand = new Random(center.hashCode());
		this.cos = Math.cos(rotation);
		this.sin = Math.cos(rotation);
	}
	
	protected Vector2i getCenter() {
		return new Vector2i(center);
	}
	
	
	protected Vector2d getRotatedCoordinates(Vector2i vec) {
		vec.toDouble();
		double x = vec.getX() - center.getX();
		double z = vec.getY() - center.getY();
		x = x*cos - z*sin;
		z = z*cos + z*sin;
		return new Vector2d(x,z);
	}
	
	protected Vector2d getRotatedRelativeCoordinates(Vector2i vec) {
		vec.toDouble();
		double x = vec.getX();
		double z = vec.getY();
		x = x*cos - z*sin;
		z = z*cos + z*sin;
		return new Vector2d(x,z);
	}
	
	@Override 
	public int hashCode() {
		int center = this.center.hashCode()*PRIME;
		center^=type.hashCode();
		return center;
	}
	
	@Override
	public String toString() {
		return type.toString() + " " + center.toString();
	}
}
