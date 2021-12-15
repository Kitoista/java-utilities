package javautilities.pokemon;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javautilities.audio.Audio;
import javautilities.lambda.Condition;
import javautilities.robot.Robot;

public class PokeFinder {

	Robot r;
	protected Color runColor = new Color(144, 196, 51);
	protected Color runColor2 = new Color(120, 181, 43);
	
	public int delay = 500;
	public String walkPattern = "ws";
	public Point runPos = new Point(-750, 1022);
	public Rectangle enemyBounds = new Rectangle(-1375, 275, 500, 225);
	public int waitAfterPoke = 1000;
	
	public Condition<BufferedImage> isGood;
	
	protected int counter = 0;
	
	public PokeFinder() {
		try {
			r = new Robot();
			Robot.TYPE_DELAY = delay;
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void walk(String pattern) {
		r.type(pattern);
	}
	
	public boolean isInFight() {
		Color color = r.getPixelColor(runPos.x, runPos.y);
		return color.equals(runColor) || color.equals(runColor2);
	}
	
	public BufferedImage nextPokemon() {
		while (!isInFight()) {
			walk(walkPattern);
		}
		return r.createScreenCapture(enemyBounds);
	}
	
	public void nextGoodPokemon() {
		while (true) {
			BufferedImage pokemon = nextPokemon();
			System.out.println("Got poke " + ++counter);
			if (isGood.evaluate(pokemon)) {
				alert();
				break;
			} else {
				System.out.println("Not good, clicking on run");
				r.clickOn(runPos);
				try {
					Thread.sleep(waitAfterPoke);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void alert() {
		System.out.println("GOOD");
		Audio.play("beepbeep");
	}
	
}
