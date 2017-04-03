package me.sebdem.astronautdesigner.view3d;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ModelReader {


	public static MeshFX read(String model) {

		MeshFX msh = new MeshFX();
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(MeshFX.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();

			URL filePath = Paths.get("resources/models/"+model).toUri().toURL();
			
			msh = (MeshFX) unMarshaller.unmarshal(filePath);
			msh.convert();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msh;
	}
}
