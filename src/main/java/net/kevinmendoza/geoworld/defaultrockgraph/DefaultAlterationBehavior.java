package net.kevinmendoza.geoworld.defaultrockgraph;

class DefaultAlterationBehavior implements IAlterationBehavior {

	public boolean isCumulative() { return false; }
	public boolean valueExceedsThreshold(double value) { return false; }

	public String getTarget() { return null; }
	public double reduceValue() { return 0; }

}
