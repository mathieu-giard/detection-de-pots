import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		//mc.mainAlgo("cercle.png",0,1,1,1,1,1);
		
		//cr�ation d'un carr� parfait
		
		
		
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carr�_et_rond_parfait");
		mc.decodeimage("carr�_et_rond_parfait");
		
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("testsegmentation");
		// LA SEGMENTATION MARCHE
		
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("testCC");
		// LES COMPOSANTES CONNEXES MARCHENT
		
		ArrayList<ArrayList<Pixel>> Contours = mc.Contours(CC,choisi);
		mc.outPutImage("testContours");
		
	}

}
