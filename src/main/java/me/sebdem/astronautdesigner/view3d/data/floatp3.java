package me.sebdem.astronautdesigner.view3d.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class floatp3 {

	@XmlTransient
	public float _x;
	
	@XmlTransient
	public float _y;
	@XmlTransient
	public float _z;
	
	@XmlAttribute(name="x")
	public void setX(float x)
	{
		_x =  x ;
	}
	@XmlAttribute(name="y")
	public void setY(float y)
	{
		_y = y;
	}
	@XmlAttribute(name="z")
	public void setZ(float z)
	{
		_z =  z;
	}
	
	public floatp3() {}
	public floatp3(float _x, float _y, float _z) {
		this._x = _x;
		this._y = _y;
		this._z = _z;
	}

}
