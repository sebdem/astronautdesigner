package me.sebdem.astronautdesigner.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import me.sebdem.astronautdesigner.skin.ITexture;

public class TextureComponent extends JComponent implements MouseListener{

	protected static final long serialVersionUID = 1L;

	protected ITexture texture;


	
	protected boolean hovering = false;
	
	
	public TextureComponent() {
		this.setSize(96, 96);
		this.addMouseListener(this);
	}
	
	public TextureComponent(ITexture texture) {
		this();
		this.texture = texture;
	}
	
	
	
	/**
	 * @return the texture
	 */
	public ITexture getTexture() {
		return texture;
	}

	/**
	 * @param texture the texture to set
	 */
	public void setTexture(ITexture texture) {
		this.texture = texture;
	}


	

	// TODO Rework!
	@Override 
	public void paintComponent(Graphics graph){
		super.paintComponent(graph);
		Graphics2D g = (Graphics2D)graph;
		Color border = (hovering) ? new Color(72,116,200) : new Color(72,72,72);
		int margin =  (hovering) ? 6 : 4;
		int moff = margin / 2;
		int width = this.getWidth(), height = this.getHeight();
		Rectangle r = new Rectangle(-moff, -moff, width + margin, height + margin);
		g.setClip(r);
		g.setColor(border);
		g.fill(r);
		
		Color  g1 = new Color(153,153,153), g2 = new Color(102,102,102);
		boolean graySwitch = true;
		for(int y = 0; y < height; y+=8){
			for(int x = 0; x < width; x+=8){
				
				g.setColor((graySwitch) ? g1 : g2);
				g.fillRect(x, y, 8, 8);
				graySwitch = !graySwitch;
			}
			graySwitch = !graySwitch;
		}
		
		
		if (this.texture != null){
			g.drawImage(texture.getDiffuse(), 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

	
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		hovering = true;
		this.getParent().repaint();
	}

	public void mouseExited(MouseEvent e) {
		hovering = false;
		this.getParent().repaint();
	}
	

}
