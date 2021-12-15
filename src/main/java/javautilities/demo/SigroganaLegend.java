package javautilities.demo;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;

import javautilities.robot.Robot;
import javautilities.ui.component.displayer.BufferedImageDisplayer;
import javautilities.ui.defaults.KButton;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;
import javautilities.ui.frame.Show;

public class SigroganaLegend {

	static Robot r;
	
	static String filename = "SigroganaLegend.txt";
	
	static Point vert = new Point(470, 400);
	static Point vertVect = new Point(-25, 210);
	
	static Point hor = new Point(350, 492);
	static Point horVect = new Point(250, 30);
	
	static Point diag = new Point(350, 420);
	static Point diagVect = new Point(198, 165);
	
	static int time = 50;
	static int mouseSteps = 50;
	
	public static void main(String[] args) throws AWTException, IOException {
		r = new Robot();
		
		init();
		
		System.out.println(time);
		System.out.println(mouseSteps);
		
		KPanel panel = new KPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		KPanel textPanel = new KPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(new KLabel("Hello this is Migiri's Sigrogana Legend 2 for Divine eyes."), BorderLayout.NORTH);
		textPanel.add(new KLabel("To change the settings, override the SigroganaLegend.txt"), BorderLayout.SOUTH);
		panel.add(textPanel);
		
		Dimension size = new Dimension(150, 150);
		Component vectImage = new BufferedImageDisplayer().toComponent(ImageIO.read(new File("vect.png")), size);
		Component horImage = new BufferedImageDisplayer().toComponent(ImageIO.read(new File("hor.png")), size);
		Component diagImage = new BufferedImageDisplayer().toComponent(ImageIO.read(new File("diag.png")), size);
		
		KPanel buttons = new KPanel();
		
		KButton vertButton = new KButton();
		KButton horButton = new KButton();
		KButton diagButton = new KButton();
		
		vertButton.add(vectImage);
		horButton.add(horImage);
		diagButton.add(diagImage);
		
		buttons.add(vertButton);
		buttons.add(horButton);
		buttons.add(diagButton);

		panel.add(buttons);

		Show show = new Show();
		show.component(panel);
		show.getFrame().setAlwaysOnTop(true);
		
		vertButton.addActionListener(e -> {
			drawLine(vert, vertVect);
		});
		
		horButton.addActionListener(e -> {
			drawLine(hor, horVect);
		});
		
		diagButton.addActionListener(e -> {
			drawLine(diag, diagVect);
		});
		
	}

	public static void drawLine(Point pos, Point vect) {
		Point original = MouseInfo.getPointerInfo().getLocation();
		r.mouseInterpolate(original, pos, 50, 50);
		r.drag(pos, new Point(pos.x + vect.x, pos.y + vect.y), time, mouseSteps);
		r.mouseMove(original.x, original.y);
	}

	private static void init() throws IOException {
		File file = new File(filename);
		if (!file.isFile()) {
			file.createNewFile();
		}
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals("")) continue;
			if (line.charAt(0) == '#') continue;
			String[] parts = line.split(" ");
			if (parts.length > 0) {
				switch (parts[0]) {
				case "time": {
					time = Integer.parseInt(parts[1]); 
					break;
				}
				case "mouseSteps": {
					mouseSteps = Integer.parseInt(parts[1]); 
					break;
				}
				case "vertical": {
					vert = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "verticalVector": {
					vertVect = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "horizontal": {
					hor = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "horizontalVector": {
					horVect = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "diagonal": {
					diag = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				case "diagonalVector": {
					diagVect = new Point(
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[2])
						); 
					break;
				}
				}
			}
		}
		scanner.close();
	}
	
}
