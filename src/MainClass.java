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


	public static void main(String[] args) {
	}
	
}
	
