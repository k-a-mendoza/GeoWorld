package net.kevinmendoza.geoworld.spongehooks.geologyconversion;

class GeologyScore {

	private double center;
	private double spread;
	
	
	public GeologyScore(double center, double spread) {
		this.center = center;
		this.spread = spread;
	}
	
	double getScore(double parameter) {
		double radical = parameter - center;
		radical*=radical;
		radical*=spread;
		return Math.exp(radical);
	}
}
