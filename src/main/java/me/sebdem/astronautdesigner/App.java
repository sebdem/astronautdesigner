package me.sebdem.astronautdesigner;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import me.sebdem.astronautdesigner.gui.ADWindow;
import me.sebdem.astronautdesigner.skin.SkinFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
//        System.out.println( "Hello World!" );
//        URL url = Paths.get("resources/dave/PlayerMdl.mesh.xml").toUri().toURL();
//        System.out.println(url.getPath() );
//        Document doc = parse(url);
//        Element el = doc.getRootElement();
    	
//    	MeshFX mesh = ModelReader.read("PlayerMdl.mesh.xml");
//    	List<LayerSource> list = PartManager.instance.registry.getSources();
//    	for(LayerSource l : list) {
//    		System.out.println(l.name);
//    	}
//    	for(int i = 0; i < mesh.vertex.size() - 1; i += 3) {
//    		Vertex v1 = mesh.vertex.get(i),
//    		       v2 = mesh.vertex.get(i + 1),
//    		       v3 = mesh.vertex.get(i + 2);
//    		System.out.println(v1+ "\t\t" + v2 + "\t\t" + v3);
//    	}
    	SkinFile skin = SkinFile.createDefaultSkin();
    	ADWindow w = new ADWindow(skin).showDesigner();
    }
    
    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
}
