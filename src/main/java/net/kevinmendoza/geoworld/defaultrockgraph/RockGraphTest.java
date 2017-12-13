package net.kevinmendoza.geoworld.defaultrockgraph;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import net.kevinmendoza.geoworld.configuration.GeoWorldConfiguration;
import net.kevinmendoza.geoworld.configuration.blocks.BaseBlockList;
import net.kevinmendoza.geoworld.configuration.blocks.IBlockDefault;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;

public class RockGraphTest {

	static BaseBlockList conf; 
	public static void main(String[] args) {
		conf = new BaseBlockList();
		List<IRock> rocks = RockGraphAccess.getRockList();
		Scanner scan = new Scanner(System.in);
		for(IRock rock : rocks) {
			BehaviorTest(rock);
			TextureTest(rock);
			println("All tests for " + rock.getName() + " are completed. Please Check Results");
			println("press any key to continue");
			scan.next();
		}
	}

	private static void TextureTest(IRock rock) {
		printDivider("Begin Texture Test on: " + rock.getName());
		println("Texture test will ensure each block returns a texture string");
		int MAX_TEX = 5;
		for(int i =0; i<MAX_TEX;i++) {
			System.out.print(" " + i + ":" + rock.getTexture(i));
		}
		printDivider("End Texture Test");
	}

	private static void BehaviorTest(IRock rock) {
		printDivider("Begin Behavior Test on: "+ rock.getName());
		println("Behavior test will test the target rock under changing conditions.");
		println("All four types of rock alteration behaviors will be tested in increments");
		println("Expect output to be loong");
		printDivider("Heat Test");
		AbstractAlteration alteration;
		IRock newRock; 
		for(double heat = 0; heat < 1500; heat+=75) {
			alteration = DefaultDataFactory.getAlteration(heat, 0,0,0);
			
			newRock = rock.applyAlteration(alteration);
			println("With " + heat + " of heat, new rock is" + newRock.getName());
		}
		printDivider("Pressure Test");
		for(double pressure = 0; pressure < 15; pressure+=0.2) {
			if(pressure > 10.0 && rock.getName().equalsIgnoreCase("SAND")) {
				int i = 0;
			}
			alteration = DefaultDataFactory.getAlteration(0, pressure,0,0);
			newRock = rock.applyAlteration(alteration);
			println("With " + pressure + " of pressure, new rock is" + newRock.getName());
		}
		printDivider("Hydrothermal Test");
		for(double hydrothermal = 0; hydrothermal < 2; hydrothermal+=0.07) {
			alteration = DefaultDataFactory.getAlteration(0, 0,hydrothermal,0);
			newRock = rock.applyAlteration(alteration);
			println("With " + hydrothermal + " of hydrothermal, new rock is" + newRock.getName());
		}
		printDivider("Weathering Test");
		for(double weather = 0; weather < 1000; weather+=15) {
			alteration = DefaultDataFactory.getAlteration(0, 0,0,weather);
			newRock = rock.applyAlteration(alteration);
			println("With " + weather + " of weathering, new rock is" + newRock.getName());
		}
		printDivider("End Behavior Test");
	}
	
	private static void println(String string) { System.out.println(string); }
	
	private static void printDivider(String inputString) {
		println("*************************  " + inputString + "  *************************");
	}

}
