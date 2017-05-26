package net.kevinmendoza.geoworld.configuration.blocks;

import org.spongepowered.api.block.BlockState;

class BaseBlock {

	private ChangeConditions temperatureChange;
	private ChangeConditions pressureChange;
	private ChangeConditions hydrothermalChange;
	private ChangeConditions weatheringChange;
	
	public BaseBlock() {
		temperatureChange = new ChangeConditions();
		pressureChange 	  = new ChangeConditions();
		hydrothermalChange= new ChangeConditions();
		weatheringChange  = new ChangeConditions();
	}
	
}
