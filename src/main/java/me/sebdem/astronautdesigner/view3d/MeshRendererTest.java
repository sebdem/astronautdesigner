package me.sebdem.astronautdesigner.view3d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jme3.math.Vector3f;

import me.sebdem.astronautdesigner.skin.Texture;

public class MeshRendererTest {
	public static void main(String[] args) throws IOException{
		int height = 900;
		float ratio = 1.25f;
		int width = (int) (height/ratio);
		//ratio = height/width;;
		//width = height/ratio;		
		
		MeshRenderer meshr = new MeshRenderer(width,height, ModelReader.read("PlayerMdl.mesh.xml"));
		
		JFrame frame = new JFrame();
		frame.add(meshr);

		frame.setBounds(10, 10, width, height);

		frame.setTitle("MeshRenderer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		meshr.setTexture( new Texture(ImageIO.read(new File("skins/skin_main_diff.png")), null) );
		//meshr.setBackground(Color.black);
		//meshr.render();

		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Vector3f v = meshr.camera.getLocation();
				System.out.println("Typed: " + e.getKeyCode());
				switch(e.getKeyCode()){
				case KeyEvent.VK_W: meshr.camera.setLocation(v.add(0, 0, 0.25f)); break;
				case KeyEvent.VK_S: meshr.camera.setLocation(v.add(0, 0, -0.25f)); break;
				case KeyEvent.VK_A: meshr.camera.setLocation(v.add(0.25f, 0, 0)); break;
				case KeyEvent.VK_D: meshr.camera.setLocation(v.add(-0.25f, 0, 0));  break;
				case KeyEvent.VK_E: meshr.camera.setLocation(v.add(0, 0.25f, 0)); break;
				case KeyEvent.VK_Q: meshr.camera.setLocation(v.add(0,-0.25f, 0));  break;
				case KeyEvent.VK_F1: try {
						ImageIO.write(meshr.getTexture().getDiffuse(), "png", new File("results/texture_mod.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} break;
				case KeyEvent.VK_F2: try {
					ImageIO.write(meshr.renderedImage, "png", new File("testRender.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				} 
				meshr.camera.lookAt(new Vector3f(0,0f,0), new Vector3f(0,1,0));
				meshr.update();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
//		float range = 1.5f, amount = 1f;
//		for(float f1 = -range; f1 <= range; f1 +=amount){
//
//			for(float f2 = -range; f2 <= range; f2 += amount){
//				meshr.camera.setLocation(new Vector3f(f1,0.5f,f2));
//				meshr.camera.lookAt(new Vector3f(0,0f,0), new Vector3f(0,1,0));
//				meshr.clear();
//				meshr.render();
//				ImageIO.write(meshr.pixelsToImage(), "png", new File("results/render/testRender_"+f1 + "-" +f2+".png"));
//			}
//		}
		
	}
}
