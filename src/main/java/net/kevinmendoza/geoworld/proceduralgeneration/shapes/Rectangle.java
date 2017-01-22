package net.kevinmendoza.geoworld.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

class Rectangle extends Shape implements Region {

	private double a;
	private double b;
	private static final RegionTypes type = RegionTypes.RECTANGLE;

	Rectangle(Vector2i center,double xAxis, double yAxis, double angleToNorth) {
		super(center,angleToNorth,type);
		this.a =xAxis;
		this.b = yAxis;
	}

	public boolean isInside(Vector2i vec) {
		Vector2d rotated = this.getRotatedRelativeCoordinates(vec);
		return isLocallyInside(rotated); 
	}
	
	private boolean isLocallyInside(Vector2d vec) {
		Vector2d center = getCenter().toDouble();
		double dx = Math.abs(vec.getX()-center.getX());
		double dz = Math.abs(vec.getY()-center.getY());
		return (dx < a && dz < b); 
	}

	@Override
	public double distanceToEdge(Vector2i vec) {
		Vector2d rotated = getRotatedRelativeCoordinates(vec);
		double dx = Math.abs(rotated.getX()) - a;
		double dz = Math.abs(rotated.getY()) - b;
		if(dx > dz)
			return dz*dz;
		return dx*dx;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Rectangle)) {
            return false;
        }

        Rectangle user = (Rectangle) o;

        return user.hashCode()==user.hashCode();
    }

	@Override
	public Vector2i getRandPoint() {
		Vector2d rot;
		while(true) {
			Vector2i vec = new Vector2i(rand.nextInt((int)a),rand.nextInt((int)b));
			rot = getRotatedRelativeCoordinates(vec);
			if(isLocallyInside(rot)) { break; }
		}
		return new Vector2i(getCenter().getX() + rot.getX(),getCenter().getX() + rot.getY());
	}

}
