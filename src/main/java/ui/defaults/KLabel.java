package ui.defaults;

import javax.swing.JLabel;

public class KLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public KLabel() {
		super();
		init();
	}
	
	public KLabel(String text) {
		super(text);
		init();
	}
	
	protected void init() {
		setBackground(UI.fillColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
