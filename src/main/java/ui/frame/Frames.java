package ui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;

import geometry.Dimensions;

public class Frames {

	public static Dimension getFrameBorderSize(JFrame frame) {
		return Dimensions.sub(frame.getSize(), frame.getContentPane().getSize());
	}
	
	public static Dimension getFrameBorderSize() {
		JFrame frame = new JFrame();
		frame.pack();
		return getFrameBorderSize(frame);
	}
	
	public static Dimension getMaximalizedSize() {
		JFrame frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setVisible(false);
		return frame.getSize();
	}

}
