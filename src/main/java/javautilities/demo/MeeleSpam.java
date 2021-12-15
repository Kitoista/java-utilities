package javautilities.demo;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import javautilities.robot.Robot;

public class MeeleSpam {

	public static void main(String[] args) {
		Robot r;
		try {
			r = new Robot();
			r.manyKeyPress(KeyEvent.VK_ALT, KeyEvent.VK_TAB);
			r.delay(400);
			Robot.TYPE_DELAY = 5;
			for (int i=0;i<5000;++i) {
				r.keyType(KeyEvent.VK_X);
				r.keyType(KeyEvent.VK_S);
				r.keyType(KeyEvent.VK_C);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
