package javautilities.games.basic.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import javautilities.games.basic.core.Resource;
import javautilities.ui.defaults.KButton;
import javautilities.ui.defaults.KFrame;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;

public class DumbUI {

	public KFrame frame = new KFrame();
	KPanel content = new KPanel();
	
	KPanel northPanel = new KPanel();
	KPanel southPanel = new KPanel();
	KPanel eastPanel = new KPanel();
	KPanel westPanel = new KPanel();
	KPanel centerPanel = new KPanel();
	public KPanel camera = new KPanel();
	
	KButton[] characterButtons;
	public CharacterButtonListener characterButtonListener;
	
	String mobId = null;
	
	Map<String, KLabel> movingMobs = new HashMap<>();
	
	public DumbUI() {
		frame.setName("DumbUI");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 300);
		content.setLayout(new FlowLayout());
		
		frame.add(content);
		content.setLayout(new BorderLayout());
		content.add(northPanel, BorderLayout.NORTH);
		content.add(southPanel, BorderLayout.SOUTH);
		content.add(eastPanel, BorderLayout.EAST);
		content.add(westPanel, BorderLayout.WEST);
		content.add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.setLayout(null);
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		centerPanel.add(camera);
		camera.setSize(1000, 1000);
		
		frame.setVisible(true);
	}
	
	
	public void render(Object data) {
		if (data != null && data instanceof String) {
			String message = (String) data;
			String[] messages = message.split("\t");
			if (message.equals("")) {
				System.out.println("Got empty string for some reason");
			} else if (messages[0].equals("[init]")) {
				renderCharacterButtons(message);
			} else if (messages[0].equals("[map]")) {
				renderMap((String) data);
			} else if (messages[0].equals("[tick]")) {
				renderTick((String) data);
			} else if (messages[0].equals("[mobId]")) {
				this.mobId = messages[1];
			} else {
				System.out.println("sad but: " + message);
			}
			
		} else {
			camera.removeAll();
			
			camera.add(new KLabel(data.toString()));
			
			frame.pack();
			frame.repaint();
		}
	}
	
	protected void renderCharacterButtons(String message) {
		camera.removeAll();
		
		String[] parts = message.split("\t");
		
		KButton[] characterButtons = new KButton[parts.length - 1];
		
		for (int i = 1; i < parts.length; ++i) {
			String text = parts[i];
			KButton button = new KButton(text);
			characterButtons[i-1] = button;
			
			camera.add(button);
			characterButtons[i-1].addActionListener((e) -> {
				for (KButton characterButton : characterButtons) {
					for (ActionListener al : characterButton.getActionListeners()) {
						characterButton.removeActionListener(al);
					}
				}
				System.out.println("Chosen" + text);
				if (characterButtonListener != null) {
					characterButtonListener.onChoose(text);
				}
			});
		}
		
		frame.pack();
		frame.repaint();
	}
	
	protected void updateMob(KLabel mob, String[] text) {
		mob.setOpaque(true);
		
		mob.setBounds(
				(int) Float.parseFloat(text[1]),
				(int) Float.parseFloat(text[2]),
				(int) Float.parseFloat(text[3]),
				(int) Float.parseFloat(text[4])
			);
		
		if (text[0].equals(mobId)) {
			camera.setLocation(500 - mob.getLocation().x, 500 - mob.getLocation().y);
			System.out.println("ysssy");
		}
		
		if (text[5] != "null") {
			System.out.println(text[5] + " " + Boolean.parseBoolean(text[6]));
			mob.setIcon(Resource.getIcon(text[5], Boolean.parseBoolean(text[6])));
		} else {
			mob.setBackground(Color.magenta);
		}
	}
	
	protected void renderMap(String message) {
		camera.removeAll();
		camera.setLayout(null);
		camera.setPreferredSize(new Dimension(1000, 1000));
		
		System.out.println(message);
		
		String[] rows = message.split("\t");
		
		for (int i = 1; i < rows.length; ++i) {
			String[] text = rows[i].split(" ");
			KLabel wall = new KLabel("Wall" + Integer.parseInt(text[0]));
			
			updateMob(wall, text);
			
			camera.add(wall);
			System.out.println(wall);
		}
		
		frame.pack();
		frame.repaint();
	}
	
	protected void renderTick(String message) {
		System.out.println(message);
		
		String[] rows = message.split("\t");
		
		for (int i = 1; i < rows.length; ++i) {
			String[] text = rows[i].split(" ");
			KLabel mob;
			if (!movingMobs.containsKey(text[0])) {
				mob = new KLabel("Mob" + Integer.parseInt(text[0]));
				movingMobs.put(text[0], mob);
				camera.add(mob);
			} else {
				mob = movingMobs.get(text[0]);
			}
			
			updateMob(mob, text);

			// System.out.println(mob);
		}
		
		frame.pack();
		frame.repaint();
	}
	
}
