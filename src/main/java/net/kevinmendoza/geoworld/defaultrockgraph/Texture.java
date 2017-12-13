package net.kevinmendoza.geoworld.defaultrockgraph;

import net.kevinmendoza.geoworld.configuration.blocks.ITextureDefault;

class Texture implements ITexture {

	private final String texture; 
	
	private Texture(Builder builder) {
		texture = builder.getTexture();
	}

	public String getTextureName(int variant) { return texture; }
	
	public void setBaseRock(IRock baseRock) { }
	
	static class Builder {
		
		private String tex;
		
		private String getTexture() { return tex; }
		
		public Builder setTexture(String tex) { this.tex=tex; return this; }
		
		public ITexture build() {
			return new Texture(this);
		}
		
	}

}
