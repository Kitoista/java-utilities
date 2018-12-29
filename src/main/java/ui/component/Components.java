package ui.component;

import java.awt.Component;

import javax.swing.JFrame;

public class Components {

	public static JFrame getParentFrame(Component component) {
		Component parent = component.getParent();
		while(parent != null && !(parent instanceof JFrame)) {
			parent = parent.getParent();
		}
		return (JFrame) parent;
	}
	
}
