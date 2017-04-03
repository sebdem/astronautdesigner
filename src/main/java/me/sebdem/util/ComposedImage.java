package me.sebdem.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ComposedImage extends BufferedImage {

	private ArrayList<BufferedImage> layers = new ArrayList<BufferedImage>();
	
	public ComposedImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}

	/**
	 * @return the layers
	 */
	public ArrayList<BufferedImage> getLayers() {
		return layers;
	}

	/**
	 * @param layers the layers to set
	 */
	public void setLayers(ArrayList<BufferedImage> layers) {
		this.layers = layers;
	}

	public void addLayer(BufferedImage layer){
		this.layers.add(layer);
	}
	public BufferedImage update()
	{
		Graphics2D g = this.createGraphics();
		g.setColor(ColorUtils.TRANSPARENT);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		for(BufferedImage img : layers){
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		g.dispose();
		return this;
	}
	
}
