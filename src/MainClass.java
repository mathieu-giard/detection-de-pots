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

import compo_connexe.CompoConnexe;
import compo_connexe.Cross;
import pactutils.Pixel;
import pactutils.Rectangle;

public class MainClass {
	private BufferedImage img;
	private Complex[] refCarre;
	private Complex[] refRond;
	private Point[] signCarre;
	private Point[] signRond;

	public ArrayList<Rectangle> mainAlgo(String filename1, String filename2,
			double d, double e, double f, double g, double h, double seuilCarre) {

		this.decodeimage(filename1);
		this.CreationDeCarreEtRond();
		this.outPutImage("carr�_et_rond_parfait");
		this.decodeimage("carr�_et_rond_parfait");
		ArrayList<Pixel> choisi = this.selec(0, 360, 0, 0, 100);
		this.outPutImage("carr�PSegm");
		ArrayList<ArrayList<Pixel>> CC = this.ComposantesConnexes(choisi);
		this.outPutImage("carr�CC");
		ArrayList<Point[]> signature = this.SIGNATURE(CC);
		this.setSignCarre(signature.get(0));
		// on a initialis� l'attribu signCarr� qui va servir de r�f�rence

		this.decodeimage(filename2);
		ArrayList<Pixel> choisi2 = this.selec(d, e, f, g, h);
		this.outPutImage("img-carr�3-segm");
		ArrayList<ArrayList<Pixel>> CC2 = this.ComposantesConnexes(choisi2);
		this.outPutImage("img-carr�3-compConn");
		ArrayList<Point[]> signature2 = this.SIGNATURE(CC2);
		ArrayList<Rectangle> R = this.zonePlante(signature2, CC2, seuilCarre);
		this.MiseEnEvidenceDuCarre(R);
		this.outPutImage("zone_rendue3");
		return R;

	/*ArrayList<Pixel> s = this.selec(seuilTinf, seuilTsup, seuilS, seuilL,seuilL2);
		
		ArrayList<ArrayList<Pixel>> t = ComposantesConnexes(s);
		
		ArrayList<ArrayList<Point>> v = ;
		
		ArrayList<Rectangle> ResultatFinal = zonePlante(
				seuilTestRondCarre);
		return ResultatFinal;*/
	}

