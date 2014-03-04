import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.*;

public class MainClass {
	private BufferedImage img;
	private Complex[] refCarre;
	private Complex[] refRond;

	public ArrayList<Rectangle> mainAlgo(String filename, int seuilTinf,
			int seuilTsup, int seuilS, int seuilL, int seuilL2,
			int seuilTestRondCarre) {
		decodeimage(filename);
		ArrayList<Pixel> s = selec(seuilTinf, seuilTsup, seuilS, seuilL,
				seuilL2);
		ArrayList<ArrayList<Pixel>> t = ComposantesConnexes(s);
		ArrayList<ArrayList<Pixel>> u = Contours(t);
		ArrayList<ArrayList<Point>> v = signature(u);
		ArrayList<Complex[]> w = descripteursDeFourier(v);
		ArrayList<Rectangle> ResultatFinal = zonePlante(w, u,
				seuilTestRondCarre);
		return ResultatFinal;
	}

	public void outPutImage(String fileName) {
		File outputfile = new File(fileName);
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Nimp(int x, int y) {
		Color color = new Color(0, 0, 0);
		int rgb = color.getRGB();
		img.setRGB(x, y, rgb);
	}

	public void decodeimage(String filename) {
		try {
			// la mï¿½thode statique ï¿½read de la classe javax.imageio.ImageIO
			// renvoie
			// une instance de la classe BufferedImage (qui ï¿½tend la classe
			// abstraite Image).
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pixel> selec(int seuilTinf, int seuilTsup, int seuilS,
			int seuilL1, int seuilL2) {
		// selection des bons pixels
		ArrayList<Pixel> Choisi = new ArrayList<Pixel>();

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {// parcourir l'image

				Color c = new Color(img.getRGB(x, y));// prendre des valeurs RGB
														// de chaque pixel
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				float[] hsb = Color.RGBtoHSB(r, g, b, null); // convertir RGB en
																// HSB

				if (hsb[2] > seuilL1 && hsb[2] < seuilL2) {
					if (hsb[1] > seuilS) {
						if (seuilTinf < hsb[0] && hsb[0] < seuilTsup) {
							Pixel P = new Pixel(x, y, hsb[0], hsb[1], hsb[2]);
							Choisi.add(P);
							// ajout de ligne pour le test
							Color color = new Color(0, 0, 0);
							int rgb = color.getRGB();
							img.setRGB(x, y, rgb);
						}
					}
				}
			}
		}
		// affiche choisi dans la console pour test
		System.out.println(Choisi);
		return Choisi;
	}

	public ArrayList<ArrayList<Pixel>> ComposantesConnexes(
			ArrayList<Pixel> choisi) {
		int i = 0;
		int L = Integer.MAX_VALUE;
		ArrayList<ArrayList<Pixel>> CC = new ArrayList<ArrayList<Pixel>>();
		ArrayList<Pixel> choisibis = choisi ;
		int sizeChoisiBis = choisibis.size();
		
		for (int h = 0; h < sizeChoisiBis;h++) {
			Pixel pixel1 = choisibis.get(h);
			if (pixel1.pixelVoisinGauche(img,choisibis)!= null && pixel1.pixelVoisinHaut(img,choisibis)!= null){
				int a = pixel1.pixelVoisinGauche(img,choisibis).getNumeroPixel();
				int b = pixel1.pixelVoisinHaut(img,choisibis).getNumeroPixel();
				if (a == L && b == L) {
					i++;
					pixel1.setNumeroPixel(i);
				} else if (b == a && a != L) {
					pixel1.setNumeroPixel(a);
				} else {
					pixel1.setNumeroPixel(Math.min(a, b));

					for (Pixel pixel2 : choisibis) {
						int c = pixel2.getNumeroPixel();

					if (c == Math.max(a, b) && Math.max(a, b) != L) {
							pixel2.setNumeroPixel(Math.min(a, b));
						}
					}
				}
			}
			choisibis.set(h, pixel1);
		}

		for (int k = 0; k < i; k++) {
			ArrayList<Pixel> cc = new ArrayList<Pixel>();
			for (Pixel pixel3 : choisibis) {
				if (pixel3.getNumeroPixel() == k) {
					cc.add(pixel3);
				}
			}
			if (cc != null) {
				CC.add(cc);
			}
		}

		System.out.println(CC);
		return CC;
	}

	public boolean BelongsTocc(Pixel pixel, ArrayList<Pixel> cc) {
		boolean b = false;
		for (Pixel p : cc) {
			if (pixel == p) {
				b = true;
			}
		}
		return b;
	}

	public ArrayList<ArrayList<Pixel>> Contours(ArrayList<ArrayList<Pixel>> CC) {
		ArrayList<ArrayList<Pixel>> CONTOURS = new ArrayList<ArrayList<Pixel>>();
		ArrayList<Pixel> contours = new ArrayList<Pixel>();
		for (ArrayList<Pixel> aa : CC) {
			for (Pixel P : aa) {
				if (BelongsTocc(P, aa) == false) {
					if (BelongsTocc(P.pixelVoisinDroite(img), aa) == true
							|| BelongsTocc(P.pixelVoisinGauche(img), aa) == true
							|| BelongsTocc(P.pixelVoisinHaut(img), aa) == true
							|| BelongsTocc(P.pixelVoisinBas(img), aa) == true) {
						contours.add(P);
					}
				}
			}
			CONTOURS.add(contours);
		}
		return CONTOURS;
	}

	public ArrayList<ArrayList<Point>> signature(ArrayList<ArrayList<Pixel>> CC) {

		int Sx = 0;
		int Sy = 0;
		ArrayList<Point> courbe = new ArrayList<Point>();
		ArrayList<ArrayList<Point>> COURBE = new ArrayList<ArrayList<Point>>();
		for (ArrayList<Pixel> cc : CC) {

			for (Pixel pixel : cc) {
				Sx = Sx + pixel.getX();
				Sy = Sy + pixel.getY();
			}

			Sx = (int) Sx / cc.size();
			Sy = (int) Sy / cc.size();
			// Pixel G = new Pixel( Sx, Sy,img);
			Point P;

			for (Pixel p : cc) {
				float r = (float) Math.sqrt((p.getX() - Sx) ^ 2
						+ (p.getY() - Sy) ^ 2);
				float O = (float) Math.atan((p.getY() - Sy) / (p.getX() - Sx));
				P = new Point((int) r, (int) O);
				courbe.add(P);
			}
		}
		COURBE.add(courbe);
		return COURBE;
	}

	/*
	 * public void paint(ArrayList<ArrayList<Point>> COURBE, Graphics g){ for
	 * (ArrayList<Point> C: COURBE){ for (int i=0 ; i< C.size()-1; i++){ double
	 * x=C.get(i).getX(); double xx= C.get(i+1).getX(); double y=
	 * C.get(i).getY(); double yy = C.get(i+1).getY(); g.drawLine( x,(int)
	 * y,xx,(int) yy);
	 * 
	 * } } }
	 */

	public ArrayList<Complex[]> descripteursDeFourier(
			ArrayList<ArrayList<Point>> COURBE) {
		FastFourierTransformer math = new FastFourierTransformer(
				DftNormalization.UNITARY);
		// XYSeries serie = this.audioData.getSeries(0);
		// XYSeries specSerie = new XYSeries("Spectrum");
		// double step = (double) sampleRate;
		// double x=0.;
		ArrayList<Complex[]> Coef = new ArrayList<Complex[]>();
		for (ArrayList<Point> courbe : COURBE) {
			double[][] serie = new double[courbe.size()][2];
			for (int j = 0; j < courbe.size(); j++) {
				serie[j][0] = courbe.get(j).getX();
				serie[j][1] = courbe.get(j).getY();
			}
			int k = (int) Math.ceil(Math.log(serie.length) / Math.log(2));

			double[] data = new double[(int) Math.pow(2, k)];
			Complex[] cResult = math.transform(data, TransformType.FORWARD);
			Coef.add(cResult);
		}
		return Coef;

	}

	/*
	 * ********** remarque: il nous faut une image test dont on est sur qu'elle
	 * est un carrŽ ou un cercle ce qui nous donne les coef de fourier des
	 * signatures de rŽfŽrence on pourra ainsi comparer les descripteurs obtenus
	 * avec ceux de ref
	 * 
	 * il manque une mŽthode comparaison des descripteurs:
	 * planteRepŽrŽeParLeCarrŽ qui prend en entrŽe les descrip l'image
	 * l'arrayList<ArrayList<Pixel>> et qui renvoit une zone au dessus du
	 * stikers carrŽ (Žventuellement nulle)
	 */

	public boolean comparaisonDescripteursCarre(Complex[] test, float seuil) {
		boolean estCarre = false;
		double diff = 0;
		for (int i = 0; i < Math.min(test.length, refCarre.length); i++) {
			diff = diff + (Math.abs(test[i].abs() - refCarre[i].abs()))
					/ (Math.abs(test[i].abs() + refCarre[i].abs()));
		}
		if (diff < seuil) {
			estCarre = true;
		}
		return estCarre;
	}

	public boolean comparaisonDescripteursRond(Complex[] test, float seuil) {
		boolean estRond = false;
		double diff = 0;
		for (int i = 0; i < Math.min(test.length, refRond.length); i++) {
			diff = diff + (Math.abs(test[i].abs() - refCarre[i].abs()))
					/ (Math.abs(test[i].abs() + refCarre[i].abs()));
		}
		if (diff < seuil) {
			estRond = true;
		}
		return estRond;
	}

	public ArrayList<Rectangle> zonePlante(ArrayList<Complex[]> DF,
			ArrayList<ArrayList<Pixel>> contours, float seuil) {
		ArrayList<Rectangle> R = new ArrayList<Rectangle>();
		for (int i = 0; i < DF.size(); i++) {

			if (comparaisonDescripteursCarre(DF.get(i), seuil)) {
				int xmax = -1;
				int xmin = Integer.MAX_VALUE;
				int ymax = 0;
				String carre = new String();

				for (Pixel p : contours.get(i)) {
					int x = p.getX();
					int y = p.getY();
					if (xmax < x) {
						xmax = x;
					}
					if (x < xmin) {
						xmin = x;
					}
					if (ymax < y) {
						ymax = y;
					}

				}
				Point p1 = new Point(xmin, 0);
				Point p2 = new Point(xmin, ymax);
				Point p3 = new Point(xmax, 0);
				Point p4 = new Point(xmax, ymax);
				Rectangle r = new Rectangle(p1, p2, p3, p4, carre);
				R.add(r);
			}
			if (comparaisonDescripteursRond(DF.get(i), seuil)) {
				int xmax = -1;
				int xmin = Integer.MAX_VALUE;
				int ymax = 0;
				String rond = new String();

				for (Pixel p : contours.get(i)) {
					int x = p.getX();
					int y = p.getY();
					if (xmax < x) {
						xmax = x;
					}
					if (x < xmin) {
						xmin = x;
					}
					if (ymax < y) {
						ymax = y;
					}

				}
				Point p1 = new Point(xmin, 0);
				Point p2 = new Point(xmin, ymax);
				Point p3 = new Point(xmax, 0);
				Point p4 = new Point(xmax, ymax);
				Rectangle r = new Rectangle(p1, p2, p3, p4, rond);
				R.add(r);

			}
		}
		return R;
	}

}
