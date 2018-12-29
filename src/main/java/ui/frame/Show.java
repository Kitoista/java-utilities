package ui.frame;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.component.converter.BufferedImageDisplayer;

public class Show {

	private JFrame frame = new JFrame();
	private JPanel content = new JPanel();
	
	public Show() {
		init();
	}
	
	private void init() {
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200, 300);
		content.setLayout(new FlowLayout());
		frame.add(content);
		start();
		frame.setVisible(true);
	}
	
	private void start() {
		content.removeAll();
	}
	
	private void end() {
		frame.pack();
		frame.repaint();
	}
	
	public void refresh() {
		end();
	}
	
	public void image(BufferedImage img) {
		start();
		content.add(new BufferedImageDisplayer().toComponent(img, null));
		end();
	}
	
	public void image(String path) {
		try {
			image(ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void component(Component comp) {
		start();
		content.add(comp);
		end();
	}
	
	
	// getters, setters
	
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
