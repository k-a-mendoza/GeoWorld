package net.kevinmendoza.geoworld.spongehooks.populators.geologysimulation;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;

public interface IBlockMap {

	void put(Vector3i queryVector, AbstractAlteration alt, AbstractRock r);

	AbstractRock getRock(Vector3i key);

	AbstractAlteration getAlteration(Vector3i key);

}
