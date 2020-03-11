package javautilities.ui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;

import javautilities.geometry.Dimensions;
import javautilities.ui.defaults.KFrame;

public class Frames {

	public static Dimension getFrameBorderSize(JFrame frame) {
		return Dimensions.sub(frame.getSize(), frame.getContentPane().getSize());
	}
	
	public static Dimension getFrameBorderSize() {
		JFrame frame = new KFrame();
		frame.setName("Frames.getFrameBorderSize");
		frame.pack();
		Dimension re = getFrameBorderSize(frame);
		frame.dispose();
		return re;
	}
	
	public static Dimension getMaximalizedSize() {
		JFrame frame = new JFrame();
		frame.setName("Frames.getMaximalizedSize");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setVisible(false);
		Dimension re = frame.getSize();
		frame.dispose();
		return re;
	}

}
