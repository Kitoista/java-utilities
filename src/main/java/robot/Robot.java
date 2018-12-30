package robot;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Robot extends java.awt.Robot {

	public static final int TYPE_DELAY = 100;
	public static final int DOUBLE_CLICK_DELAY = 100;
	
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	public Robot(GraphicsDevice screen) throws AWTException {
		super(screen);
	}
	
	public Robot() throws AWTException {
		super();
	}
	
	public void click() {
		mousePress(InputEvent.BUTTON1_MASK);
		mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void doubleClick() {
		click();
		delay(DOUBLE_CLICK_DELAY);
		click();
	}

	public void clickOn(int x, int y) {
		mouseMove(x, y);
		click();
	}

	public void clickOn(Point point) {
		clickOn(point.x, point.y);
	}
	
	public void type(String txt) {
		for (char i = 0; i < txt.length(); i++) {
			charType(txt.charAt(i));
		}
	}
	
	public void charType(char c) {
		if (c >= 'A' && c <= 'Z') {
			keyPress(KeyEvent.VK_SHIFT);
			keyType(c);
			keyRelease(KeyEvent.VK_SHIFT);
			return;
		}
		if (c >= 'a' && c <= 'z') {
			c += 'A' - 'a';
			keyType(c);
			return;
		}
		if (c >= '0' && c <= '9') {
			keyType(c);
			return;
		}
		pressUnicode(c);
	}
	
	public void pressUnicode(int arg) {
		int key = arg;
		if (OS.contains("win")) {
			if (key == (int) 'õ') {
				key = 139;
			} else if (key == (int) 'Õ') {
				key = 138;
			} else if (key == (int) 'û') {
				key = 251;
			} else if (key == (int) 'Û') {
				key = 235;
			}
			// https://stackoverflow.com/questions/397113/how-to-make-the-java-awt-robot-type-unicode-characters-is-it-possible
			keyPress(KeyEvent.VK_ALT);
			for (int i = arg == key ? 3 : 2; i >= 0; --i) {
				// extracts a single decade of the key-code and adds
				// an offset to get the required VK_NUMPAD key-code
				int numpad_kc = key / (int) (Math.pow(10, i)) % 10 + KeyEvent.VK_NUMPAD0;
				keyType(numpad_kc);
			}
			keyRelease(KeyEvent.VK_ALT);
		} else {
			manyKeyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT, KeyEvent.VK_U);
			type(Integer.toHexString(key));
			keyType(KeyEvent.VK_ENTER);
		}
	}
	
	public void manyKeyPress(int... keys) {
		for (int i = 0; i < keys.length; i++) {
			keyPress(keys[i]);
		}
		for (int i = keys.length - 1; i >= 0; i--) {
			keyRelease(keys[i]);
		}
	}
	
	public void keyType(int keycode) {
		keyPress(keycode);
		delay(TYPE_DELAY);
		keyRelease(keycode);
	}
	
	public void delay(int ms) {
		if (ms == 0) return;
		super.delay(ms);
	}
}
