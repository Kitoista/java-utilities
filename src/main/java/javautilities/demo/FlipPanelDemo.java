package javautilities.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javautilities.ui.component.FlipPanel;
import javautilities.ui.component.displayer.BufferedImageDisplayer;
import javautilities.ui.frame.Show;

public class FlipPanelDemo {

	public static void main(String[] args) throws IOException {
		Show show = new Show();
		
		JPanel original = new JPanel();
		original.setBackground(Color.CYAN);
		original.setOpaque(true);
		JLabel label = new JLabel("NyomiKAH");
		BufferedImageDisplayer bid = new BufferedImageDisplayer();
		Component dewgong = bid.toComponent(ImageIO.read(new File("dewgong.jpg")), new Dimension(600, 600));
		original.add(dewgong);
		
		original.add(label);
		
		JPanel container = new JPanel();
		FlipPanel fp1 = new FlipPanel(original);
		FlipPanel fp2 = new FlipPanel(original);
		
		container.setLayout(null);
		original.setBounds(300, 0, 600, 300);
		fp1.setBounds(-300, 0, 600, 600);
		fp2.setBounds(900, 0, 300, 600);
		container.setPreferredSize(new Dimension(1200, 600));
		
		container.add(original);
		container.add(fp1);
		container.add(fp2);
		
		show.component(container);
		show.getFrame().getContentPane().addMouseMotionListener(new MouseMotionListener () {
			private int a = 1;
			public void mouseDragged(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				p.x -= original.getLocation().x + container.getParent().getLocation().x;
				p.y -= original.getLocation().y + container.getParent().getLocation().y;
				dewgong.setLocation(p);
				original.firePropertyChange("dewgong", a, 1-a);
			}
		});
	}
	
}
