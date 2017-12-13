package net.kevinmendoza.geoworld.defaultrockgraph;

public interface IAlterationBehavior {

	boolean isCumulative();

	boolean valueExceedsThreshold(double value);

	String getTarget();

	double reduceValue();

}
