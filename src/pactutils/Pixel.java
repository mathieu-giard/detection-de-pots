package pactutils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import compo_connexe.Cross;

public class Pixel implements Cross {

	/********* constructueur *********/

	private int x;
	private int y;
	private float T;
	private float L;
	private float S;
	private int nP = -1;
	private boolean selectionne = false;

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

	/******** appartenance **********/

	public boolean BelongsTocc(ArrayList<Pixel> cc) {
		boolean b = false;
		for (Pixel p : cc) {
			if (this == p) {
				b = true;
			}
		}
		return b;
	}

	/********* voisinage ***********/

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
			if (p.getX() == this.x && p.getY() == this.y - 1) {
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
			if (p.getX() == this.x && p.getY() == this.y + 1) {
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

	/********** getter **********/

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNumeroPixel() {
		return nP;
	}

	public void setNumeroPixel(int a) {
		this.nP = a;
	}

	// A FAIRE !!!!!!!!!!!
	@Override
	public ArrayList<Cross> getVoisin(Cross[][] tab) {
		ArrayList<Cross> retour = new ArrayList<Cross>();
		if (x == 0 && y == 0)
			;
		else if (x == 0) {
			if (tab[x][y - 1].getNb() >= 0)
				retour.add(tab[x][y - 1]);
		} else if (y == 0) {
			if (tab[x - 1][y].getNb() >= 0)
				retour.add(tab[x - 1][y]);
		} else {
			if (tab[x][y - 1].getNb() >= 0)
				retour.add(tab[x][y - 1]);
			if (tab[x - 1][y].getNb() >= 0)
				retour.add(tab[x - 1][y]);
		}

		return retour;
	}

	public int getNb() {
		return nP;
	}

	public void setNb(int nb) {
		this.nP = nb;

	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public boolean getSelectionne() {
		return this.selectionne;
	}
}