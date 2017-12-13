package net.kevinmendoza.geoworld.configuration.blocks;

import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class BlockTexture implements ITextureDefault {

	private String baseTexture;
	
	private BlockTexture(Builder builder) {
		baseTexture = builder.getBaseTexture();
	}

	public String getBaseTexture() { return baseTexture; }

	static class Builder {

		private String s;
		
		private String getBaseTexture() { return s; }
		
		public Builder setBaseTexture(String[] tex) {
			this.s=tex[0]; return this; }
		public BlockTexture build() {
			return new BlockTexture(this);
		}

	}

}
