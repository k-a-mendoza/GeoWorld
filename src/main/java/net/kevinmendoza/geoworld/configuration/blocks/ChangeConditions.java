package net.kevinmendoza.geoworld.configuration.blocks;

public class ChangeConditions {

	private String target;
	private double threshold;
	
	public boolean willChange(double input) {
		if(threshold < input && threshold > -0.01)
			return true;
		return false;
	}

	public String getTarget() {
		return target;
	}
}
