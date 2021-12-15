package javautilities.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javautilities.image.Images;

public class ImageDemo {

	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read(new File("Treasures3.jpg"));
		
		List<BufferedImage> images = Images.splitImage(image, 228, 351, 17, 0);
		
		int i=62;
		for (BufferedImage img : images) {
			ImageIO.write(img, "png", new File("Treasure" + i++ + ".png"));
		}
		
	}
	
}
