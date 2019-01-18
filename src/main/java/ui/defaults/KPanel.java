package ui.defaults;

import javax.swing.JPanel;

public class KPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public KPanel() {
		super();
		init();
	}
	
	private void init() {
		setBackground(UI.panelColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
