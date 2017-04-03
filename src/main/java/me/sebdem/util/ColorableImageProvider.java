package me.sebdem.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ColorableImageProvider extends BufferedImage {

	
	public ColorableImageProvider(int width, int height, int imageType) {
		super(width, height, imageType);
	}

	public ColorableImage createInstance(){
		return new ColorableImage(this);
	}
	public ColorableImage createInstance(int initialTint){
		return new ColorableImage(this, initialTint);
	}
	
	public static ColorableImageProvider fromResource(String resourcepath, int tint) throws MalformedURLException, IOException{
		return (ColorableImageProvider)ImageIO.read(Paths.get("resources/"+resourcepath).toUri().toURL());
	}
}
