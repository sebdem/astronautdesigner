package me.sebdem.astronautdesigner.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.sebdem.astronautdesigner.gui.components.SkinLayerComponent;
import me.sebdem.astronautdesigner.skin.SkinFile;
import me.sebdem.astronautdesigner.skin.SkinFile.SkinSection;
import me.sebdem.astronautdesigner.view3d.MeshRenderer;
import me.sebdem.astronautdesigner.view3d.ModelReader;
import me.sebdem.astronautdesigner.view3d.RenderMode;

public class ADWindow extends JFrame {

	private JPanel content;

	public MeshRenderer renderer;

	public SkinFile skin;

	private static final long serialVersionUID = 1L;

	public ADWindow(SkinFile skin) throws IOException {
		this.skin = skin;
		content = new JPanel();
		content.setLayout(new GridLayout());
		this.add(content);
		this.setBounds(10, 10, 840, 640);
		this.setTitle("Astronaut Designer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		renderer = new MeshRenderer(500, ModelReader.read("PlayerMdl.mesh.xml"));
		renderer.setTexture(skin.body);
		// renderer.texture = new Texture(ImageIO.read(new
		// File("skins/Grineer/Lancer.png")));

		content.add(renderer);

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setLayout(new FlowLayout());
		

		
		JButton switchViewMode = new JButton("Render Mode");
		switchViewMode.addActionListener((actionEvent) -> {

			switch (this.renderer.renderMode) {
			case Model3D:
				this.renderer.setRenderMode(RenderMode.Texture2D);
				break;
			case Texture2D:
				this.renderer.setRenderMode(RenderMode.Model3D);
				break;
			}
			this.repaint();
		});
		buttonWrapper.add(switchViewMode);

		JButton setLayer = new JButton("Do Stuff");
		buttonWrapper.add(setLayer);
		
		
		content.add(buttonWrapper);

		/*JPanel imageContainer = new JPanel();
		imageContainer.setLayout(new FlowLayout() );
		TextureComponent image;
		for(ITexture t : skin.body.getLayers()){
			image = new TextureComponent(t);
			image.setPreferredSize(new Dimension(256,256));
			image.addOnAction(new IInteraction(){
				@Override
				public Object perform(Object... params) {
					skin.body.update();
					return null;
				}});
			imageContainer.add(image);
		}*/
		SkinLayerComponent layers = new SkinLayerComponent(skin);
		content.add(layers);
		
		setLayer.addActionListener((actionEvent) -> {
			skin.set(SkinSection.BODY, new Object());
			renderer.update();
			layers.repaint();
			//imageContainer.repaint();
		});

	}

	public ADWindow showDesigner() {
		this.setVisible(true);
		return this;
	}

}