	public void outPutImage(String fileName) {
		File outputfile = new File(fileName);
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSignCarre(Point[] p) {
		this.signCarre = p;
	}

	public void Nimp(int x, int y) {
		Color color = new Color(0, 0, 0);
		int rgb = color.getRGB();
		img.setRGB(x, y, rgb);
	}

	public void ChangeCouleurZone(ArrayList<Pixel> zone) {
		Color color = new Color(0, 255, 0);
		int rgb = color.getRGB();
		for (Pixel p : zone) {
			img.setRGB(p.getX(), p.getY(), rgb);

		}
	}

	public void ChangeCouleurZone2(ArrayList<Pixel> zone) {
		Color color = new Color(0, 0, 0);
		int rgb = color.getRGB();
		for (Pixel p : zone) {
			img.setRGB(p.getX(), p.getY(), rgb);

		}
	}

	public void TSL(int x, int y) {
		Color c = new Color(img.getRGB(x, y));// prendre des valeurs RGB
		// de chaque pixel
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		System.out.println(hsb[0]);
		System.out.println(hsb[1]);
		System.out.println(hsb[2]);
	}

	public void CreationDeCarre() {
		for (int i = 200; i < 300; i++) {
			for (int j = 100; j < 200; j++) {
				Color color = new Color(245, 215, 0);
				int rgb = color.getRGB();
				img.setRGB(i, j, rgb);
			}
		}
	}

	public void CreationDeCarreEtRond() {
		for (int i = 20; i < 100; i++) {
			for (int j = 100; j < 180; j++) {
				Color color = new Color(245, 215, 0);
				int rgb = color.getRGB();
				img.setRGB(i, j, rgb);
			}
		}
		for (int k = 150; k < 350; k++) {
			for (int h = 50; h < 250; h++) {
				if (Math.pow(k - 250, 2) + Math.pow(h - 150, 2) <= 1000) {
					Color color = new Color(255, 215, 0);
					int rgb = color.getRGB();
					img.setRGB(k, h, rgb);
				}
			}
		}
	}

	public void CreationDeRond() {
		/*
		 * for(int k=150;k<170;k++){ for(int h=50;h<70;h++){ if(
		 * Math.pow(k-160,2)+ Math.pow(h-60,2)<= 100){ Color color = new
		 * Color(255, 215, 0); int rgb = color.getRGB(); img.setRGB(k, h, rgb);
		 * } }
		 * 
		 * }
		 */
		for (int k = 150; k < 350; k++) {
			for (int h = 50; h < 250; h++) {
				if (Math.pow(k - 250, 2) + Math.pow(h - 150, 2) <= 1000) {
					Color color = new Color(255, 215, 0);
					int rgb = color.getRGB();
					img.setRGB(k, h, rgb);
				}
			}
		}
	}

	public void decodeimage(String filename) {
		try {
			// la méthode statique éread de la classe javax.imageio.ImageIO
			// renvoie
			// une instance de la classe BufferedImage (qui étend la classe
			// abstraite Image).
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pixel> selec(double seuilTinf, double seuilTsup,
			double seuilS, double seuilL1, double seuilL2) {
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
		System.out.println(Choisi.size());
		return Choisi;
	}

	public ArrayList<ArrayList<Pixel>> ComposantesConnexes(
			ArrayList<Pixel> choisi) {
		int i = 0;
		int L = Integer.MAX_VALUE;
		ArrayList<ArrayList<Pixel>> CC = new ArrayList<ArrayList<Pixel>>();
		ArrayList<Pixel> choisibis = choisi;
		int sizeChoisiBis = choisibis.size();

		for (int h = 0; h < sizeChoisiBis; h++) {
			Pixel pixel1 = choisibis.get(h);
			if (pixel1.pixelVoisinGauche(img, choisibis) != null
					&& pixel1.pixelVoisinHaut(img, choisibis) != null) {
				int a = pixel1.pixelVoisinGauche(img, choisibis)
						.getNumeroPixel();
				int b = pixel1.pixelVoisinHaut(img, choisibis).getNumeroPixel();
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
					// pour les tests
					// Color color = new Color(k*100, 250, 200-30*k);
					Color color = new Color(k * 20 % 255, 125 + (-1) ^ k * 10
							* k % 255, 255 - 20 * k % 255);
					int rgb = color.getRGB();
					img.setRGB(pixel3.getX(), pixel3.getY(), rgb);

					cc.add(pixel3);
				}
			}
			if (cc.size() > 50) { // on elimine les petites taches inutiles
				CC.add(cc);
			}
		}

		System.out.println(CC.size());
		return CC;
	}

	public ArrayList<ArrayList<Pixel>> ComposantesConnexes2(
			ArrayList<Pixel> choisi) {
		CompoConnexe compoConnexe = new CompoConnexe(choisi, img.getWidth(),
				img.getHeight());
		return compoConnexe.getCompo();
	}

	public ArrayList<ArrayList<Pixel>> Contours(ArrayList<ArrayList<Pixel>> CC,
			ArrayList<Pixel> choisi) {
		ArrayList<ArrayList<Pixel>> CONTOURS = new ArrayList<ArrayList<Pixel>>();

		for (ArrayList<Pixel> aa : CC) {
			ArrayList<Pixel> contours = new ArrayList<Pixel>();
			for (Pixel P : aa) {

				if (P.pixelVoisinDroite(img, choisi).BelongsTocc(aa) == false
						|| P.pixelVoisinGauche(img, choisi).BelongsTocc(aa) == false
						|| P.pixelVoisinBas(img, choisi).BelongsTocc(aa) == false
						|| P.pixelVoisinHaut(img, choisi).BelongsTocc(aa) == false) {
					// Pour le test:
					Color color = new Color(255, 0, 0);
					int rgb = color.getRGB();
					img.setRGB(P.getX(), P.getY(), rgb);
					contours.add(P);
				}

			}
			System.out.println(contours.size());
			CONTOURS.add(contours);
		}
		System.out.println(CONTOURS.size());
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

	public ArrayList<Point[]> SIGNATURE(ArrayList<ArrayList<Pixel>> CC) {

		ArrayList<Point[]> signDiscrete = new ArrayList<Point[]>();

		for (ArrayList<Pixel> cc : CC) {
			Point[] sign = new Point[72]; // 72=360/5 on échantillonne tous les
											// 5 degrés
			int seuil = 2;
			int Sx = 0;
			int Sy = 0;

			for (Pixel pixel : cc) {
				Sx = Sx + pixel.getX();
				Sy = Sy + pixel.getY();
			}

			Sx = (int) Sx / cc.size();
			Sy = (int) Sy / cc.size();

			for (int i = -180; i < 180; i = i + 5) {
				double r = 0;
				double theta = 0;
				for (Pixel pix : cc) {
					double a = pix.getY() - Sy;
					double b = pix.getX() - Sx;

					if (b > 0) {
						double c = a / b;
						theta = 360 / (2 * Math.PI) * Math.atan(c);

						if (theta < i + seuil && theta > i - seuil) {

							double R = Math.sqrt(Math.pow(a, 2)
									+ Math.pow(b, 2));
							r = Math.max(r, R);
						}
					}
					if (b < 0) {
						double c = a / b;
						if (a > 0) {
							theta = 180 + 360 / (2 * Math.PI) * Math.atan(c);
							if (theta < i + seuil && theta > i - seuil) {

								double R = Math.sqrt(Math.pow(a, 2)
										+ Math.pow(b, 2));
								r = Math.max(r, R);
							}
						}
						if (a < 0) {
							theta = -180 + 360 / (2 * Math.PI) * Math.atan(c);
							if (theta < i + seuil && theta > i - seuil) {

								double R = Math.sqrt(Math.pow(a, 2)
										+ Math.pow(b, 2));
								r = Math.max(r, R);
							}
						}
					} else if (a != 0 && b == 0) {
						int L = Integer.MAX_VALUE;
						int c = (int) (a / Math.abs(a));
						int d = c * L;
						theta = 360 / (2 * Math.PI) * Math.atan(d);

						if (theta < i + seuil && theta > i - seuil) {

							double R = Math.sqrt(Math.pow(a, 2)
									+ Math.pow(b, 2));
							r = Math.max(r, R);
						}
					} else {
					}
				}
				Point P = new Point((int) r, i);
				// P.setLocation(r,i);
				sign[i / 5 + 36] = P; // 36=180/5
			}

			// System.out.println(sign[55].getX());
			// System.out.println(sign[38].getY());
			signDiscrete.add(sign);

		}
		return signDiscrete;
	}

	public void testarctan() {
		double a = 360 / (2 * Math.PI) * Math.atan(1.02);
		System.out.println(a);
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

	public ArrayList<Complex[]> descripteursDeFourier(ArrayList<Point[]> COURBE) {
		FastFourierTransformer math = new FastFourierTransformer(
				DftNormalization.UNITARY);
		// XYSeries serie = this.audioData.getSeries(0);
		// XYSeries specSerie = new XYSeries("Spectrum");
		// double step = (double) sampleRate;
		// double x=0.;
		ArrayList<Complex[]> Coef = new ArrayList<Complex[]>();
		for (Point[] courbe : COURBE) {

			double[][] serie = new double[360][2];
			for (int j = 0; j < 360; j++) {
				serie[j][0] = courbe[j].getX();
				serie[j][1] = courbe[j].getY();
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
	 * est un carré ou un cercle ce qui nous donne les coef de fourier des
	 * signatures de référence on pourra ainsi comparer les descripteurs
	 * obtenus avec ceux de ref
	 * 
	 * il manque une méthode comparaison des descripteurs:
	 * planteRepéréeParLeCarré qui prend en entrée les descrip l'image
	 * l'arrayList<ArrayList<Pixel>> et qui renvoit une zone au dessus du
	 * stikers carré (éventuellement nulle)
	 */

	public boolean comparaisonDescripteursCarre(Complex[] test, float seuil) {
		boolean estCarre = false;
		double diff = 0;
		for (int i = 2; i < Math.min(test.length, refCarre.length); i++) { // commence
																			// é
																			// deux
																			// pour
																			// ne
																			// pas
																			// prendre
																			// en
																			// compte
																			// la
																			// taille
																			// du
																			// carre
			diff = diff + 1 / i * (Math.abs(test[i].abs() - refCarre[i].abs())) // 1/i
																				// signifie
																				// que
																				// les
																				// coeff
																				// de
																				// f
																				// les
																				// plus
																				// gd(loin)
																				// sont
																				// -
																				// imp
																				// ce
																				// st
																				// du
																				// detail
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

	public double moyenne() {
		double g = 0;
		for (int i = 0; i < this.signCarre.length; i++) {
			g = g + this.signCarre[i].getX();
		}
		g = g / this.signCarre.length;
		return g;
	}

	public boolean IsCarre(Point[] signature, double seuil) {
		boolean EstCarre = false;
		double C = 0;

		double f = 0; // moyenne de la signature
		for (int i = 0; i < signature.length; i++) {
			f = f + signature[i].getX();
		}
		f = f / signature.length;

		double g = 0; // moyenne de la signature témoin
		for (int i = 0; i < this.signCarre.length; i++) {
			g = g + this.signCarre[i].getX();
		}
		g = g / this.signCarre.length;

		double sigma1 = 0; // ecart type de la signature
		for (int i = 0; i < signature.length; i++) {
			sigma1 = sigma1 + Math.pow((signature[i].getX() - f), 2);
		}
		sigma1 = sigma1 / signature.length;
		sigma1 = Math.sqrt(sigma1);

		double sigma2 = 0; // ecart type de la signature témoin
		for (int i = 0; i < this.signCarre.length; i++) {
			sigma2 = sigma2 + Math.pow((this.signCarre[i].getX() - f), 2);
		}
		sigma2 = sigma2 / this.signCarre.length;
		sigma2 = Math.sqrt(sigma2);

		for (int k = 0; k < 72; k++) {
			double Ck = 0;
			for (int i = 0; i < 72; i++) {
				Ck = Ck + (signature[(i + k) % 72].getX() - f)
						* (this.signCarre[i].getX() - g);
			}
			Ck = Ck / (sigma1 * sigma2);
			// if (Ck<0){ Ck = - Ck ;} //je suis pas sér qu'il faille prendre
			// la val abs mais
			C = Math.max(Ck, C);
		}
		System.out.println("C vaut  " + C + "  le seuil est  " + seuil);
		if (C > seuil) {
			EstCarre = true;
		}

		return EstCarre;
	}

	public boolean IsRond(Point[] signature, double seuil) {
		boolean EstRond = false;
		double f = 0; // moyenne de la signature
		for (int i = 0; i < signature.length; i++) {
			f = f + signature[i].getX();
		}
		f = f / signature.length;

		double sigma1 = 0; // ecart type de la signature
		for (int i = 0; i < signature.length; i++) {
			sigma1 = sigma1 + Math.pow((signature[i].getX() - f), 2);
		}
		sigma1 = sigma1 / signature.length;
		sigma1 = Math.sqrt(sigma1);

		System.out.println("sigma vaut " + sigma1 + "   le seuil est " + seuil);
		if (sigma1 < seuil) {
			EstRond = true;
		}
		return EstRond;
	}

	public ArrayList<Rectangle> zonePlante(ArrayList<Point[]> Sign,
			ArrayList<ArrayList<Pixel>> compConn, double seuilCarre) {

		ArrayList<Rectangle> R = new ArrayList<Rectangle>();
		for (int i = 0; i < Sign.size(); i++) {

			if (this.IsCarre(Sign.get(i), seuilCarre)) {
				int xmax = -1;
				int xmin = Integer.MAX_VALUE;
				int ymax = 0;
				String carre = new String("carré");

				for (Pixel p : compConn.get(i)) {
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
				int delta = xmax - xmin;
				xmin = (int) xmin - delta / 2;
				int xgch = Math.max(xmin, 0);
				xmax = (int) xmax + delta / 2;
				int xim = img.getWidth() - 1;
				int xdt = Math.min(xmax, xim);

				System.out.println(xmin + "  " + xmax + "  " + ymax);
				Point p1 = new Point(xgch, 0);
				Point p2 = new Point(xgch, ymax);
				Point p3 = new Point(xdt, 0);
				Point p4 = new Point(xdt, ymax);
				Rectangle r = new Rectangle(p1, p2, p3, p4, carre);
				R.add(r);
			}
			/*
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
		} */
		}
		return R;

	}

	public void MiseEnEvidenceDuCarre(Rectangle R) {
		int xmin = (int) Math.round(R.getP1().x);
		int xmax = (int) Math.round(R.getP3().x);
		int ymax = (int) Math.round(R.getP3().y);
		int ymin = (int) Math.round(R.getP1().y);

		for (int i = xmin; i < xmax; i++) {
			Color color = new Color(255, 0, 0);
			int rgb = color.getRGB();
			img.setRGB(i, 0, rgb);
			img.setRGB(i, ymax, rgb);
		}
		for (int i = ymin; i < ymax; i++) {
			Color color = new Color(255, 0, 0);
			int rgb = color.getRGB();
			img.setRGB(xmin, i, rgb);
			img.setRGB(xmax, i, rgb);
		}
	}
}
