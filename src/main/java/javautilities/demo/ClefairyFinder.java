package javautilities.demo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javautilities.image.Images;
import javautilities.pokemon.PokeFinder;
import javautilities.robot.Robot;

public class ClefairyFinder {

	static String filename = "ClefairyFinder.txt";
	static Robot r;
	static Scanner sc; 
	static PokeFinder pf;
	// static Color goodColor = new Color(191, 138, 72);
	// static Color goodColor = new Color(149, 108, 61);
	static Color goodColor = new Color(143, 102, 54);
	
	public static void main(String[] args) throws AWTException {
		pf = new PokeFinder();
		pf.walkPattern = "aadd";
		
		r = new Robot();
		sc = new Scanner(System.in);
		
		try {
			init();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) {
			r.manyKeyPress(KeyEvent.VK_ALT, KeyEvent.VK_TAB);
			
			pf.isGood = (image) -> {
				return Images.hasColor(image, goodColor);
			};
			
			pf.nextGoodPokemon();
			sc.nextLine();
		}
		
	}
	
	private static void init() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals("")) continue;
			if (line.charAt(0) == '#') continue;
			String[] parts = line.split(" ");
			if (parts.length > 0) {
				switch (parts[0]) {
				case "delay": {
					pf.delay = Integer.parseInt(parts[1]); 
					break;
				}
				case "enemyBounds": {
					pf.enemyBounds = new Rectangle(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2]),
							Integer.parseInt(parts[3]),
							Integer.parseInt(parts[4])
						); 
					break;
				}
				case "runPos": {
					pf.runPos = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "walkPattern": {
					pf.walkPattern = parts[1]; 
					break;
				}
				case "goodColor": {
					goodColor = new Color(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2]),
							Integer.parseInt(parts[3])
						); 
					break;
				}
				}
			}
		}
		scanner.close();
	}
	
}
