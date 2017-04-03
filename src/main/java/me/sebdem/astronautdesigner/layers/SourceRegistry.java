package me.sebdem.astronautdesigner.layers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "sourceregistry")
public class SourceRegistry {

	@XmlElementWrapper(name = "sources")
	@XmlElement(name="layersource")
	public void setSources(ArrayList<LayerSource> sources){
		this.sources = sources;
	}
	public List<LayerSource> getSources(){
		return this.sources;
	}
	
	private List<LayerSource> sources = new ArrayList<LayerSource>();
	
	public SourceRegistry() { 
		System.out.println("Registry Created");
	}
	
}
