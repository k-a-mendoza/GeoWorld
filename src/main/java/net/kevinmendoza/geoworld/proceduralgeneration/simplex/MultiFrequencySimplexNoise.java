/*  GeoWorlds: a geology-based procedural terrain generation mod for Minecraft.
    Copyright (C) 2015 Kevin A. Mendoza. kevinmendoza@icloud.como

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.kevinmendoza.geoworld.proceduralgeneration.simplex;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class MultiFrequencySimplexNoise implements NoiseMap {

	private double weightSum;
	
	List<NoiseMap> freqWrappers;


	MultiFrequencySimplexNoise(double[] frequencies, double[] weights,
			long seed) {
		this.weightSum = 0;
		this.freqWrappers = new ArrayList<NoiseMap>();
		
		for (int i = 0; i < weights.length; i++) {
			freqWrappers.add(
					NoiseMapFactory
					.SimpleSimplexMap(frequencies[i],weights[i],seed+i));
			this.weightSum += (double) weights[i];
		}
	}
	
	@Override
	public double getNoise(double x, double y, double z) {
		double sum = 0;
		for (int i = 0; i < freqWrappers.size(); i++) {
			sum += freqWrappers.get(i).getNoise(x,y,z);
		}
		sum = sum / weightSum;
		return sum;
	}
	
	@Override
	public double getNoise(double x, double y) {
		return getNoise(x,0,y);
	}
	
	@Override
	public double getNoise(Vector3i vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2i vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}

	@Override
	public double getNoise(Vector3d vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2d vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}
}
