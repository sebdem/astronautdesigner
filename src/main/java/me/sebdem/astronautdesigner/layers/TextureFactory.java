package me.sebdem.astronautdesigner.layers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import me.sebdem.astronautdesigner.skin.ColorableTexture;
import me.sebdem.astronautdesigner.skin.ComposedTexture;
import me.sebdem.astronautdesigner.skin.ITexture;
import me.sebdem.astronautdesigner.skin.Texture;
import me.sebdem.astronautdesigner.skin.TextureType;
import me.sebdem.util.ColorableImage;

@XmlRootElement(name = "source")
public class TextureFactory {

	/*
	 * @XmlElements({
	 * 
	 * @XmlElement(name="colorableTexture", type=ColorableTexture.class),
	 * 
	 * @XmlElement(name="composedTexture", type=ComposedTexture.class),
	 * 
	 * @XmlElement(name="texture", type=Texture.class) })
	 */
	@XmlElementWrapper(name = "layers")
	@XmlElement(name = "source")
	public List<TextureFactory> layers = new ArrayList<TextureFactory>();

	@XmlAttribute
	public String name;

	@XmlAttribute
	public boolean emission;

	@XmlAttribute(required = false)
	public boolean colorable;

	@XmlAttribute(required = false)
	public int tint;

	private TextureType textureType;

	public TextureFactory() {
		this.name = "";
		this.emission = false;
		this.colorable = false;
		this.tint = 0;
		textureType = null;
	}

	public TextureFactory(String name, String emission, String colorable, String tint) {
		super();
		System.out.printf("Setting Source; %s %s %s %s \n", name, emission, colorable, tint);
		this.name = name;
		this.emission = emission.equalsIgnoreCase("true");
		this.colorable = colorable.equalsIgnoreCase("true");

		if (tint != null && !tint.isEmpty())
			this.tint = Integer.parseInt(tint);
		checkTextureType();

	}

	public TextureType getType() {
		return (this.textureType != null) ? this.textureType : checkTextureType();
	}

	public TextureType checkTextureType() {
		System.out.println("checking " + this.toString());
		if (this.layers != null && this.layers.size() > 0) {
			System.out.println("COMPOSED");
			this.textureType = TextureType.COMPOSED;
		} else if (isColored()) {
			this.textureType = TextureType.COLORABLE;
		} else {
			this.textureType = TextureType.SIMPLE;
		}
		return textureType;
	}

	private boolean isColored() {
		return (this.colorable || (this.tint > 0));
	}

	public ITexture create() {
		System.out.println("Creating " + this.toString());

		ITexture texture = null;

		String filepath = "resources/parts/" + name;
		try {
			if (this.getType() == TextureType.COMPOSED) {
				texture = new ComposedTexture();
				for (TextureFactory tfac : this.layers) {
					((ComposedTexture) texture).addLayer(tfac.create());
				}
			} else {
				System.out.println("Reading: " + filepath + ".png");
				BufferedImage diff = ImageIO.read(Paths.get(filepath + ".png").toFile());
				BufferedImage em = (this.emission) ? ImageIO.read(Paths.get(filepath + "_em.png").toFile()) : null;
				if (isColored()) {
					texture = new ColorableTexture(
							(this.tint > 0) ? new ColorableImage(diff, this.tint) : new ColorableImage(diff), em);
					((ColorableTexture) texture).setColorable(this.colorable);
					System.out.println("Texture can be Colored" + (texture));
				} else {
					texture = new Texture(diff, em);
					System.out.println("Texture can not be altered " + (texture));
				}
			}

		} catch (IOException e) {
			System.out.printf("Failed To Read: \"%s\"\n", filepath);
			e.printStackTrace();
		}

		return texture;
	}

	public TextureFactory(String name, String emission, String colorable) {

		this(name, emission, colorable, null);
	}

	public String toString() {
		return String.format("Source; %s %s %s %s %s %s", name, String.valueOf(emission), String.valueOf(colorable),
				String.valueOf(tint), String.valueOf(this.layers.size()), this.textureType);
	}
}
