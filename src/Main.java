import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		//mc.mainAlgo("cercle.png",0,1,1,1,1,1);
		mc.decodeimage("carré5.png");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("carré5");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		
	}

}
