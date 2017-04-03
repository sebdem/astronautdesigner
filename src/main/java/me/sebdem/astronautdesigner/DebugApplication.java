package me.sebdem.astronautdesigner;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import me.sebdem.astronautdesigner.layers.TextureFactory;
import me.sebdem.astronautdesigner.skin.ColorableTexture;
import me.sebdem.astronautdesigner.skin.ComposedTexture;
import me.sebdem.astronautdesigner.skin.ITexture;
import me.sebdem.util.RandomUtil;

public class DebugApplication {

	public static void main(String[] args) throws IOException {

		List<TextureFactory> textures = new ArrayList<TextureFactory>();
		List<File> filesInFolder = Files.walk(Paths.get("resources/parts/"))
                .filter((Path file)->{ return file.toString().endsWith(".smadxml"); })
                .map(Path::toFile)
                .collect(Collectors.toList());
		
		filesInFolder.forEach((item)->{textures.add(loadFromRegXMLFile(item));});
		
		System.out.println(filesInFolder.size() + " " +textures.size());
		
		int index = 0;
		
		
			for(int i = 0; i < 6; i++, index++)
			{
				ComposedTexture comp = new ComposedTexture();
				
				for(TextureFactory t : textures) 
				{
					ITexture texture = t.create();
					if (texture instanceof ComposedTexture){
						colorTextureLayers((ComposedTexture)texture);
					}
					texture.update();
					comp.addLayer(texture);

					File output = new File(Paths.get("results/tests").toString() +"/"+t.name+"_" + i + "_" + index + ".png");
					output.createNewFile();
					ImageIO.write(texture.getDiffuse(), "png", output);
				}
				comp.update();

				File output = new File(Paths.get("results/comp").toString() +"/composed_" + i + "_" + index + ".png");
				output.createNewFile();
				ImageIO.write(comp.getDiffuse(), "png", output);
			}
		


	}

	private static void colorTextureLayers(ComposedTexture texture){
		for(ITexture ctext: texture.getLayers())
		{	
			if (ctext instanceof ComposedTexture){
				colorTextureLayers((ComposedTexture) ctext);
			}
			else if (ctext instanceof ColorableTexture && ((ColorableTexture) ctext).isColorable()){
				setRandomColorForTexture((ColorableTexture) ctext);
			}
		}
		texture.update();
	}
	
	private static void setRandomColorForTexture(ColorableTexture texture){
		texture.setTint(
				new Color(
						RandomUtil.randomIntAround(RandomUtil.randomIntAround(120, 120), 15, 120),
						RandomUtil.randomIntAround(RandomUtil.randomIntAround(120, 120), 15, 120),
						RandomUtil.randomIntAround(RandomUtil.randomIntAround(120, 120), 15, 120),
					(int)( 255)
				).getRGB());
	}
	
	
	public static TextureFactory loadFromRegXMLFile(File file){
		
		TextureFactory texture = new TextureFactory();
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(TextureFactory.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();

			//URL filePath = Paths.get("resources/partRegistry.xml").toUri().toURL();
			
			texture = ((TextureFactory)unMarshaller.unmarshal(file));
			
			System.out.printf("Has Attributes: %s - %s - %s \n", texture.name ,texture.emission, texture.colorable);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (InvalidPathException e) {
			e.printStackTrace();
		} 
		return texture;
	}
}
