package net.kevinmendoza.geoworld.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

class Ellipse extends Shape implements Region {

	private final double a;
	private final double b;
	private static final RegionTypes type = RegionTypes.ELLIPSE;

	Ellipse(Vector2i center, double majorAxis, double minorAxis, double angleToNorth) {
		super(center, angleToNorth,type);
		a = majorAxis;
		b = minorAxis;
	}

	@Override
	public boolean isInside(Vector2i vec) {
		Vector2i vec1 = new Vector2i(vec);
		Vector2d rotated = getRotatedRelativeCoordinates(vec1);
		Vector2d center = getCenter().toDouble();
		Vector2d localVec = new Vector2d(rotated.getX() - center.getX(),
							  rotated.getY() - center.getY());
		return isLocallyInside(localVec);
	}
	
	private boolean isLocallyInside(Vector2d vec) {
		double x = vec.getX();
		double z = vec.getY();
		return (x*x / a + z*z / b < 1);
	}

	@Override
	public double distanceToEdge(Vector2i vec) {
		Vector2d rotated = getRotatedRelativeCoordinates(vec);
		double angle = getAngleFromQueryPoint(rotated);
		Vector2d edgePoint = new Vector2d(a*Math.cos(angle),b*Math.sin(angle));
		return edgePoint.distanceSquared(0,0)-rotated.distanceSquared(0,0);
	}
	
	private double getAngleFromQueryPoint(Vector2d vec) {
		if(vec.getX()<0.001 && vec.getX() > -0.001)
			vec.add(0.01, 0);
		return Math.atan(vec.getY()/vec.getX());
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Rectangle)) {
            return false;
        }

       	Ellipse user = (Ellipse) o;

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
