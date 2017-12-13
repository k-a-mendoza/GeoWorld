package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.Random;

class AlterationBehavior implements IAlterationBehavior {
	
	private final boolean isCumulative;
	private final double ST_DEV = 0.75;
	private final double endThreshold;
	private final double beginThreshold;
	private final double diff;
	private final String target;
	private final Random rand;
	
	AlterationBehavior(Builder builder){
		rand = new Random();
		isCumulative = builder.isCumulative();
		endThreshold = builder.getEndThreshold();
		beginThreshold = builder.getBeginningThreshold();
		target = builder.getTarget();
		diff = endThreshold - beginThreshold;
	}

	public boolean isCumulative() { return isCumulative; }
	public double reduceValue() { return endThreshold; }
	public String getTarget() { return target; }
	
	public boolean valueExceedsThreshold(double value) { 
		if(value > endThreshold) {
			return true;
		}
		else if(value < beginThreshold) {
			return false; 
		}
		else {
			double testDiff = value - beginThreshold;
			double fudgeFactor = diff*rand.nextDouble();
			double d = testDiff + fudgeFactor;
			return (d>diff);
		}
	}
	


	static class Builder {

		private boolean cumulative;
		private String t;
		private double bThres;
		private double eThres;
		
		public Builder setCumulative(boolean c) { cumulative = c; return this; }
		public Builder setLowerThreshold(double l) { bThres = l; return this; }
		public Builder setUpperThreshold(double u) { eThres = u; return this; }
		public Builder setTarget(String target) { t = target; return this; }
		
		private boolean isCumulative() { return cumulative; }
		private String getTarget() { return t; }
		private double getBeginningThreshold() { return bThres; }
		private double getEndThreshold() { return eThres; }

		public IAlterationBehavior build() {
			return new AlterationBehavior(this);
		}
		
	}
}
