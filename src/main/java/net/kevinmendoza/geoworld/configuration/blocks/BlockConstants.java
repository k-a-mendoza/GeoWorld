package net.kevinmendoza.geoworld.configuration.blocks;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AlterationType;

class BlockConstants {
	
	
	private HashMap<String,Rock> rockMap;
	
	private Basalt basalt;
	private Andesite andesite;
	private Rhyolite rhyolite;
	private Diorite diorite;
	private Granite granite;

	private Mudstone mudstone;
	private SandStone sandstone;
	private Conglomerate conglomerate;
	private Slate slate;

	private Mud mud;
	private Sand sand;
	private Gravel gravel;
	private Talus talus;

	private MilkyQuartzVein 	quartzVein;
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
		rockMap.put("DIORITE", 	diorite);
		rockMap.put("GRANITE", 	granite);

		rockMap.put("MUDSTONE", 	mudstone);
		rockMap.put("SANDSTONE", 	sandstone);
		rockMap.put("CONGLOMERATE",conglomerate);
		rockMap.put("SLATE", 		slate);

		rockMap.put("MUD", 		mud);
		rockMap.put("SAND", 		sand);
		rockMap.put("GRAVEL", 		gravel);
		rockMap.put("TALUS", 		talus);

		rockMap.put("MILKY_QUARTZ_VEIN", 		quartzVein);
		rockMap.put("PEGMATITE_VEIN", 			pegmatiteVein);
	}

	void createBlocks() {

		basalt = new Basalt();
		andesite = new Andesite();
		rhyolite = new Rhyolite();

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
		
		quartzVein = new MilkyQuartzVein();
		pegmatiteVein 	  = new PegmatiteVein();
	}
	
	static abstract class Rock {
		
		boolean NULL_TEMP 		= false;
		boolean NULL_PRESSURE	= false;
		boolean NULL_HYDRO 	 	= false;
		boolean NULL_WEATHER 	= false;

		String   NAME;
		String[] TEXTURES;

		double[] TEMPERATURE_THRESHOLD;
		String   TEMPERATURE_CONVERSION;

		double[] PRESSURE_THRESHOLD;
		String   PRESSURE_CONVERSION;

		double[] HYDROTHERMAL_THRESHOLD;
		String   HYDROTHERMAL_CONVERSION;

		double[] WEATHER_THRESHOLD;
		String   WEATHER_CONVERSION ;

		BlockDefault getBaseBlock() {
			
			return new BlockDefault.Builder()
					.setBehaviorMap(new BehaviorMap.Builder()
							.addBehavior(AlterationType.HEAT, new BehaviorDefault.Builder()
									.setNull(NULL_TEMP)
									.setTarget(TEMPERATURE_CONVERSION)
									.setThresholds(TEMPERATURE_THRESHOLD)
									.build())
							.addBehavior(AlterationType.PRESSURE, new BehaviorDefault.Builder()
									.setNull(NULL_PRESSURE)
									.setTarget(PRESSURE_CONVERSION)
									.setThresholds(PRESSURE_THRESHOLD)
									.build())
							.addBehavior(AlterationType.HYDROTHERMAL, new BehaviorDefault.Builder()
									.setNull(NULL_HYDRO)
									.setTarget(HYDROTHERMAL_CONVERSION)
									.setThresholds(HYDROTHERMAL_THRESHOLD)
									.build())
							.addBehavior(AlterationType.WEATHERING, new BehaviorDefault.Builder()
									.setNull(NULL_WEATHER)
									.setTarget(WEATHER_CONVERSION)
									.setThresholds(WEATHER_THRESHOLD)
									.build())
							.build())
					.setName(NAME)
					.setTextures(new BlockTexture.Builder()
							.setBaseTexture(TEXTURES)
							.build())
					.build();
		}
	}

	private static final class Basalt extends Rock {

		Basalt() {
			NAME 	  				= "BASALT";
			TEXTURES 				= new String[]{"OBSIDIAN"};

			TEMPERATURE_THRESHOLD  	= new double[]{300,600};
			TEMPERATURE_CONVERSION 	= "SLATE";

			PRESSURE_THRESHOLD  	= new double[]{4,7};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.7,1.2};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Andesite extends Rock {
		Andesite() {
			
			NAME 	  				= "ANDESITE";
			TEXTURES 				= new String[]{"ANDESITE"};

			TEMPERATURE_THRESHOLD  	= new double[]{300,600};
			TEMPERATURE_CONVERSION 	= "SLATE";

			PRESSURE_THRESHOLD  	= new double[]{4,7};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.6,1.1};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Rhyolite extends Rock {
		Rhyolite() {
			
			NAME 	  				= "RHYOLITE";
			TEXTURES 				= new String[]{"STONE"};

			TEMPERATURE_THRESHOLD  	= new double[]{300,600};
			TEMPERATURE_CONVERSION 	= "SLATE";

			PRESSURE_THRESHOLD  	= new double[]{4,7};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.5,1.0};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Diorite extends Rock {
		Diorite() {
			
			NAME 	   	= "DIORITE";
			TEXTURES 	= new String[]{"DIORITE"};

			NULL_TEMP	  = true;
			NULL_PRESSURE = true;

			HYDROTHERMAL_THRESHOLD  = new double[]{0.7,1.1};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Granite extends Rock {
		Granite() {
			
			NAME 	   = "GRANITE";
			TEXTURES = new String[]{"GRANITE"};

			NULL_TEMP	  = true;
			NULL_PRESSURE = true;

			HYDROTHERMAL_THRESHOLD  = new double[]{0.7,1.1};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{300,700};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Mudstone extends Rock {
		Mudstone() {
		
			NAME 	   = "MUDSTONE";
			TEXTURES = new String[]{"HARDENED_CLAY"};

			TEMPERATURE_THRESHOLD  	= new double[]{200,400};
			TEMPERATURE_CONVERSION 	= "SLATE";

			PRESSURE_THRESHOLD  	= new double[]{2,4};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.3,1.0};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class SandStone extends Rock {
		SandStone() {
			
			NAME 	   = "SANDSTONE";
			TEXTURES = new String[]{"SANDSTONE","RED_SANDSTONE"};

			TEMPERATURE_THRESHOLD  	= new double[]{300,600};
			TEMPERATURE_CONVERSION 	= "SLATE";

			PRESSURE_THRESHOLD  	= new double[]{4,6};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.5,1.0};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{10,30};
			WEATHER_CONVERSION 		= "SAND";
		}
	}
	private static final class Conglomerate extends Rock {
		Conglomerate() {
			
			NAME 	   = "CONGLOMERATE";
			TEXTURES = new String[]{"COBBLESTONE"};

			TEMPERATURE_THRESHOLD  	= new double[]{600,900};
			TEMPERATURE_CONVERSION 	= "DIORITE";

			PRESSURE_THRESHOLD  	= new double[]{4,8};
			PRESSURE_CONVERSION 	= "DIORITE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.6,1.0};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Slate extends Rock {
		Slate() {
			
			NAME 	   = "SLATE";
			TEXTURES = new String[]{"STONE"};

			TEMPERATURE_THRESHOLD  	= new double[]{700,900};
			TEMPERATURE_CONVERSION 	= "DIORITE";

			PRESSURE_THRESHOLD  	= new double[]{7,12};
			PRESSURE_CONVERSION 	= "DIORITE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.6,1.0};
			HYDROTHERMAL_CONVERSION = "MILKY_QUARTZ_VEIN";

			WEATHER_THRESHOLD  		= new double[]{100,300};
			WEATHER_CONVERSION 		= "TALUS";
		}
	}
	private static final class Mud extends Rock {
		Mud() {
			
			NAME 	 = "MUD";
			TEXTURES = new String[]{"CLAY"};

			NULL_WEATHER = true;
			
			TEMPERATURE_THRESHOLD  	= new double[]{100,200};
			TEMPERATURE_CONVERSION 	= "MUDSTONE";

			PRESSURE_THRESHOLD  	= new double[]{0.5,1};
			PRESSURE_CONVERSION 	= "MUDSTONE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.3,0.7};
			HYDROTHERMAL_CONVERSION = "MUDSTONE";
		}
	}
	private static final class Sand extends Rock {
		Sand() {
			
			NAME 	   = "SAND";
			TEXTURES = new String[]{"SAND","RED_SAND"};
			
			NULL_WEATHER = true;

			TEMPERATURE_THRESHOLD  	= new double[]{200,400};
			TEMPERATURE_CONVERSION 	= "SANDSTONE";

			PRESSURE_THRESHOLD  	= new double[]{1,2};
			PRESSURE_CONVERSION 	= "SANDSTONE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.3,0.7};
			HYDROTHERMAL_CONVERSION = "SANDSTONE";
		}
	}
	private static final class Gravel extends Rock {
		Gravel() {
			
			NAME 	   = "GRAVEL";
			TEXTURES = new String[]{"GRAVEL"};

			TEMPERATURE_THRESHOLD  	= new double[]{200,300};
			TEMPERATURE_CONVERSION 	= "CONGLOMERATE";

			PRESSURE_THRESHOLD  	= new double[]{1,3};
			PRESSURE_CONVERSION 	= "SLATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.3,0.7};
			HYDROTHERMAL_CONVERSION = "CONGLOMERATE";

			WEATHER_THRESHOLD  = new double[]{50,100};
			WEATHER_CONVERSION = "SAND";
		}
	}
	private static final class Talus extends Rock {
		Talus() {
			
			NAME 	   = "TALUS";
			TEXTURES = new String[]{ "COBBLESTONE"};

			TEMPERATURE_THRESHOLD  	= new double[]{300,600};
			TEMPERATURE_CONVERSION 	= "CONGLOMERATE";

			PRESSURE_THRESHOLD  	= new double[]{3,6};
			PRESSURE_CONVERSION 	= "CONGLOMERATE";

			HYDROTHERMAL_THRESHOLD  = new double[]{0.6,1.1};
			HYDROTHERMAL_CONVERSION = "CONGLOMERATE";

			WEATHER_THRESHOLD  = new double[]{50,100};
			WEATHER_CONVERSION = "GRAVEL";
		}
	}
	private static final class MilkyQuartzVein extends Rock {
		MilkyQuartzVein() {
			
			NAME 	   = "MILKY_QUARTZ_VEIN";
			TEXTURES		 = new String[]{"DIORITE"};
			
			NULL_HYDRO = true;

			TEMPERATURE_THRESHOLD  = new double[]{600,800};
			TEMPERATURE_CONVERSION = "PEGMATITE_VEIN";

			PRESSURE_THRESHOLD  = new double[]{6,8};
			PRESSURE_CONVERSION = "PEGMATITE_VEIN";

			WEATHER_THRESHOLD  = new double[]{100,300};
			WEATHER_CONVERSION = "TALUS";
		}
	}
	private static final class PegmatiteVein extends Rock {
		PegmatiteVein() {
			
			NAME 	   = "PEGMATITE_VEIN";
			TEXTURES 		= new String[]{"GRANITE"};

			NULL_TEMP 		= true;
			NULL_PRESSURE 	= true;
			NULL_HYDRO 		= true;

			WEATHER_THRESHOLD  = new double[]{100,300};
			WEATHER_CONVERSION = "TALUS";
		}
	}
	
}
