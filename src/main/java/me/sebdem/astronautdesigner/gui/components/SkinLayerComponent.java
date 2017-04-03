package me.sebdem.astronautdesigner.gui.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import me.sebdem.astronautdesigner.skin.ITexture;
import me.sebdem.astronautdesigner.skin.SkinFile;

public class SkinLayerComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	protected JPanel bodyLayerPanel;
	protected JPanel helmetLayerPanel;
	protected JTabbedPane tabbedPane;
	
	protected ArrayList<TextureComponent> bodyLayers;
	protected ArrayList<TextureComponent> helmetLayers;
	
	protected SkinFile skin;

	
	public SkinLayerComponent(SkinFile skin){
		super();
		this.skin = skin;
		bodyLayers = new ArrayList<TextureComponent>();
		helmetLayers = new ArrayList<TextureComponent>();
		setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		bodyLayerPanel = new JPanel();
		tabbedPane.addTab("Body", null, bodyLayerPanel, null);
		
		helmetLayerPanel = new JPanel();
		tabbedPane.addTab("Helmet", null, helmetLayerPanel, null);
		helmetLayerPanel.setVisible(false);
		bodyLayerPanel.setVisible(true);
		
		
		initComp();
	}
	
	
	public void initComp(){
		TextureComponent image;
		for(ITexture t : this.skin.body.getLayers()){
			image = new TextureComponent(t);
			image.setPreferredSize(new Dimension(128,128));
			this.bodyLayerPanel.add(image);
		}
		
		for(ITexture t : this.skin.helmet.getLayers()){
			image = new TextureComponent(t);
			image.setPreferredSize(new Dimension(128,128));
			this.helmetLayerPanel.add(image);
		}
	}
	
	
}
