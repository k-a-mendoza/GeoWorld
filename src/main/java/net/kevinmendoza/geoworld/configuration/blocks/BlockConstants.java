package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.HashMap;

class BlockConstants {
	
	private HashMap<String,Rock> rockMap;
	
	private Basalt basalt;
	private Andesite andesite;
	private Rhyolite rhyolite;
	private Gabbro gabbro;
	private Diorite diorite;
	private Granite granite;

	private Mudstone mudstone;
	private SandStone sandstone;
	private Conglomerate conglomerate;
	private Slate slate;
	private Gneiss gneiss;

	private Mud mud;
	private Sand sand;
	private Gravel gravel;
	private Talus talus;

	private MilkyQuartzStringer quartzStringer;
	private MilkyQuartzVein 	quartzVein;
	private PegmatiteStringer	pegmatiteStringer;
	private PegmatiteVein		pegmatiteVein;

	BlockConstants(){
		createBlocks();
		addBlocks();
	}
	
	HashMap<String,Rock> getRocks() {
		return rockMap;
	}

	void addBlocks() {
		rockMap = new HashMap<>();
		rockMap.put("BASALT", 		basalt);
		rockMap.put("ANDESITE", 	andesite);
		rockMap.put("RHYOLITE", 	rhyolite);
		rockMap.put("GABBRO", 		gabbro);
		rockMap.put("DIORITE", 	diorite);
		rockMap.put("GRANITE", 	granite);

		rockMap.put("MUDSTONE", 	mudstone);
		rockMap.put("SANDSTONE", 	sandstone);
		rockMap.put("CONGLOMERATE",conglomerate);
		rockMap.put("SLATE", 		slate);
		rockMap.put("GNEISS", 		gneiss);

		rockMap.put("MUD", 		mud);
		rockMap.put("SAND", 		sand);
		rockMap.put("GRAVEL", 		gravel);
		rockMap.put("TALUS", 		talus);

		rockMap.put("MILKY_QUARTZ_STRINGER", 	quartzStringer);
		rockMap.put("MILKY_QUARTZ_VEIN", 		quartzVein);
		rockMap.put("PEGMATITE_STRINGER", 		pegmatiteStringer);
		rockMap.put("PEGMATITE_VEIN", 			pegmatiteVein);
	}

	void createBlocks() {
		basalt = new Basalt();
		andesite = new Andesite();
		rhyolite = new Rhyolite();
		gabbro = new Gabbro();
		diorite = new Diorite();
		granite = new Granite();

		mud = new Mud();
		sand = new Sand();
		gravel = new Gravel();
		talus = new Talus();

		mudstone = new Mudstone();
		sandstone = new SandStone();
		conglomerate = new Conglomerate();
		slate = new Slate();
		gneiss = new Gneiss();

		quartzStringer = new MilkyQuartzStringer();
		quartzVein = new MilkyQuartzVein();
		pegmatiteStringer = new PegmatiteStringer();
		pegmatiteVein 	  = new PegmatiteVein();
	}
	
	static abstract class Rock {
		String   NAME;
		String[] TEXTURES;

		double 	TEMPERATURE_THRESHOLD;
		String[] TEMPERATURE_CONVERSION;

		double 	PRESSURE_THRESHOLD;
		String[] PRESSURE_CONVERSION;

		double 	HYDROTHERMAL_THRESHOLD;
		String[] HYDROTHERMAL_CONVERSION;

		double 	WEATHER_THRESHOLD;
		String[] WEATHER_CONVERSION ;

		BaseBlock getBaseBlock() {
			BaseBlock block = new BaseBlock();

			ChangeConditions temperature = new ChangeConditions();
			ChangeConditions pressure = new ChangeConditions();
			ChangeConditions hydrothermal = new ChangeConditions();
			ChangeConditions weather = new ChangeConditions();

			block.setTextures(TEXTURES);

			temperature.setThreshold(TEMPERATURE_THRESHOLD);
			pressure.setThreshold(PRESSURE_THRESHOLD);
			hydrothermal.setThreshold(HYDROTHERMAL_THRESHOLD);
			weather.setThreshold(WEATHER_THRESHOLD);

			temperature.setTarget(TEMPERATURE_CONVERSION);
			pressure.setTarget(PRESSURE_CONVERSION);
			hydrothermal.setTarget(HYDROTHERMAL_CONVERSION);
			weather.setTarget(WEATHER_CONVERSION);

			block.setConditions("Temperature",temperature);
			block.setConditions("Pressure",pressure);
			block.setConditions("hydrothermal", hydrothermal);
			block.setConditions("weather", weather);

			return block;
		}
	}

	private static final class Basalt extends Rock {

