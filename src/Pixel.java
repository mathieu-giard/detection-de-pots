import java.awt.Color;
import java.awt.image.BufferedImage;


public class Pixel {

	private int x ;
	private int y ;
	private float T ;
	private float L ;
	private float S ;
	private int nP = 0;

	
	public Pixel(int x, int y, float T,float S, float L ){
		this.x=x;
		this.y=y;
		this.T=T;
		this.L=L;
		this.S=S;
	}
	
	public Pixel(int x, int y, BufferedImage img){
		

		Color c = new Color(img.getRGB(x, y));//prendre des valeurs RGB de chaque pixel
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		float[] hsb = Color.RGBtoHSB(r, g, b, null); //convertir RGB en HSB
		this.T = hsb[0];
		this.S = hsb[1];
		this.L = hsb[2];
		this.x = x;
		this.y = y;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	

	
	public Pixel pixelVoisinGauche(Pixel pixel, BufferedImage img){
		Pixel V = null;
		if (pixel.getX()!= 0){
			V = new Pixel(x-1,y,img);
		}
		return V;
	}
	
	public Pixel pixelVoisinDroite(Pixel pixel, BufferedImage img){
		Pixel V = null;
		if (pixel.getX()!= img.getTileWidth()){
			V = new Pixel(x+1,y,img);
		}
		return V;
	}
	
	public Pixel pixelVoisinHaut(Pixel pixel, BufferedImage img){
		Pixel V = null;
		if (pixel.getX()!= 0){
			V = new Pixel(x,y-1,img);
		}
		return V;
	}
	
	public Pixel pixelVoisinBas(Pixel pixel, BufferedImage img){
		Pixel V = null;
		if (pixel.getX()!= img.getHeight()){
			V = new Pixel(x,y+1,img);
		}
		return V;
	}
	
	public int getNumeroPixel(){
		return nP;
	}
}
