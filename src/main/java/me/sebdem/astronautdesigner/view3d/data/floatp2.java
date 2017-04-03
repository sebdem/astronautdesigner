package me.sebdem.astronautdesigner.view3d.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("restriction")
public class floatp2 {

	@XmlTransient
	public float _x;
	@XmlTransient
	public float _y;
	
	public floatp2() {}
	public floatp2(float _x, float _y) {
		this._x = _x;
		this._y = _y;
	}
	
	@XmlAttribute(name="u")
	public void setX(float x)
	{
		_x =  x ;
	}
	@XmlAttribute(name="v")
	public void setY(float y)
	{
		_y =   y;
	}

}
