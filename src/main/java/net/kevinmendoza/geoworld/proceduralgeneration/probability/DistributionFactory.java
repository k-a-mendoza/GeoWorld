package net.kevinmendoza.geoworld.proceduralgeneration.probability;

import ninja.leaping.configurate.ConfigurationNode;

public class DistributionFactory {

	public static Distribution BuildUniformDistribution(long seed, double min, double max){
		return new UniformDistribution.UniformBuilder().setMax(max).setMin(min).setRandom(seed).Build();
	}

	public static Distribution BuildNormalDistribution(long seed, double min, double max, 
			double skew, double bias){
		return new TruncatedSkewDistribution.SkewBuilder()
				.setBias(bias).setMax(max).setMin(min).setSkew(skew).setRandom(seed).Build();
	}
	
	public static Distribution BuildDistribution(ConfigurationNode node,long seed) {
		int x = node.getNode("max").getInt(100);
		int m = node.getNode("min").getInt(100);
		return BuildUniformDistribution(seed,m,x);
	}
}
