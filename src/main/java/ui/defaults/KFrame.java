package ui.defaults;

import javax.swing.JFrame;

public class KFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public KFrame() {
		super();
		init();
	}
	
	private void init() {
		getContentPane().setBackground(UI.panelColor);
		getContentPane().setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
