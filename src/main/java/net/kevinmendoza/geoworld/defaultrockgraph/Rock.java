package net.kevinmendoza.geoworld.defaultrockgraph;

import java.util.HashMap;
import java.util.List;

import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;

public class Rock implements IRock {
	
	private static final int MAX_RECURSION = 4;
	
	private final String name;
	private final ITexture texture;
	private final HashMap<AlterationType,IAlterationBehavior> rockBehavior;
	
	private Rock(Builder builder){
		name = builder.getName();
		texture = builder.getTexture();
		rockBehavior = builder.getBehaviorList();
	}

	public String getName() { return name; }
	
	public String getTexture(int variant) { return texture.getTextureName(variant); }
	
	public IRock applyAlteration(AbstractAlteration alteration) {
		for(AlterationType type : AlterationType.values()) {
			IAlterationBehavior behavior = rockBehavior.get(type);
			double value = alteration.getAlterationValue(type);
			if(behavior.valueExceedsThreshold(value)) {
				AbstractAlteration newAlteration = adjustAlteration(type,alteration,behavior,value);
				return RockGraphAccess.getRock(behavior.getTarget()).applyAlteration(newAlteration,this,1);
			}
		}
		return this;
	}

	public IRock applyAlteration(AbstractAlteration alteration,IRock baseRock, int recursion) {
		if(recursion<MAX_RECURSION) {
			for(AlterationType type : AlterationType.values()) {
				IAlterationBehavior behavior = rockBehavior.get(type);
				double value = alteration.getAlterationValue(type);
				if(behavior.valueExceedsThreshold(value)) {
					AbstractAlteration newAlteration = adjustAlteration(type,alteration,behavior,value);
					return RockGraphAccess.getRock(behavior.getTarget()).applyAlteration(newAlteration,baseRock,recursion++);
				}

			}
		}
		texture.setBaseRock(baseRock);
		return this;
	}

	private AbstractAlteration adjustAlteration(AlterationType type, AbstractAlteration alteration, IAlterationBehavior behavior,
			double value) {
		if(behavior.isCumulative()) {
			value = value - behavior.reduceValue();
			if(value < 0)
				value = 0;
			AbstractAlteration alteration2 = alteration.copy();
			alteration2.setAlterationValue(type,value);
			return alteration2;
		}
		else {
			return alteration;
		}
	}

	static class Builder {

		private String rockName;
		private HashMap<AlterationType, IAlterationBehavior> behaviorList;
		private ITexture texture;
		
		public Builder setRockName(String name) { this.rockName = name; return this; }
		public Builder setBehaviorList(HashMap<AlterationType,IAlterationBehavior> list) {
			this.behaviorList = list; return this;
		}
		public Builder setTexture(ITexture texture) {
			this.texture=texture; return this;
		}
		
		private String getName() { return rockName; }
		private HashMap<AlterationType, IAlterationBehavior> getBehaviorList() { return behaviorList; }
		private ITexture getTexture() { return texture; }
		public IRock build() {
			return new Rock(this);
		}

	}
}
