package net.kevinmendoza.geoworld.geology.geologicobject;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;

public interface GeologicObjectInterface {

	public boolean isInObject(Vector2i center);
	
	public ChemicalConditions getConditions(Vector3i query);
}
