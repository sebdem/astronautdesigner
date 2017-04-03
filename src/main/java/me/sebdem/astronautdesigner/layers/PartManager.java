package me.sebdem.astronautdesigner.layers;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import me.sebdem.astronautdesigner.view3d.MeshFX;

@SuppressWarnings("restriction")
public class PartManager {
	
	public static PartManager instance = new PartManager();

	public SourceRegistry registry;
	
	private PartManager() {
		loadFromRegXMLFile();
	}
	
	

	public void loadFromRegXMLFile(){
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(SourceRegistry.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();

			URL filePath = Paths.get("resources/partRegistry.xml").toUri().toURL();
			
			registry = (SourceRegistry)unMarshaller.unmarshal(filePath);
			System.out.println("Success:" + registry.getSources().size());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	
}
