package net.kevinmendoza.geoworld.proceduralgeneration.pointgeneration;

public class PointGeneratorFactory {

	public static PointGeneratorInterface makePointGenerator(long seed,int spacing) {
		return new PointGenerator.PointGeneratorBuilder().withSeed(seed).withSpacing(spacing).Build();
	}
}
