package me.sebdem.astronautdesigner.view3d;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import me.sebdem.astronautdesigner.view3d.data.Face;
import me.sebdem.astronautdesigner.view3d.data.UVCord;
import me.sebdem.astronautdesigner.view3d.data.Vertex;

@SuppressWarnings("restriction")
@XmlRootElement(name = "mesh")
public class MeshFX extends TriangleMesh {
	
	@XmlElementWrapper(name = "faces")
	public List<Face> face = new ArrayList<Face>();
	@XmlElementWrapper(name = "vertexbuffer")
	public List<Vertex> vertex = new ArrayList<Vertex>();
	@XmlElementWrapper(name = "uvbuffer")
	public List<UVCord> uvcord = new ArrayList<UVCord>();

	public MeshFX() {
		super();
		this.setVertexFormat(VertexFormat.POINT_NORMAL_TEXCOORD);
	}

	public void convert() {

		for (UVCord uv : uvcord) {
			this.getTexCoords().addAll(uv.uv._x, uv.uv._y);
		}

		for (Vertex v : vertex) {
			this.getPoints().addAll(v.pos._x, v.pos._y, v.pos._z);
			this.getNormals().addAll(v.norm._x, v.norm._y, v.norm._z);
		}

		for (Face f : face) {
			for (int vindex : reverse(f.vindexes)) {
				if (this.getVertexFormat() == VertexFormat.POINT_NORMAL_TEXCOORD)
					this.getFaces().addAll(vindex, vindex, vindex);
				else
					this.getFaces().addAll(vindex, vindex);
			}
		}
	}

	public int[] reverse(int[] lst) {
		int[] lstre = new int[lst.length];
		for (int i = 0; i < lst.length; i++) {
			int e = lst[lst.length - 1 - i];
			lstre[i] = e;
		}
		return lst;

	}

}
