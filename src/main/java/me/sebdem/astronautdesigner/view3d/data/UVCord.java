package me.sebdem.astronautdesigner.view3d.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class UVCord {
	@XmlElement(name = "texcoord")
	public floatp2 uv = new floatp2();

	public UVCord() {
	}

	public UVCord(float u, float v) {
		uv = new floatp2(u, v);
	}
	
	public String toString(){
		return "UV: " + uv._x + "/" + uv._y;
	}
}
