package me.sebdem.astronautdesigner.view3d;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JComponent;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import me.sebdem.astronautdesigner.skin.ITexture;
import me.sebdem.astronautdesigner.view3d.data.UVCord;
import me.sebdem.astronautdesigner.view3d.data.Vertex;
import me.sebdem.util.ColorUtils;

public class MeshRenderer extends JComponent /*implements Runnable */{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BufferedImage renderedImage;

	private MeshFX mesh;
	private ITexture texture;
	private int[] pixels;
	private float[] depthBuffer;
	private int width;
	private int height;
	private float renderDetail = 0.5f;
	public Camera camera;

	//private boolean running;

	//private Thread thread;

	public RenderMode renderMode;

	public MeshRenderer(int height, MeshFX mesh) {
		this((int) (height / 1.25f), height, mesh);
	}

	public MeshRenderer(int width, int height, MeshFX mesh) {
		super();
		this.renderMode = RenderMode.Texture2D;
		this.width = width;
		this.height = height;
		this.renderedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//this.renderedImage = createVolatileImage(width, height);
		this.pixels = new int[width * height];// ((DataBufferInt)renderedImage.getData().getDataBuffer()).getData();

		this.depthBuffer = new float[pixels.length];
		this.mesh = mesh;
		this.camera = new Camera(width, height);
		this.camera.setFrustumPerspective(60, 0.75f, 1, 10);
		this.camera.setLocation(new Vector3f(-1.5f, 0.5f, 1.5f));
		this.camera.lookAt(new Vector3f(0, 0f, 0), new Vector3f(0, 1, 0));
		// camera.setRotation(new Quaternion(30,0,0,0));
		// camera.setFrustumFar(2)
		this.clear();
		//this.thread = new Thread(this);
		this.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println("Repainting");
		switch(renderMode){
			case Model3D: g.drawImage(this.renderedImage, 0, this.renderedImage.getHeight(), this.renderedImage.getWidth(), -this.renderedImage.getHeight(), null); break;
			case Texture2D: g.drawImage(this.renderedImage, 0, 0, this.renderedImage.getWidth(), this.renderedImage.getHeight(), null); break;
		}
		
		g.dispose();
	}

	public BufferedImage pixelsToImage() {
		BufferedImage i = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		i.setRGB(0, 0, this.width, this.height, pixels, 0, this.width);
		return i;
	}

	public void setRotation(float x, float y, float z) {
		Vector3f v = camera.getLocation();
		// this.camera.setLocation(v.add((float)Math.cos(x) * 0.5f,
		// (float)Math.sin(y) * 0.5f, (float)Math.cos(z) * 0.5f));
	}

	public void setPixel(int index, int value, float bufferValue) {
		if (index >= 0 && index < pixels.length)
			if (depthBuffer[index] > bufferValue) {
				// System.out.println("TRUE");
				pixels[index] = value;
				depthBuffer[index] = bufferValue;
			}
	}

	public void setPixel(int x, int y, int value, float bufferValue) {
		if (x >= 0 && x < width)
			setPixel(x + y * width, value, bufferValue);
	}

	public void setPixel(Vector3f vec, int value) {
		setPixel((int) (vec.x * width), (int) (vec.y * height), value, vec.z);
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (this.getBackground() != null) ? this.getBackground().getRGB() : ColorUtils.TRANSPARENT_INT;
			depthBuffer[i] = Integer.MAX_VALUE;
		}
	}

	public void renderImage() {
		// Graphics g = this.getGraphics();
		// if (g != null){
		// BufferedImage img = this.renderedImage;
		// if ( img != null){
		// // Das zu zeichnende Image wird hier invertiert, um dem Rendering
		// fehler entgegen zu wirken
		// g.drawImage(img, 0, 0,img.getWidth(), img.getHeight(), null);
		// }
		// g.dispose();
		// }
		this.repaint();
	}

