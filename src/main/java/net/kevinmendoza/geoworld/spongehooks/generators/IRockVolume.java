package net.kevinmendoza.geoworld.spongehooks.generators;

import java.util.List;

import com.flowpowered.math.vector.Vector3i;


/**
 * 
 * @author kevinmendoza
 *
 * interface representing sections of a chunk that can be changed
 */
interface IRockVolume {

	List<Vector3i> volumeList();

}
