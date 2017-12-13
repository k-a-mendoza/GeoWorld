package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.Set;

public interface IRockParameters {

	Set<String> getRockNameSet();

	Object getRockDefaults(String rockName);

}
