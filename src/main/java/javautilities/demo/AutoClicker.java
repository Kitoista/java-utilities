package javautilities.demo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

import javautilities.robot.Robot;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.frame.Show;

public class AutoClicker {

	public static String filename = "AutoClicker.txt";
	
	static int activeTime = 30 * 60 * 1000;
	static int fakeTime = 30 * 60 * 1000;
	
	static int activeInterval = 5 * 1000;
	static int fakeInterval = 5 * 60 * 1000;

	static int activeIterations;
	static int fakeIterations;

	static Point spinPos = new Point(1250, 500);
	static Point xPos = new Point(1300, 275);
	
	static KLabel status = new KLabel("SPINNING");
	
	static Robot r;
	
	public static void main(String[] args) throws AWTException, InterruptedException, FileNotFoundException {
		load();
		run();
	}
	
	public static void load() {
		try {
			Show show = new Show();
			show.component(status);
			show.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			show.getFrame().setTitle("SpinBot");
			show.getFrame().setSize(new Dimension(300, 75));
			Scanner scanner = new Scanner(new File(filename));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.equals("")) continue;
				if (line.charAt(0) == '#') continue;
				String[] parts = line.split(" ");
				if (parts.length > 0) {
					switch (parts[0]) {
					case "activeTime": {
						activeTime = Integer.parseInt(parts[1]);
						break;
					}
					case "activeInterval": {
						activeInterval = Integer.parseInt(parts[1]);
						break;
					}
					case "fakeTime": {
						fakeTime = Integer.parseInt(parts[1]);
						break;
					}
					case "fakeInterval": {
						fakeInterval = Integer.parseInt(parts[1]);
						break;
					}
					case "spinPos": {
						spinPos.x = Integer.parseInt(parts[1]);
						spinPos.y = Integer.parseInt(parts[2]);
						break;
					}
					case "xPos": {
						xPos.x = Integer.parseInt(parts[1]);
						xPos.y = Integer.parseInt(parts[2]);
						break;
					}
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		activeIterations = activeTime / activeInterval;
		fakeIterations = fakeTime / fakeInterval;
	}
	
	public static void run() throws AWTException, InterruptedException {
		r = new Robot();
		
		while (true) {
			status.setText("SPINNING");
			for (int i=0; i < activeIterations; ++i) {
				active();
			}
			status.setText("DEACTIVATING");
			deactivate();
			status.setText("FAKING");
			for (int i=0; i < fakeIterations; ++i) {
				fake();
			}
			status.setText("ACTIVATING");
			activate();
		}
	}
	
	public static void active() throws InterruptedException {
		Thread.sleep(activeInterval);
		r.mouseMove(spinPos.x, spinPos.y);
		r.click();
	}
	
	public static void deactivate() throws InterruptedException {
		Thread.sleep(activeInterval);
		r.mouseMove(xPos.x, xPos.y);
		r.click();
	}
	
	public static void fake() throws InterruptedException {
		Thread.sleep(fakeInterval);
		r.keyPress(KeyEvent.VK_A);
		Thread.sleep(1000);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_D);
		Thread.sleep(1000);
		r.keyRelease(KeyEvent.VK_D);
	}
	
	public static void activate() throws InterruptedException {
		Thread.sleep(1000);
		r.keyType(KeyEvent.VK_SPACE);
		Thread.sleep(3000);
		r.keyType(KeyEvent.VK_SPACE);
		Thread.sleep(3000);
		r.keyType(KeyEvent.VK_1);
		Thread.sleep(3000);
	}
	
}
