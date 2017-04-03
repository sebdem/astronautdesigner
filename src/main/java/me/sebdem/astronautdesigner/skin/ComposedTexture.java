package me.sebdem.astronautdesigner.skin;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.sebdem.util.ColorUtils;

public class ComposedTexture implements ITexture {

	private ArrayList<ITexture> layers = new ArrayList<ITexture>();
	
	protected BufferedImage diffuse;
	protected BufferedImage emission;
	
	public ComposedTexture(){
		super();
	}
	public ComposedTexture(ArrayList<ITexture> layers){
		this();
		this.layers = layers; 
		// TODO Layers in ComposedTexture Constructor
	}
	
	public ITexture getLayer(int index){
		return layers.get(index);
	}
	public  ArrayList<ITexture> getLayers(){
		return layers;
	}
	public void addLayer(ITexture layer){
		layers.add(layer);
	}
	public void addLayer(int index, ITexture layer){
		layers.add(index, layer);
	}
	public void setLayer(int index, ITexture layer){
		layers.set(index, layer);
	}
	public ITexture removeLayer(int index){
		return layers.remove(index);
	}
	public boolean removeLayer(ITexture layer){
		return layers.remove(layer);
	}
	
	
	public BufferedImage getDiffuse() {
		if (diffuse == null){
			// TODO Diffuse Generierung für ComposedTexture
			this.update();
		}
		return diffuse;
	}

	public void setDiffuse(BufferedImage diffuse) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(){
		this.diffuse = new BufferedImage(256,256, BufferedImage.TYPE_INT_ARGB);
		this.emission = new BufferedImage(256,256, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D gd = this.diffuse.createGraphics(), ge = this.emission.createGraphics();
		gd.setClip(0, 0, 256, 256);
		ge.setClip(0, 0, 256, 256);
//		clearGraphic(gd); 
//		clearGraphic(ge);

		for(ITexture tex : this.layers){
			BufferedImage dif = tex.getDiffuse(), em = tex.getEmission();
			if(dif != null)
				gd.drawImage(dif, 0, 0, this.diffuse.getWidth(), this.diffuse.getHeight(), null);
			if(em != null)
				ge.drawImage(em, 0, 0, this.emission.getWidth(), this.emission.getHeight(), null);
		}
		gd.dispose();
		ge.dispose();
	}
	
	@SuppressWarnings("unused")
	private void clearGraphic(Graphics2D g){
		g.setColor(ColorUtils.TRANSPARENT);
		java.awt.Rectangle rec = g.getClipBounds();
		System.out.println(g);
		g.clearRect(0, 0, 
				(int)rec.getWidth(), 
				(int)rec.getHeight());
	}

	public BufferedImage getEmission() {

		if (emission == null){
			// TODO Emission Generierung für ComposedTexture
			this.update();
		}
		return emission;
	}


	public void setEmission(BufferedImage emission) {
		// TODO Auto-generated method stub
		
	}

	public ITexture copy() {
		return new ComposedTexture();
	}
	@Override
	public TextureType getType() {
		return TextureType.COMPOSED;
	}

}
