package ui.defaults;

import javax.swing.JFileChooser;

public class KFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	public KFileChooser() {
		super();
		init();
	}
	
	private void init() {
		setBackground(UI.fillColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
