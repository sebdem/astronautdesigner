package me.sebdem.astronautdesigner;

import java.io.File;
import java.nio.file.InvalidPathException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import me.sebdem.astronautdesigner.layers.TextureFactory;

public class SmadxmlLoader {

	
	@SuppressWarnings("unchecked")
	public static <E> E loadGeneric(File file){
		E object = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(TextureFactory.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();
			object = ((E)unMarshaller.unmarshal(file));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (InvalidPathException e) {
			e.printStackTrace();
		} 
		return object;
	}
}
