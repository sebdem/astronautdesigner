package me.sebdem.astronautdesigner.skin;

import java.awt.image.BufferedImage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public interface ITexture {
	public BufferedImage getDiffuse();
	public void setDiffuse(BufferedImage diffuse);
	
	public BufferedImage getEmission();
	public void setEmission(BufferedImage emission);
	
	
	public ITexture copy();
	
	public TextureType getType();
	
	public void update();
}
