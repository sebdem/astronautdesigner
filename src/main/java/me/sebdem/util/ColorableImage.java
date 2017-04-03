package me.sebdem.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ColorableImage extends BufferedImage {

	private BufferedImage original;
	private int tint;
	
	public ColorableImage(int width, int height, int imageType) {
		super(width, height, imageType);
		this.tint = ColorUtils.TRANSPARENT_INT;
	}
	
	public ColorableImage(BufferedImage original){
		this(original.getWidth(), original.getHeight(), original.getType());
		this.original = original;
		this.update();
	}

	public ColorableImage(BufferedImage original, int tint) {
		this(original);
		this.setTint(tint);
	}

	public static ColorableImage fromResource(String resourcepath) throws MalformedURLException, IOException{
		return new ColorableImage(ImageIO.read(Paths.get("resources/"+resourcepath).toUri().toURL()));
	}
	public static ColorableImage fromResource(String resourcepath, int tint) throws MalformedURLException, IOException{
		return new ColorableImage(ImageIO.read(Paths.get("resources/"+resourcepath).toUri().toURL()), tint);
	}

	
	public BufferedImage update()
	{
		int channel = 3566399;
		int ocolor = channel;
		int tempcol = channel;

		Graphics2D g = this.createGraphics();
		g.setColor(ColorUtils.TRANSPARENT);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.dispose();
		
		for(int y = 0; y < this.getHeight(); y++)
		{	for(int x = 0; x < this.getWidth(); x++)
			{
				ocolor = channel;
				tempcol = 0;
				int tint = -1;

				ocolor = original.getRGB(x, y);
				if (!ColorUtils.isChannel(ocolor))
				{
					tempcol = ocolor;
					tint = this.tint;
				}
					
				if (!ColorUtils.isChannel(tempcol))
					this.setRGB(x, y, ((tint != -1 && tint != ColorUtils.TRANSPARENT_INT) ? ColorUtils.multiplyColor(tempcol, tint) : tempcol));
			}
		}
		return this;
	}
	
	
	
	/**
	 * @return the original
	 */
	public BufferedImage getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(BufferedImage original) {
		this.original = original;
		update();
	}

	/**
	 * @return the color
	 */
	public int getTint() {
		return tint;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return new Color(tint);
	}

	/**
	 * @param color the color to set
	 */
	public void setTint(int tint) {
		this.tint = tint;
		update();
	}
	public void setColor(Color color) {
		this.setTint(color.getRGB());
	}
	
}
