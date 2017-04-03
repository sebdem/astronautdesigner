package me.sebdem.astronautdesigner.skin;

import java.awt.Color;
import java.awt.image.BufferedImage;

import me.sebdem.util.ColorableImage;

public class ColorableTexture extends Texture  {

	protected boolean colorable;
	
	public ColorableTexture(ColorableImage diffuse, BufferedImage emission) {
		super(diffuse, emission);
		//this.diffuse.update();
	}
	
	public void setTint(int tint){
		if(isColorable())
			((ColorableImage) this.diffuse).setTint(tint);
	}
	
	public void setColor(Color color){
		this.setTint(color.getRGB());
	}
	
	public Color getColor(){
		return ((ColorableImage) this.diffuse).getColor();
	}
	public int getTint(){
		return ((ColorableImage) this.diffuse).getTint();
	}

	public boolean isColorable(){
		return this.colorable;
	}
	public void setColorable(boolean colorable){
		this.colorable = colorable;
	}
	
	/**
	 * @param diffuse the diffuse to set
	 */
	@Override
	public void setDiffuse(BufferedImage diffuse) {
		this.diffuse = (ColorableImage) diffuse;
		update();
	}
	
	@Override
	public TextureType getType(){
		return TextureType.COLORABLE;
	}
	
	@Override
	public void update(){
		((ColorableImage) this.diffuse).update();
	}

}
