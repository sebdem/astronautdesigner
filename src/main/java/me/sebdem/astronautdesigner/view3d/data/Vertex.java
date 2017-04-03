package me.sebdem.astronautdesigner.view3d.data;

import javax.xml.bind.annotation.*;

import com.jme3.renderer.Camera;

@XmlType
public class Vertex
{
	@XmlElement(name="position")
	public floatp3 pos = new floatp3();
	@XmlElement(name="normal")
	public floatp3 norm;
	
	@XmlTransient
	public int model_index = 0;
	public static int mi = 0;
	@XmlTransient
	public int rgb = 0;
	
	public Vertex() { 
		norm = new floatp3(0,0,0);
	}
	
	public Vertex(float x, float y, float z)
	{
		this(x,y,z,0,0,0);
	}
	public  Vertex(float x, float y, float z, float xn, float yn, float zn)
	{
		pos._x = x;
		pos._y = y;
		pos._z = z;

		norm = new floatp3(xn,yn,zn);
		model_index = mi;
		mi++;
	}
	public String toString()
	{
		return "V:[x:"+pos._x+", y:"+pos._y+", z:"+pos._z+"]";
	}
	
	public int getIndex()
	{
		return this.model_index;
	}
}
