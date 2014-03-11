package pactutils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pixel {

	private int x;
	private int y;
	private float T;
	private float L;
	private float S;
	private int nP = Integer.MAX_VALUE;

	public Pixel(int x, int y, float T, float S, float L) {
		this.x = x;
		this.y = y;
		this.T = T;
		this.L = L;
		this.S = S;
	}

	public Pixel(int x, int y, BufferedImage img) {

		Color c = new Color(img.getRGB(x, y));// prendre des valeurs RGB de
												// chaque pixel
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		float[] hsb = Color.RGBtoHSB(r, g, b, null); // convertir RGB en HSB
		this.T = hsb[0];
		this.S = hsb[1];
		this.L = hsb[2];
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// tentative de modif
	public Pixel pixelVoisinGauche(BufferedImage img, ArrayList<Pixel> choisibis) {
		Pixel V = null;
		for (Pixel p : choisibis) {
			if (p.getX() == this.x - 1 && p.getY() == this.y) {
				V = p;
			}
		}
		if (V == null) {
			if (x != 0) {
				V = new Pixel(x - 1, y, img);
			}
		}
		return V;
	}

	public Pixel pixelVoisinDroite(BufferedImage img, ArrayList<Pixel> choisibis) {
		Pixel V = null;
		for (Pixel p : choisibis) {
			if (p.getX() == this.x + 1 && p.getY() == this.y) {
				V = p;
			}
		}
		if (V == null) {
			if (x != img.getTileWidth()) {
				V = new Pixel(x + 1, y, img);
			}
		}
		return V;
	}

	public Pixel pixelVoisinHaut(BufferedImage img, ArrayList<Pixel> choisibis) {
		Pixel V = null;
		for (Pixel p : choisibis) {
			if (p.getX() == this.x && p.getY() == this.y-1) {
				V = p;
			}
		}
		if (V == null) {
			if (this.y != 0) {
				V = new Pixel(x, y - 1, img);
			}
		}
		return V;
	}

	public Pixel pixelVoisinBas(BufferedImage img, ArrayList<Pixel> choisibis) {
		Pixel V = null;
		for (Pixel p : choisibis) {
			if (p.getX() == this.x && p.getY() == this.y+1) {
				V = p;
			}
		}
		if (V == null) {
			if (this.y != img.getHeight()) {
				V = new Pixel(x, y + 1, img);
			}
		}	
		return V;
	}

	
	public boolean BelongsTocc(ArrayList<Pixel> cc) {
		boolean b = false;
		for (Pixel p : cc) {
			if (this == p) {
				b = true;
			}
		}
		return b;
	}
	
	
	
	public int getNumeroPixel() {
		return nP;
	}

	public void setNumeroPixel(int a) {
		this.nP = a;
	}

}
