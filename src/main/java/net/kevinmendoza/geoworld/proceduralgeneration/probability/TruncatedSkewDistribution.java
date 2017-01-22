package net.kevinmendoza.geoworld.proceduralgeneration.probability;

import java.util.Random;

class TruncatedSkewDistribution implements Distribution{

	private final Random rand;
	private final double max;
	private final double min;
	private final double skew;
	private final double bias;
	
	private TruncatedSkewDistribution(SkewBuilder builder) {
		this.rand = builder.rand;
		this.max  = builder.max;
		this.min  = builder.min;
		this.skew = builder.skew;
		this.bias = builder.bias;
	}

	public double getRVar() {
		double range = max - min;
		double mid = min + (range / 2.0);
		double unitGaussian = rand.nextGaussian();
		double biasFactor = Math.exp(bias);
		double retval = mid + (range
				* (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew))
						- 0.5));
		return retval;
	}

	static class SkewBuilder { 
		Random rand = new Random(1);
		private double max  = 1;
		private double min  = 0;
		private double skew = 1;
		private double bias = 1;


		public SkewBuilder setRandom(long seed){ this.rand=new Random(seed); return this; }
		public SkewBuilder setMax(double m) { this.max=m;  return this; }
		public SkewBuilder setMin(double m) { this.min=m;  return this; }
		public SkewBuilder setSkew(double s){ this.skew=s; return this; }
		public SkewBuilder setBias(double b){ this.bias=b; return this; }
		public Distribution Build() { return new TruncatedSkewDistribution(this); }
	}
}
