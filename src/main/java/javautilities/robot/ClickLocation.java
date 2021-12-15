package javautilities.robot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;
import javautilities.ui.frame.Show;

public class ClickLocation {

	public static KLabel value = new KLabel("[0, 0]");
	
	public static Robot robot;
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		Show show = new Show();
		KPanel panel = new KPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(new KLabel("Hello, this is Migiri's mouse location tracker."));
		panel.add(value);

		show.component(panel);
		show.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		show.getFrame().setTitle("Mouse location");
		show.getFrame().setAlwaysOnTop(true);
		
		while(true) {
			Point a = MouseInfo.getPointerInfo().getLocation();
			value.setText("Mouse position: [" + a.x + ", " + a.y + "]");
			Thread.sleep(100);
		}
	}
	
}
