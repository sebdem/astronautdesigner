package me.sebdem.astronautdesigner.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import me.sebdem.astronautdesigner.gui.components.TextureComponent;
import me.sebdem.astronautdesigner.skin.ITexture;

public class SingleTextureSelection extends JDialog {

	private static final long serialVersionUID = 1L;
	
	protected JPanel content;
	
	
	
	
	
	
	
	protected ITexture selectedTexture;
	
	public SingleTextureSelection(ITexture texture){
		this.selectedTexture = texture;
		
		this.setTitle("Texture Options");
		this.setPreferredSize(new Dimension(400,300));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{261, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
				content = new JPanel();
				GridBagConstraints gbc_content = new GridBagConstraints();
				gbc_content.fill = GridBagConstraints.BOTH;
				gbc_content.gridx = 0;
				gbc_content.gridy = 0;
				getContentPane().add(content, gbc_content);
				
						GridBagLayout gbl_contentPane = new GridBagLayout();
						gbl_contentPane.columnWidths = new int[]{0, 275, 0, 0};
						gbl_contentPane.rowHeights = new int[]{0, 130, 32, 0};
						gbl_contentPane.columnWeights = new double[]{};
						gbl_contentPane.rowWeights = new double[]{};
						content.setLayout(gbl_contentPane);
						GridBagLayout gbl_content = new GridBagLayout();
						gbl_content.columnWidths = new int[]{0};
						gbl_content.rowHeights = new int[]{0};
						gbl_content.columnWeights = new double[]{Double.MIN_VALUE};
						gbl_content.rowWeights = new double[]{Double.MIN_VALUE};
						content.setLayout(gbl_content);
						
						TextureComponent textureComponent = new TextureComponent();
						GridBagConstraints gbc_textureComponent = new GridBagConstraints();
						gbc_textureComponent.gridx = 0;
						gbc_textureComponent.gridy = 0;
						content.add(textureComponent, gbc_textureComponent);
		
		
		
	}
	
	
}
