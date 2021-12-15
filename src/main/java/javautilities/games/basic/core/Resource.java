package javautilities.games.basic.core;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resource {

	public static final String FOLDER = "game/basic/";
	
	static Map<String, ImageIcon> icons = new HashMap<>();
	
	public static ImageIcon getIcon(String name, boolean flipped) {
		if (name == null || name.equals("null")) {
			return null;
		}
		String mapName = flipped ? "_" + name : name;
		if (icons.containsKey(mapName)) {
			return icons.get(mapName);
		}
		ImageIcon re;
		if (!flipped) {
			re = new ImageIcon(FOLDER + "icons/" + name + ".png");
		} else {
			re = loadFlipIcon(name);
		}
		icons.put(mapName, re);
		return re;
	}
	
	public static ImageIcon loadFlipIcon(String name) {
		try {
			System.out.println(name);
			BufferedImage bi = ImageIO.read(new File(FOLDER + "icons/" + name + ".png"));
			
			BufferedImage flipped = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
			AffineTransform tran = AffineTransform.getTranslateInstance(bi.getWidth(), 0);
			AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
			tran.concatenate(flip);

			Graphics2D g = flipped.createGraphics();
			g.setTransform(tran);
			g.drawImage(bi, 0, 0, null);
			g.dispose();

			return new ImageIcon(flipped);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
