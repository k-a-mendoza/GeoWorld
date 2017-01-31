package net.kevinmendoza.geoworld.geology.geologicobject;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;

public interface GeologicObjectInterface {

	public boolean isInObject(Vector2i center);
	
	public Region getRegion();
	
	public ChemicalConditions getConditions(Vector3i query);
}
