import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class MainClass {
	private BufferedImage img;

	
	public void decodeimage(String filename){	
		try{
			//la m�thode statique �read de la classe javax.imageio.ImageIO renvoie
			//une instance de la classe BufferedImage (qui �tend la classe abstraite Image).
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	public ArrayList<Pixel> selec(BufferedImage img, int seuilTinf,int seuilTsup, int seuilS){
		//selection des bons pixels
		ArrayList<Pixel> Choisi = null;
		
		for(int x = 0; x < img.getWidth(); x++){
        	for(int y = 0; y < img.getHeight(); y++){//parcourir l'image
        	
        		Color c = new Color(img.getRGB(x, y));//prendre des valeurs RGB de chaque pixel
            	int r = c.getRed();
            	int g = c.getGreen();
            	int b = c.getBlue();
            	
            	float[] hsb = Color.RGBtoHSB(r, g, b, null); //convertir RGB en HSB
		
            	if ( hsb[1]> seuilS ){
            		if (seuilTinf < hsb[0] && hsb[0]< seuilTsup){
            			Pixel P = new Pixel(x,y,hsb[0],hsb[1],hsb[2]);
            			Choisi.add(P);
            		}
            	}
        	}
		}	
            	
		return Choisi;
	}

	public ArrayList<ArrayList<Pixel>> ComposantesConnexes(ArrayList<Pixel> choisi, BufferedImage img){
		int i = 0;
		int L = Integer.MAX_VALUE;
		ArrayList<ArrayList<Pixel>> CC = new ArrayList<ArrayList<Pixel>>();
		CC = null;
		
		for(Pixel pixel1 : choisi){
			int a = pixel1.pixelVoisinGauche(img).getNumeroPixel();
			int b = pixel1.pixelVoisinHaut(img).getNumeroPixel();
			if (a == L && b==L){
				i++;
				pixel1.setNumeroPixel(i);
			} else if (b== a && a!= L){
				pixel1.setNumeroPixel(a);
			} else {
				pixel1.setNumeroPixel(Math.min(a,b));
				
				for(Pixel pixel2 : choisi){
					int c = pixel2.getNumeroPixel();
					
					if (c == Math.max(a, b) && Math.max(a, b)!= L){
						pixel2.setNumeroPixel(Math.min(a,b)) ;
					}	
				}
			}
		}
		
		for(int k=0; k<i ; k++){
			ArrayList<Pixel> cc = new ArrayList<Pixel>();
			for(Pixel pixel3 : choisi){
				if (pixel3.getNumeroPixel()==k){
					cc.add(pixel3);
				}
			}
			if (cc!=null){
				CC.add(cc);
			}
		}
		
		return CC;
	}
	
	public boolean BelongsTocc(Pixel pixel, ArrayList<Pixel> cc){
		boolean b= false;
		for (Pixel p: cc){
			if (pixel ==p){
				b= true;
			} 
		}
		return b;
	}
	
	public ArrayList<Pixel> Contours(BufferedImage img, ArrayList<ArrayList<Pixel>> CC, ArrayList<Pixel> cc){
		ArrayList<Pixel> contours=null;
		for (ArrayList<Pixel> aa: CC){
			for(Pixel P: cc){
	        		if (BelongsTocc(P, cc)==false){
	        			if (BelongsTocc(P.pixelVoisinDroite(img), cc)==true || BelongsTocc(P.pixelVoisinGauche(img), cc)==true || BelongsTocc(P.pixelVoisinHaut(img), cc)==true || BelongsTocc(P.pixelVoisinBas(img), cc)==true){
	        				contours.add(P);
	        			}
	        		}
	        }
		}
		return contours;
	}
	
}
	
