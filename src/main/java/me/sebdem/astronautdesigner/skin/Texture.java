package me.sebdem.astronautdesigner.skin;

import java.awt.image.BufferedImage;

public class Texture implements ITexture{

	protected BufferedImage diffuse;
	protected BufferedImage emission;
	/**
	 * @return the diffuse
	 */
	public BufferedImage getDiffuse() {
		return diffuse;
	}
	/**
	 * @param diffuse the diffuse to set
	 */
	public void setDiffuse(BufferedImage diffuse) {
		this.diffuse = diffuse;
	}
	/**
	 * @return the emission
	 */
	public BufferedImage getEmission() {
		return emission;
	}
	/**
	 * @param emission the emission to set
	 */
	public void setEmission(BufferedImage emission) {
		this.emission = emission;
	}
	
	public Texture(BufferedImage diffuse, BufferedImage emission) {
		super();
		this.diffuse = diffuse;
		this.emission = emission;
	}
	public Texture(BufferedImage diffuse) {
		this(diffuse, null);
	}
	public Texture copy() {
		return new Texture(this.getDiffuse(), this.getEmission());
	}
	
	@Override
	public TextureType getType() {
		return TextureType.SIMPLE;
	}
	@Override
	public void update() {
	}
	
	
	
	
}
