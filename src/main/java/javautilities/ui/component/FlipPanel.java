package javautilities.ui.component;


import java.awt.Component;
import java.awt.Point;
import javax.swing.JPanel;

public class FlipPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	MirrorPanel mirror;

	public FlipPanel(Component original) {
		mirror = new MirrorPanel(original);
		setLayout(null);
		add(mirror);
	}
	
	public void setOffset(Point point) {
		mirror.setLocation(new Point(-point.x, -point.y));
	}
	
}
