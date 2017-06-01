package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.ArrayList;
import java.util.List;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ChangeConditions {

	@Setting
	private List<String> target = new ArrayList<>();
	@Setting
	private double threshold;
	
	void setTarget(String[] string) {
		for(int i = 0;i<string.length;i++)
			target.add(string[i]);
	}
	void setThreshold(double val) {
		threshold= val;
	}
	
	public boolean willChange(double input) {
		if(!target.get(0).equalsIgnoreCase("NA") && threshold < input)
			return true;
		return false;
	}

	public String getTarget(int variant) {
		return target.get(variant);
	}
}
