package me.sebdem.astronautdesigner.view3d.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlType

public class Face {
	public static boolean full;

	@XmlTransient
	public boolean isVisible;
	
	@XmlTransient
	public int[] vindexes = new int[3];
	
	@XmlAttribute
	public void setV1(short v1) {
		//vertices.add(parent.vertex.get(v1));
		vindexes[0] = v1;
	}

	@XmlAttribute
	public void setV2(short v2) {
		//vertices.add(parent.vertex.get(v2));
		vindexes[1] = v2;
	}

	@XmlAttribute
	public void setV3(short v3) {
		//vertices.add(parent.vertex.get(v3));
		vindexes[2] = v3;
	}

	@XmlTransient
	public int getV1() {
		return vindexes[0];
	}

	@XmlTransient
	public int getV2() {
		return vindexes[1];
	}

	@XmlTransient
	public int getV3() {
		return vindexes[2];
	}
	
	@XmlTransient
	public List<Vertex> vertices = new ArrayList<Vertex>();


	public Face() {
	}

	public Face(short v1, short v2, short v3) {
		setV1(v1);
		setV2(v2);
		setV3(v3);
	}
	
	
	public String toString(){
		return vindexes[0]+ "_"+vindexes[1]+"_"+vindexes[2];
	}

}
