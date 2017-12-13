package net.kevinmendoza.geoworld.configuration.blocks;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BehaviorDefault implements IBehaviorDefaults {

	@Setting
	private double beginningThreshold;
	@Setting
	private double endingThreshold;
	@Setting
	private String target;
	@Setting
	private boolean isNull;
	
	private BehaviorDefault(Builder builder) {
		isNull = false;
		beginningThreshold = builder.getStart();
		endingThreshold = builder.getEnd();
		target = builder.getTarget();
	}
	
	public BehaviorDefault() { isNull = true; }
	
	public boolean isNull() { return isNull; }
	public double getLowerThreshold() { return beginningThreshold; }
	public double getUpperThreshold() { return endingThreshold; }
	public String getTarget() { return target; }
	
	static class Builder {

		private double beginningThreshold;
		private double endingThreshold;
		private String target;
		private boolean isNull;

		public Builder setThresholds(double[] thresholds) {
			if(!isNull) {
				beginningThreshold = thresholds[0];
				endingThreshold = thresholds[1];
			}
			return this;
		}
		public Builder setTarget(String t){target = t; return this; }
		public Builder setNull(boolean b) {isNull = b; return this; }
		
		private double getStart() { return beginningThreshold;}
		private double getEnd() { return endingThreshold; }
		private String getTarget() { return target; }
		
		public BehaviorDefault build() {
			if(isNull) {
				return new BehaviorDefault();
			}
			else {
				return new BehaviorDefault(this);
			}
		}

	}

}
