package me.sebdem.astronautdesigner.layers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("restriction")
@XmlType
public class LayerSource{


	@XmlAttribute
	public String name;

	@XmlAttribute
	public boolean emission;
	
	@XmlAttribute
	public boolean colorable;

	@XmlTransient
	public BufferedImage texture;

	@XmlTransient
	public BufferedImage emission_overlay;
	
	public LayerSource() {
		this.name = "";
		this.emission = false;
	}
	

	public LayerSource(String name, String emission) {
		super();
		System.out.println("Setting Source; " + name); 
		this.name = name;
		this.emission = emission.equalsIgnoreCase("true") ;
		try {
			this.texture = ImageIO.read(Paths.get("resources/parts/"+name+"_diff.png").toFile());
			if(this.emission) {
				this.emission_overlay = ImageIO.read(Paths.get("resources/parts/"+name+"_em.png").toFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	

}
