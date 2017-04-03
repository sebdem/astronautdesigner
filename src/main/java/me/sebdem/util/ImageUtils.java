package me.sebdem.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public final static Color TRANSPARENT = new Color(0,0,0,0);
	
	public static BufferedImage clear(BufferedImage image, Color clearColor){
		Graphics2D g = image.createGraphics();
		g.setColor(clearColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.dispose();
		return image;
	}
	public static BufferedImage clear(BufferedImage image){
		return clear(image, TRANSPARENT);
	}
}
