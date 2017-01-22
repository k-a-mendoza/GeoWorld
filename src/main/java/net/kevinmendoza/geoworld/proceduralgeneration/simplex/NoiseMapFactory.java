package net.kevinmendoza.geoworld.proceduralgeneration.simplex;

public class NoiseMapFactory {

	
	public static NoiseMap MakeDomainMap(NoiseMap map1, NoiseMap map2,double cutoff){
		return new DomainMap(map1,map2,cutoff);
	}
	
	public static NoiseMap MakeSmoothDomainMap(NoiseMap map1, NoiseMap map2,double cutoff){
		return new DomainMap(map1,map2,cutoff);
	}
	
	public static NoiseMap MakeSimplexNoiseMap(double[] frequencies,double[] weight,long seed) {
		return new MultiFrequencySimplexNoise(frequencies,weight,seed);
	}
	
	public static NoiseMap getOffsetNoiseMap(NoiseMap map, double offset, double multiplier) {
		return new ImplementedMap(map,offset,multiplier);
	}
	
	static NoiseMap SimpleSimplexMap(double frequencies,double weight,long seed) {
		return new SimpleSimplexNoiseMap(frequencies,weight,seed);
	}
	
}
