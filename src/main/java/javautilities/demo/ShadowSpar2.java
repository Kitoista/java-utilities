package javautilities.demo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javautilities.robot.Robot;
import javautilities.ui.component.displayer.BufferedImageDisplayer;
import javautilities.ui.frame.Show;

public class ShadowSpar2 {

	public Robot robot;
	
	public Point position = new Point(745, 511);
	public Dimension size = new Dimension(40, 40);
	public int pause = 1000;
	
	public boolean isPlaying = false;
	
	protected JTextArea logs = new JTextArea();
	protected JScrollPane scrollPane;
	
	protected BufferedImage left;
	protected BufferedImage right;
	protected BufferedImage top;
	protected BufferedImage bottom;
	
	protected Rectangle leftRect;
	protected Rectangle rightRect;
	protected Rectangle topRect;
	protected Rectangle bottomRect;
	
	protected JLabel leftLog;
	protected JLabel rightLog;
	protected JLabel topLog;
	protected JLabel bottomLog;
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		ShadowSpar2 ss2 = new ShadowSpar2();
		
		ss2.start();
	}
	
	public ShadowSpar2() throws AWTException {
		robot = new Robot();
	}
	
	
	public void start() throws InterruptedException {
		Random rand = new Random();
		BufferedImageDisplayer bid = new BufferedImageDisplayer();

		JPanel bigPanel = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 3));
		
		panel.add(new JLabel("Postion"));
		JTextField x = new JTextField();
		JTextField y = new JTextField();
		panel.add(x);
		panel.add(y);
		
		panel.add(new JLabel("Size"));
		JTextField width = new JTextField();
		JTextField height = new JTextField();
		panel.add(width);
		panel.add(height);
		
		panel.add(new JLabel("Pause"));
		JTextField pauseField = new JTextField();
		pauseField.setText("500");
		panel.add(pauseField);
		
		JButton button = new JButton("Start");
		panel.add(button);
		
		
		JPanel logPanel = new JPanel();
		
		logs.setEditable(false);
		scrollPane = new JScrollPane(logs);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new GridLayout(3, 3));
		
		logPanel.add(scrollPane);
		logPanel.add(imgPanel);
		
		Show show = new Show();
		bigPanel.add(panel);
		bigPanel.add(logPanel);
		show.component(bigPanel);
		show.getFrame().setAlwaysOnTop(true);
		show.getFrame().setTitle("Shadow Spar 2 by Migiri");
		show.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(e -> {
			isPlaying = !isPlaying;
			if (isPlaying) {
				button.setText("Stop");
			} else {
				button.setText("Start");
			}
		});
		
		while (true) {
			while(!isPlaying) {
				Thread.sleep(100);
			}
			
			position.x = Integer.parseInt(x.getText());
			position.y = Integer.parseInt(y.getText());
			size.width = Integer.parseInt(width.getText());
			size.height = Integer.parseInt(height.getText());
			pause = Integer.parseInt(pauseField.getText());
			
			leftRect = new Rectangle(position.x - size.width / 2, position.y, size.width / 2, size.height / 2);
			rightRect = new Rectangle(position.x + size.width * 3 / 2, position.y, size.width / 2, size.height / 2);
			topRect = new Rectangle(position.x, position.y - size.height / 2, size.width / 2, size.height / 2);
			bottomRect = new Rectangle(position.x, position.y + size.height * 3 / 2, size.width / 2, size.height / 2);
			
			left = robot.createScreenCapture(leftRect);
			right = robot.createScreenCapture(rightRect);
			top = robot.createScreenCapture(topRect);
			bottom = robot.createScreenCapture(bottomRect);
			
			imgPanel.removeAll();
			
			imgPanel.add(new JLabel());
			imgPanel.add(bid.toComponent(top));
			imgPanel.add(new JLabel());
			imgPanel.add(bid.toComponent(left));
			imgPanel.add(new JLabel());
			imgPanel.add(bid.toComponent(right));
			imgPanel.add(new JLabel());
			imgPanel.add(bid.toComponent(bottom));
			imgPanel.add(new JLabel());
			
			show.getFrame().pack();
			
			String[] str = {"left", "right", "top", "bottom"};
			int[] keys = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
			
			while (isPlaying) {
				BufferedImage newLeft = robot.createScreenCapture(leftRect);
				BufferedImage newRight = robot.createScreenCapture(rightRect);
				BufferedImage newTop = robot.createScreenCapture(topRect);
				BufferedImage newBottom = robot.createScreenCapture(bottomRect);
				
				double[] diffs = {
						diff(left, newLeft),
						diff(right, newRight),
						diff(top, newTop),
						diff(bottom, newBottom)
				};
				
				int bestI = -1;
				double bestDiff = 0; 
				
				for (int i = 0; i < diffs.length; i++) {
					if (diffs[i] > bestDiff) {
						bestDiff = diffs[i];
						bestI = i;
					}
				}
				
				if (bestI != -1) {
					log(str[bestI]);
					robot.keyPress(keys[bestI]);
					Thread.sleep(50);
					robot.keyRelease(keys[bestI]);
				} else {
					log("Nothing changed");
				}
				
				Thread.sleep((long) Math.floor((rand.nextFloat() / 2 + 0.75) * pause));
			}
		}
		
		
	}
	
	public double diff(BufferedImage original, BufferedImage newImage) {
		double diff = 0;
		for (int i = 0 ; i < size.width / 2; ++i) {
			for (int j = 0 ; j < size.height / 2; ++j) {
				Color oColor = new Color(original.getRGB(i, j));
				Color nColor = new Color(newImage.getRGB(i, j));
				
				diff += Math.abs(oColor.getRed() - nColor.getRed());
				diff += Math.abs(oColor.getGreen() - nColor.getGreen());
				diff += Math.abs(oColor.getBlue() - nColor.getBlue());
			}
		}
		return diff;
	}
	
	public void log(Object obj) {
		logs.append(obj.toString() + "\n");
		if (scrollPane != null) {
			JScrollBar vertical = scrollPane.getVerticalScrollBar();
			vertical.setValue( vertical.getMaximum() );
		}
	}
	
}
