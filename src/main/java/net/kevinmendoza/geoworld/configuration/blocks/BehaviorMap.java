package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class BehaviorMap {
	
	@Setting
	private HashMap<AlterationType,BehaviorDefault> behaviorMap;
	
	private BehaviorMap(Builder builder) {
		behaviorMap  = builder.getMap();
	}
	public IBehaviorDefaults getBehavior(AlterationType type) { return behaviorMap.get(type); }

	static class Builder {

		private HashMap<AlterationType, BehaviorDefault> behaviorMap;
		
		public Builder() { behaviorMap = new HashMap<>(); }
		public Builder addBehavior(AlterationType type,BehaviorDefault behavior) {
			behaviorMap.put(type, behavior);
			return this;
		}
		
		public BehaviorMap build() {
			return new BehaviorMap(this);
		}
		
		private HashMap<AlterationType, BehaviorDefault> getMap() { return behaviorMap; }
		
	}
}