		Basalt() {
			NAME 	  				= "BASALT";
			TEXTURES 				= new String[]{"STONE","OBSIDIAN"};

			TEMPERATURE_THRESHOLD  	= 800;
			TEMPERATURE_CONVERSION 	= new String[]{"SLATE"};

			PRESSURE_THRESHOLD  	= 3;
			PRESSURE_CONVERSION 	= new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 2;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  		= 100;
			WEATHER_CONVERSION 		= new String[]{"TALUS"};
		}
	}
	private static final class Andesite extends Rock {
		Andesite() {
			NAME 	  				= "ANDESITE";
			TEXTURES 				= new String[]{"ANDESITE"};

			TEMPERATURE_THRESHOLD  	= 700;
			TEMPERATURE_CONVERSION 	= new String[]{"SLATE"};

			PRESSURE_THRESHOLD  	= 2;
			PRESSURE_CONVERSION 	= new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 1.5;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  		= 50;
			WEATHER_CONVERSION 		= new String[]{"GRAVEL"};
		}
	}
	private static final class Rhyolite extends Rock {
		Rhyolite() {
			NAME 	  				= "RHYOLITE";
			TEXTURES 				= new String[]{"STONE"};

			TEMPERATURE_THRESHOLD  	= 600;
			TEMPERATURE_CONVERSION 	= new String[]{"SLATE"};

			PRESSURE_THRESHOLD  	= 1.5;
			PRESSURE_CONVERSION 	= new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 1.5;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  		= 10;
			WEATHER_CONVERSION 		= new String[]{"SAND"};
		}
	}
	private static final class Gabbro extends Rock {
		Gabbro() {
			NAME 	   = "GABBRO";
			TEXTURES = new String[]{"STONE","ANDESITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = 4;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 50;
			WEATHER_CONVERSION =new String[]{ "TALUS"};
		}
	}
	private static final class Diorite extends Rock {
		Diorite() {
			NAME 	   = "Diorite";
			TEXTURES = new String[]{"DIORITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = 2;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 150;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class Granite extends Rock {
		Granite() {
			NAME 	   = "GRANITE";
			TEXTURES = new String[]{"GRANITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = 2;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 300;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class Mudstone extends Rock {
		Mudstone() {
			NAME 	   = "MUDSTONE";
			TEXTURES = new String[]{"HARDENED_CLAY"};

			TEMPERATURE_THRESHOLD  = 100;
			TEMPERATURE_CONVERSION = new String[]{"SLATE"};

			PRESSURE_THRESHOLD  = 2;
			PRESSURE_CONVERSION = new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 1;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 5;
			WEATHER_CONVERSION = new String[]{"MUD"};
		}
	}
	private static final class SandStone extends Rock {
		SandStone() {
			NAME 	   = "SANDSTONE";
			TEXTURES = new String[]{"SANDSTONE","RED_SANDSTONE"};

			TEMPERATURE_THRESHOLD  = 300;
			TEMPERATURE_CONVERSION = new String[]{"SLATE"};

			PRESSURE_THRESHOLD  = 2;
			PRESSURE_CONVERSION = new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 1.5;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 10;
			WEATHER_CONVERSION = new String[]{"SAND"};
		}
	}
	private static final class Conglomerate extends Rock {
		Conglomerate() {
			NAME 	   = "CONGLOMERATE";
			TEXTURES = new String[]{"COBBLESTONE"};

			TEMPERATURE_THRESHOLD  = 800;
			TEMPERATURE_CONVERSION = new String[]{"GNEISS"};

			PRESSURE_THRESHOLD  = 4;
			PRESSURE_CONVERSION = new String[]{"GNEISS"};

			HYDROTHERMAL_THRESHOLD  = 0.5;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 100;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class Slate extends Rock {
		Slate() {
			NAME 	   = "SLATE";
			TEXTURES = new String[]{"STONE"};

			TEMPERATURE_THRESHOLD  = 800;
			TEMPERATURE_CONVERSION = new String[]{"GNEISS"};

			PRESSURE_THRESHOLD  = 8;
			PRESSURE_CONVERSION = new String[]{"GNEISS"};

			HYDROTHERMAL_THRESHOLD  = 1;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 70;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class Gneiss extends Rock {
		Gneiss() {
			NAME 	   = "GNEISS";
			TEXTURES = new String[]{"STONE","DIORITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = 3;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 150;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class Mud extends Rock {
		Mud() {
			NAME 	 = "MUD";
			TEXTURES = new String[]{"CLAY"};

			TEMPERATURE_THRESHOLD  = 100;
			TEMPERATURE_CONVERSION = new String[]{"MUDSTONE"};

			PRESSURE_THRESHOLD  = 0.5;
			PRESSURE_CONVERSION = new String[]{"MUDSTONE"};

			HYDROTHERMAL_THRESHOLD  = 2;
			HYDROTHERMAL_CONVERSION = new String[]{"MUDSTONE"};

			WEATHER_THRESHOLD  = -1;
			WEATHER_CONVERSION = new String[]{"NA"};
		}
	}
	private static final class Sand extends Rock {
		Sand() {
			NAME 	   = "SAND";
			TEXTURES = new String[]{"SAND","RED_SAND"};

			TEMPERATURE_THRESHOLD  = 300;
			TEMPERATURE_CONVERSION = new String[]{"SANDSTONE"};

			PRESSURE_THRESHOLD  = 0.5;
			PRESSURE_CONVERSION = new String[]{"SANDSTONE"};

			HYDROTHERMAL_THRESHOLD  = 0.5;
			HYDROTHERMAL_CONVERSION = new String[]{"SANDSTONE"};

			WEATHER_THRESHOLD  = 50;
			WEATHER_CONVERSION = new String[]{"MUD"};
		}
	}
	private static final class Gravel extends Rock {
		Gravel() {
			NAME 	   = "GRAVEL";
			TEXTURES = new String[]{"GRAVEL"};

			TEMPERATURE_THRESHOLD  = 300;
			TEMPERATURE_CONVERSION = new String[]{"CONGLOMERATE"};

			PRESSURE_THRESHOLD  = 1;
			PRESSURE_CONVERSION = new String[]{"CONGLOMERATE"};

			HYDROTHERMAL_THRESHOLD  = 1;
			HYDROTHERMAL_CONVERSION = new String[]{"CONGLOMERATE"};

			WEATHER_THRESHOLD  = 10;
			WEATHER_CONVERSION = new String[]{"SAND"};
		}
	}
	private static final class Talus extends Rock {
		Talus() {
			NAME 	   = "TALUS";
			TEXTURES = new String[]{"COBBLESTONE","GREEN_COBBLESTONE"};

			TEMPERATURE_THRESHOLD  = 400;
			TEMPERATURE_CONVERSION = new String[]{"SLATE"};

			PRESSURE_THRESHOLD  = 2;
			PRESSURE_CONVERSION = new String[]{"SLATE"};

			HYDROTHERMAL_THRESHOLD  = 2;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_STRINGER"};

			WEATHER_THRESHOLD  = 5;
			WEATHER_CONVERSION = new String[]{"GRAVEL"};
		}
	}
	private static final class MilkyQuartzStringer extends Rock {
		MilkyQuartzStringer() {
			NAME 	   = "MILKY_QUARTZ_STRINGER";
			TEXTURES = new String[]{"STONE","COBBLESTONE","DIORITE"};

			TEMPERATURE_THRESHOLD  = 700;
			TEMPERATURE_CONVERSION = new String[]{"PEGMATITE_STRINGER"};

			PRESSURE_THRESHOLD  = 5;
			PRESSURE_CONVERSION = new String[]{"PEGMATITE_STRINGER"};

			HYDROTHERMAL_THRESHOLD  = 4;
			HYDROTHERMAL_CONVERSION = new String[]{"MILKY_QUARTZ_VEIN"};

			WEATHER_THRESHOLD  = 50;
			WEATHER_CONVERSION = new String[]{"GRAVEL"};
		}
	}
	private static final class MilkyQuartzVein extends Rock {
		MilkyQuartzVein() {
			NAME 	   = "MILKY_QUARTZ_VEIN";
			TEXTURES = new String[]{"DIORITE"};

			TEMPERATURE_THRESHOLD  = 700;
			TEMPERATURE_CONVERSION = new String[]{"PEGMATITE_VEIN"};

			PRESSURE_THRESHOLD  = 5;
			PRESSURE_CONVERSION = new String[]{"PEGMATITE_VEIN"};

			HYDROTHERMAL_THRESHOLD  = -1;
			HYDROTHERMAL_CONVERSION = new String[]{"NA"};

			WEATHER_THRESHOLD  = 100;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
	private static final class PegmatiteStringer extends Rock {
		PegmatiteStringer() {
			NAME 	   = "PEGMATITE_STRINGER";
			TEXTURES = new String[]{"STONE","GRANITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = 5;
			HYDROTHERMAL_CONVERSION = new String[]{"PEGMATITE_VEIN"};

			WEATHER_THRESHOLD  = 60;
			WEATHER_CONVERSION = new String[]{"GRAVEL"};
		}
	}
	private static final class PegmatiteVein extends Rock {
		PegmatiteVein() {
			NAME 	   = "PEGMATITE_VEIN";
			TEXTURES = new String[]{"GRANITE"};

			TEMPERATURE_THRESHOLD  = -1;
			TEMPERATURE_CONVERSION = new String[]{"NA"};

			PRESSURE_THRESHOLD  = -1;
			PRESSURE_CONVERSION = new String[]{"NA"};

			HYDROTHERMAL_THRESHOLD  = -1;
			HYDROTHERMAL_CONVERSION = new String[]{"NA"};

			WEATHER_THRESHOLD  = 100;
			WEATHER_CONVERSION = new String[]{"TALUS"};
		}
	}
}
