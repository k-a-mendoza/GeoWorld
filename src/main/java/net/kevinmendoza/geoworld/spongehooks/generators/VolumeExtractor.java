package net.kevinmendoza.geoworld.spongehooks.generators;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

class VolumeExtractor implements IRockVolumeExtractor {
	
	private GenerationPopulator generator;
	
	public VolumeExtractor(Builder builder) {
		generator = builder.getGenerator();
	}

	public IRockVolume getSurface(World world, MutableBlockVolume buffer, ImmutableBiomeVolume
			biomes) {
		populateVolumeWithDefaultGenerator(world, buffer, biomes);
		return createRockVolume(buffer);
	}
	
	private IRockVolume createRockVolume(MutableBlockVolume buffer) {
		return new RockVolume.Builder().setVolume(buffer).build();
	}

	private void populateVolumeWithDefaultGenerator(World world, MutableBlockVolume buffer,
			ImmutableBiomeVolume biomes) {
		generator.populate(world, buffer, biomes);
	}

	static class Builder {

		private GenerationPopulator generator;

		Builder setWorldGenerator(GenerationPopulator defaultBasePopulator) {
			this.generator = defaultBasePopulator; return this;
		}
		GenerationPopulator getGenerator() { return generator; }
		
		IRockVolumeExtractor build() {
			return new VolumeExtractor(this);
		}
		
	}

}
