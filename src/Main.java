import java.awt.Point;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		//mc.mainAlgo("cercle.png",0,1,1,1,1,1);
		
		//création d'un carré parfait
		
		// pour trouver les bons seuils
		//mc.decodeimage("testjaune.png");
		//mc.TSL(230, 150);
		
		//mc.testarctan();
		
		/*
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carré_et_rond_parfait");
		mc.decodeimage("carré_et_rond_parfait");
		
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("testsegmentation");
		// LA SEGMENTATION MARCHE
		
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("testCC");
		// LES COMPOSANTES CONNEXES MARCHENT
		*/
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carré_et_rond_parfait");
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		// SIGNATURE fonctionne !!!
		boolean rond = mc.IsRond(signature.get(1),0.05);
		System.out.println(rond);
		boolean carre = mc.IsRond(signature.get(0), 0.1);
		System.out.println(carre);
		// ESt rond à l'air de fonctionner à confirmer!
		
		/*
		ArrayList<ArrayList<Pixel>> Contours = mc.Contours(CC,choisi);
		mc.outPutImage("testContours");
		// LES CONTOURS MARCHENT*/
		
		
		
		/* *******
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0.1, 0.25, 0, 0 , 1);
		mc.outPutImage("resultat_poussin");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("poussinCC");
		*** */
	}

}
