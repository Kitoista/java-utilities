package javautilities.demo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javautilities.robot.Robot;
import javautilities.ui.frame.Show;
import winkeyboard.Keyboard;
import winkeyboard.ScanCode;

public class JustMove {
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		
//	    System.out.println(System.getProperty("java.library.path"));

		
		Show show = new Show();
		JLabel label = new JLabel("It should type in 2 seconds");
		show.component(label);
		show.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		show.getFrame().setSize(new Dimension(200, 100));
		show.getFrame().setAlwaysOnTop(true);
		
		Thread.sleep(2000);
		label.setText("Typing...");
		Keyboard keyboard = new Keyboard();
		for (int i = 0; i < 10; i++) {
			keyboard.winKeyPress(ScanCode.DIK_1 + i);
			Thread.sleep(100);
			keyboard.winKeyRelease(ScanCode.DIK_1 + i);
			Thread.sleep(100);
		}
		Thread.sleep(2000);
		label.setText("Typing left right up down");
		keyboard.winKeyPress(ScanCode.DIK_LEFT);
		Thread.sleep(100);
		keyboard.winKeyRelease(ScanCode.DIK_LEFT);
		Thread.sleep(100);
		keyboard.winKeyPress(ScanCode.DIK_RIGHT);
		Thread.sleep(100);
		keyboard.winKeyRelease(ScanCode.DIK_RIGHT);
		Thread.sleep(100);
		keyboard.winKeyPress(ScanCode.DIK_UP);
		Thread.sleep(100);
		keyboard.winKeyRelease(ScanCode.DIK_UP);
		Thread.sleep(100);
		keyboard.winKeyPress(ScanCode.DIK_DOWN);
		Thread.sleep(100);
		keyboard.winKeyRelease(ScanCode.DIK_DOWN);
		
		Thread.sleep(1000);
		label.setText("Alright, it's done. I hope it worked");
	}
	
}
