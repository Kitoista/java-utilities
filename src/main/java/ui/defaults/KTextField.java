package ui.defaults;

import javax.swing.JTextField;

public class KTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	
	public KTextField() {
		super();
		init();
	}
	
	public KTextField(String text) {
		super(text);
		init();
	}
	
	private void init() {
		setBackground(UI.panelColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
