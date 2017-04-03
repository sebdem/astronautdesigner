package me.sebdem.astronautdesigner.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class ImageComponent extends JComponent implements MouseListener{

	protected static final long serialVersionUID = 1L;

	protected BufferedImage image;

	protected List<IInteraction> onAction;

	
	protected boolean hovering = false;
	
	
	public ImageComponent() {
		this.setSize(96, 96);
		this.addMouseListener(this);
		this.onAction = new ArrayList<IInteraction>();
	}
	
	public ImageComponent(BufferedImage image) {
		this();
		this.image = image;
	}
	
	
	
	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the onAction
	 */
	public IInteraction[] getOnAction() {
		return (IInteraction[]) onAction.toArray();
	}

	/**
	 * @param onAction the onAction to set
	 */
	public void addOnAction(IInteraction onAction) {
		this.onAction.add(onAction);
	}

	

	// TODO Rework!
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Color border = (hovering) ? Color.lightGray : Color.gray;
		int width = this.getWidth(), height = this.getHeight();
		g.setClip(-2, -2, width + 4, height + 4);
		g.setColor(border);
		g.fillRect(-2, -2, width + 4, height + 4);
		
		Color g1 = new Color(102,102,102), g2 = new Color(153,153,153);
		boolean graySwitch = true;
		for(int y = 0; y < height; y+=8){
			for(int x = 0; x < width; x+=8){
				
				g.setColor((graySwitch) ? g2 : g1);
				g.fillRect(x, y, 8, 8);
				graySwitch = !graySwitch;
			}
			graySwitch = !graySwitch;
		}
		
		
		g.drawImage(this.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}

	
	public void mouseClicked(MouseEvent e) {
		this.onAction.forEach((item) -> { item.perform();});
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		hovering = true;
	}

	public void mouseExited(MouseEvent e) {
		hovering = false;
	}
	

}
