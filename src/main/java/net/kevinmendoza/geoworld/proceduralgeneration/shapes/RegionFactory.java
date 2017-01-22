package net.kevinmendoza.geoworld.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.region.GeologicRegionPrecursor;
import net.kevinmendoza.geoworld.proceduralgeneration.probability.Distribution;
import net.kevinmendoza.geoworld.proceduralgeneration.probability.DistributionFactory;
import ninja.leaping.configurate.ConfigurationNode;

public class RegionFactory {

	public static Region MakeRegionWithConfig(ConfigurationNode node, Vector2i vec) {
		Random rand = new Random(vec.hashCode());
		int mw = node.getNode("Maximum Width").getInt(100);
		int aw = node.getNode("Minimum Width").getInt(100);
		int al = node.getNode("Maximum Length").getInt(100);
		int ml = node.getNode("Minimum Length").getInt(100);
		RegionTypes rt = RegionTypes.valueOf(node.getNode("Region Type").getString("Rectangle"));
		Distribution distw = DistributionFactory.BuildUniformDistribution(vec.hashCode(),mw,aw);
		Distribution distl = DistributionFactory.BuildUniformDistribution(2^vec.hashCode(),ml,al);
		return RegionFactory.MakeRegionType(rt, vec, 
							distw.getRVar(), distl.getRVar(), rand.nextDouble()*Math.PI*2);
	}
	
	public static Region MakeRandomRegion(long seed,Vector2i fullCenter,int size) {
		Random rand = new Random(seed^fullCenter.hashCode());
		if(rand.nextBoolean())
			return new Ellipse(fullCenter,rand.nextInt(size)+size/2,rand.nextInt(size)+size/2,rand.nextDouble());
		else
			return new Rectangle(fullCenter,rand.nextInt(size)+size/2,rand.nextInt(size)+size/2,rand.nextDouble());
	}
	/**
	 * creates an Elliptical Region Object
	 * @param  vec the center of the Ellipse. fullCenter
	 * @param  xAxis half the xAxis extent
	 * @paramy yAxis half the yAxis extent
	 * @param  theta the angle in radians clockwise from the north
	 * @return
	 */
	public static Region MakeEllipse(Vector2i vec,double xAxis,
			double yAxis,double theta) {
		return new Ellipse(vec,xAxis,yAxis,theta);
	}
	
	/**
	 * creates a Rectangular Region Object
	 * @param  vec the center of the rectangle. fullCenter
	 * @param  xAxis half the xAxis extent
	 * @paramy yAxis half the yAxis extent
	 * @param  theta the angle in radians clockwise from the north
	 * @return
	 */
	public static Region MakeRectangle(Vector2i vec,double xAxis,
			double yAxis,double theta) {
		return new Rectangle(vec,xAxis,yAxis,theta);
	}
	public static Region MakeRegionType(RegionTypes type, Vector2i vec,
			double a, double b, double t) {
		if(type.equals(RegionTypes.ELLIPSE))
			return MakeEllipse(vec,a,b,t);
		else
			return MakeRectangle(vec,a,b,t);
	}
}