//	public void start() {
//		this.running = true;
//		this.thread.start();
//	}
//
//	public void run() {
//		long lastTime = System.nanoTime();
//		final double ns = 1000000000.0 / 30.0;// 30 times per second
//		double delta = 0;
//		requestFocus();
//		long now = 0, passed = 0;
//		while (running) {
//			now = System.nanoTime();
//			passed = (now - lastTime);
//			delta = delta + (passed / ns);
//			lastTime = now;
//			while (delta >= 1) {
//				update();
//				delta--;
//			}
//			renderImage();
//		}
//	}

	public void update() {
		clear();
		render();
		this.renderedImage = this.pixelsToImage();
		this.repaint();
		// try {
		// ImageIO.write(renderedImage, "png", new File("testRender.png"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void render() {
		if (this.texture != null){
				
			switch (renderMode) {
			case Model3D:
				renderTexture3D();
				break;
			case Texture2D:
				renderTexture2D();
				break;
			}
		}
		// System.gc();
	}

	private void renderTexture3D() {

		List<Vertex> vrtxs = this.mesh.vertex;
		List<UVCord> uvcords = this.mesh.uvcord;

		Vector3f v1, v2, v3, vM;

		Vector3f vmin, vmax;
		UVCord uv1, uv2, uv3;

		for (int i = 0; i < mesh.vertex.size() - 1; i += 3) {

			v1 = toVector3f(vrtxs.get(i));
			v2 = toVector3f(vrtxs.get(i + 1));
			v3 = toVector3f(vrtxs.get(i + 2));
			vM = v1.add(v2).add(v3).divideLocal(3);

			uv1 = uvcords.get(i);
			uv2 = uvcords.get(i + 1);
			uv3 = uvcords.get(i + 2);

			v1 = camera.getScreenCoordinates(v1);
			v2 = camera.getScreenCoordinates(v2);
			v3 = camera.getScreenCoordinates(v3);

			vmin = getSmallest(v1, v2, v3);
			vmax = getBiggest(v1, v2, v3);

			Polygon p = new Polygon(
					new int[] { (int) Math.floor(v1.x), (int) Math.floor(v2.x), (int) Math.floor(v3.x) },
					new int[] { (int) Math.floor(v1.y), (int) Math.floor(v2.y), (int) Math.floor(v3.y) }, 3);

			if (this.texture != null) {

				int stepsizeX = (int) (vmax.x - vmin.x);
				// int stepsizeY = (int) (vmax.y - vmin.y);
				float stepsizeZ = (vmax.z - vmin.z) / (float) (stepsizeX);

				float a = v1.subtract(v2).cross(v1.subtract(v3)).length();
				float a1, a2, a3;
				Vector3f f1, f2, f3;// , v1_2, v1_3;
				int texU, texV;

				// v1_2 = v2.subtract(v1);
				// v1_3 = v3.subtract(v1);

				for (float x = vmin.x; x < vmax.x; x += renderDetail) {
					for (float y = vmin.y; y < vmax.y; y += renderDetail) {

						f1 = new Vector3f(v1.x - x, v1.y - y, 0);
						f2 = new Vector3f(v2.x - x, v2.y - y, 0);
						f3 = new Vector3f(v3.x - x, v3.y - y, 0);

						a1 = f2.cross(f3).length() / a;
						a2 = f3.cross(f1).length() / a;
						a3 = f1.cross(f2).length() / a;

						if ((a1 >= 0) && (a2 >= 0) && (a3 >= 0) && (a1 + a2 + a3 <= 1)) {

							texU = (int) Math.floor(
									(uv1.uv._x * a1 + uv2.uv._x * a2 + uv3.uv._x * a3) * (texture.getDiffuse().getWidth() - 1));
							texV = (int) Math.floor(
									(uv1.uv._y * a1 + uv2.uv._y * a2 + uv3.uv._y * a3) * (texture.getDiffuse().getHeight() - 1));

							if (texU >= 0 && texV >= 0 && texU < texture.getDiffuse().getWidth() && texV < texture.getDiffuse().getHeight() ) {
								setPixel((int) Math.floor(x), (int) Math.floor(y), texture.getDiffuse().getRGB(texU, texV),
										lerp(vmin.z, vmax.z, stepsizeZ * x));
							}
						}
					}
				}

			}
		}
	}

	private void renderTexture2D() {
		int texWidth = this.texture.getDiffuse().getWidth();
		int texHeight = this.texture.getDiffuse().getHeight();
		int offx = (this.width - texWidth) / 2;
		int offy = (this.height - texHeight) / 2;

		for (int y = offy; y < offy + texHeight; y++) {
			for (int x = offx; x < offx + texWidth; x++) {
				setPixel(x, y, this.texture.getDiffuse().getRGB(x - offx, y - offy), 1);
			}
		}
	}

	private Vector3f toVector3f(Vertex v) {
		return new Vector3f(v.pos._x, v.pos._y, v.pos._z);
	}

	@SuppressWarnings("unused")
	private static int shade(int ocolor, float factor) {
		return (int) (((ocolor & 16711680) >> 16) * factor) & 16711680 << 16
				| (int) (((ocolor & 65280) >> 8) * factor) & 65280 << 8 | (int) (((ocolor & 255)) * factor);
	}

	private Vector3f getSmallest(Vector3f... vec) {
		Vector3f retVec = new Vector3f(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
		for (Vector3f v : vec) {
			if (v.x < retVec.x)
				retVec.x = v.x;
			if (v.y < retVec.y)
				retVec.y = v.y;
			if (v.z < retVec.z)
				retVec.z = v.z;
		}
		return retVec;
	}

	private Vector3f getBiggest(Vector3f... vec) {
		Vector3f retVec = new Vector3f(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
		for (Vector3f v : vec) {
			if (v.x > retVec.x)
				retVec.x = v.x;
			if (v.y > retVec.y)
				retVec.y = v.y;
			if (v.z > retVec.z)
				retVec.z = v.z;
		}
		return retVec;
	}

	private float lerp(float pMin, float pMax, float p) {
		return lerp(pMin, pMax, p, 1);
	}

	private float lerp(float pMin, float pMax, float p, float base) {
		float value = (base - p) * pMin + p * pMax;
		// System.out.print("lerp("+value+")");
		return value;
	}

	public void setTexture(ITexture texture) {
		this.texture = texture;
		this.update();
	}

	public void setRenderMode(RenderMode mode) {
		this.renderMode = mode;
		this.update();
	}

	public ITexture getTexture() {
		return this.texture;
	}

}
