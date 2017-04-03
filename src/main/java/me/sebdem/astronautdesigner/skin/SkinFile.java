package me.sebdem.astronautdesigner.skin;

import java.awt.Color;
import java.io.File;
import me.sebdem.astronautdesigner.SourceRegistry;
import me.sebdem.astronautdesigner.layers.TextureFactory;
import me.sebdem.util.ColorUtils;
import me.sebdem.util.RandomUtil;

public class SkinFile {

	public enum SkinSection {
		BODY, HELMET
	}
	
	public SkinFile(){
		this.body = new ComposedTexture();
		this.helmet = new ComposedTexture();
	}
	
	public ComposedTexture body;
	public ComposedTexture helmet;
	
	
	public void set(SkinSection at, Object... params){
		switch(at){
		case BODY: 
			ITexture t = body.getLayer((int)(Math.random() * body.getLayers().size()));
			Color[] colors = ColorUtils.createColorArray(32);
			if (t instanceof ComposedTexture){
				ComposedTexture cmpt = (ComposedTexture)t;
				for(ITexture lt : cmpt.getLayers()){
					if (lt instanceof ColorableTexture){
						((ColorableTexture)lt).setColor( RandomUtil.arrayRandomItem(colors) );
						if(RandomUtil.randomBool(30))
							break;
					}
				}
				
			} else if (t instanceof ColorableTexture){
				((ColorableTexture)t).setColor( RandomUtil.arrayRandomItem(colors) );
			}
			t.update();
			body.update();
			break;
		case HELMET: ;break;
		}
	}
	
	
	public static SkinFile createDefaultSkin(){
		SkinFile f = new SkinFile();
		
		try {
			//f.body.addLayer(new Texture(ImageIO.read(new File("skins/CorpusIndustries-Skins/0_Crewmans/Complete Skin/Crewman.png"))));
			TextureFactory body = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/body/default.smadxml"));
			f.body.addLayer(body.create());
			
			TextureFactory face = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/face/default.smadxml"));
			f.body.addLayer(face.create());

			TextureFactory hair = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/hair/default_hair.smadxml"));
			f.body.addLayer(hair.create());
			
			TextureFactory suit = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/suit/default.smadxml"));
			f.body.addLayer(suit.create());
			
			TextureFactory light = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/suit/default_light.smadxml"));
			f.body.addLayer(light.create());
			
			TextureFactory gloves = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/suit/default_gloves.smadxml"));
			f.body.addLayer(gloves.create());
			

			TextureFactory helmet = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/helmet/default_glass.smadxml"));
			f.helmet.addLayer(helmet.create());
			helmet = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/helmet/default.smadxml"));
			f.helmet.addLayer(helmet.create());
			helmet = SourceRegistry.loadFromRegXMLFile(new File("resources/parts/helmet/default_lights.smadxml"));
			f.helmet.addLayer(helmet.create());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
}
