package javautilities.ui.frame;

import java.awt.Frame;

import javax.swing.JButton;

import javautilities.ui.defaults.KButton;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;

public class FrameHandler extends KPanel {

	private static final long serialVersionUID = 1L;

	public FrameHandler() {
		init();
	}
	
	public void init() {
		add(new KLabel("FrameHandler"));
		JButton list = new KButton("List");
		list.addActionListener(e -> {
			for (Frame frame : Frame.getFrames()) {
				System.out.println(frame);
			}
			System.out.println();
		});
		add(list);
		
		JButton showAll = new KButton("Show");
		showAll.addActionListener(e -> {
			for (Frame frame : Frame.getFrames()) {
				frame.setVisible(true);
			}
		});
		add(showAll);
	}
	
}
