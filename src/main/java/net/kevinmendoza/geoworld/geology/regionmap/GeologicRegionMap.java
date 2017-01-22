package net.kevinmendoza.geoworld.geology.regionmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.geology.StratigraphicColumn;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworld.proceduralgeneration.pointgeneration.PointGeneratorInterface;


public abstract class GeologicRegionMap implements GeologicContainer {
	
	private final double frequency;
	private final double spacing;
	private final LithogenicOrder order;
	private PointGeneratorInterface pointQuery;
	private GeologicRegionMapCache<GeologicObjectInterface> regionCache;
	private long seed;
	
	public GeologicRegionMap(GeologicRegionMapBuilderInterface builder) {
		this.spacing   	= builder.getSpacing();
		this.frequency 	= builder.getFrequency();
		this.order 	  	= builder.getOrder();
		seed 	  		= 1;
		regionCache 	= new GeologicRegionMapCache<>();
	    pointQuery 		= PointGeneratorFactory.makePointGenerator(seed,(int)spacing);
	}
	
	@Override
	public void primeGenerationAt(Vector2i min) {
		Vector2i center = new Vector2i(min);
		List<Vector2i> pointsToBuild = regionCache.checkKeys(
												pointQuery.getFlooredCenterNeighborhood(center));
		if(!pointsToBuild.isEmpty()) {
			for(Vector2i vec : pointsToBuild) {
				if(shouldBuildRegion(vec)) {
					regionCache.addRegion(vec, buildRegion(pointQuery.getFullCenter(vec)));
				}
				else {
					regionCache.addRegion(vec, null);
				}
			}
		}
	}
	
	protected abstract GeologicObjectInterface buildRegion(Vector2i fullCenter);

	private boolean shouldBuildRegion(Vector2i center) {
		Random rand = new Random(center.hashCode()^seed);
		if(rand.nextInt((int) (1/frequency)) <=1) 
			return true;
		return false;
	}
	
	@Override
	public void setSeed(long seed) {
		this.seed  = seed;
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,(int)spacing);
		setFactorySeed(seed);
	}
	
	public abstract void setFactorySeed(long seed);
	
	@Override
	public LithogenicOrder getOrder() {
		return order;
	}

	@Override
	public StratigraphicColumn getColumnAt(Vector2i center) {
		List<Vector2i> surroundingKeys = pointQuery.getFlooredCenterNeighborhood(center);
		List<GeologicObjectInterface> geologyRegions = regionCache.getNeighboringRegions(surroundingKeys);
		List<GeologicObjectInterface> relevantRegions = new ArrayList<>();
		for(GeologicObjectInterface geologyRegion : geologyRegions) {
			if(geologyRegion.isInObject(center)){
				relevantRegions.add(geologyRegion);
			}
		}
		return buildStratigraphicColumn(relevantRegions);
	}
	
	protected abstract StratigraphicColumn buildStratigraphicColumn(List<GeologicObjectInterface> relevantRegions);

}
