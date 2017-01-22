package net.kevinmendoza.geoworld.proceduralgeneration.probability;

import java.util.Random;

class UniformDistribution implements Distribution {

	private double min;
	private Random rand;
	private double range;

	UniformDistribution(UniformBuilder builder) {
		this.rand = builder.rand;
		this.min = builder.min;
		this.range = builder.max - builder.min;
	}

	public double getRVar() {
		return rand.nextDouble()*range + min;
	}

	static class UniformBuilder { 
		private Random rand = new Random(1);
		private double max  = 1;
		private double min  = 0;

		public UniformBuilder setRandom(long seed){ this.rand=new Random(seed); return this; }
		public UniformBuilder setMax(double m) { this.max=m;  return this; }
		public UniformBuilder setMin(double m) { this.min=m;  return this; }
		public Distribution Build() { return new UniformDistribution(this); }
	}
}
