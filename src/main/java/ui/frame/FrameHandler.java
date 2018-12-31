package ui.frame;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameHandler extends JPanel {

	private static final long serialVersionUID = 1L;

	public FrameHandler() {
		init();
	}
	
	public void init() {
		add(new JLabel("FrameHandler"));
		JButton list = new JButton("List");
		list.addActionListener(e -> {
			for (Frame frame : Frame.getFrames()) {
				System.out.println(frame);
			}
			System.out.println();
		});
		add(list);
		
		JButton showAll = new JButton("Show");
		showAll.addActionListener(e -> {
			for (Frame frame : Frame.getFrames()) {
				frame.setVisible(true);
			}
		});
		add(showAll);
	}
	
}
