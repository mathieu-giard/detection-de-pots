import java.awt.Point;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		MainClass mc2 = new MainClass();
	
		
		
		// pour trouver les bons seuils
		//mc.decodeimage("testjaune.png");
		//mc.TSL(230, 150);
		
		
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
		
		// 2eme test
		mc.decodeimage("nimp.png");
		ArrayList<Pixel> choix = mc.selec(0, 360, 0, 0, 100);
		ArrayList<ArrayList<Pixel>> cc = mc.ComposantesConnexes(choix);
		mc.outPutImage("nimp_cc");
		//Les CC marchent
		
		
		ArrayList<ArrayList<Pixel>> Contours = mc.Contours(CC,choisi);
		mc.outPutImage("testContours");
		// LES CONTOURS MARCHENT*/
		
		
		/*
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carré_et_rond_parfait");
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		// SIGNATURE fonctionne !!! */
		/*
		boolean rond = mc.IsRond(signature.get(1),0.05);
		System.out.println(rond);
		boolean carre = mc.IsRond(signature.get(0), 0.1);
		System.out.println(carre);
		// ESt rond à l'air de fonctionner à confirmer!*/
		
		
		
		
		/*
		 * ne marche pas c'est un mystère CC ne revoit plus rien !!!!
		 * 
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarre();
		mc.outPutImage("carré_et_rond_parfait");
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		*/
		
		//initialisation
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carré_et_rond_parfait");
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		mc.setSignCarre(signature.get(0));
		// on a initialisé l'attribu signCarré qui va servir de référence
		
		
		/*
		mc2.decodeimage("pagevierge.png");
		mc2.CreationDeCarreEtRond();
		mc2.outPutImage("carré_et_rond_parfait");
		mc2.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi2 = mc2.selec(0, 360, 0, 0, 100);
		ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
		ArrayList<Point[]> signature2 = mc2.SIGNATURE(CC2);
		boolean rond = mc.IsCarre(signature2.get(1),0.9);
		System.out.println(rond);
		boolean carre = mc.IsCarre(signature2.get(0), 0.9);
		System.out.println(carre);
		// Marche avec une image parfaite */
		
		
		/*// test avec des image de synthèse non parfaite
		System.out.println("BEBUT");
		
		mc2.decodeimage("rondPaint.png");
		mc2.TSL(186	, 199);
		ArrayList<Pixel> choisi2 = mc2.selec(0.10, 0.2, 0.5, 0.1, 1.1);
		mc2.outPutImage("carré_segmentation");
		ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
		mc2.outPutImage("carré_compConn");
		ArrayList<Point[]> signature2 = mc2.SIGNATURE(CC2);
		mc2.ChangeCouleurZone(CC2.get(0));
		mc2.outPutImage("zoneDontOnTesteSiCEstUnCarre");
		boolean carré1 = mc.IsCarre(signature2.get(0), 0.8 *10);
		System.out.println(carré1);
		boolean rond1 = mc.IsRond(signature2.get(0), 0.4 *10);
		System.out.println(rond1);
		/*
		boolean carré2 = mc.IsCarre(signature2.get(1), 0.9);
		System.out.println(carré2);
		/*
		boolean carré3 = mc.IsCarre(signature.get(2), 0.9);
		System.out.println(carré3);
		boolean carré4 = mc.IsCarre(signature.get(3), 0.9);
		System.out.println(carré4);
		boolean carré5 = mc.IsCarre(signature.get(4), 0.9);
		System.out.println(carré5);
		boolean carré6 = mc.IsCarre(signature.get(5), 0.9);
		System.out.println(carré6);
		
		
		
		/* *******
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 *	
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarre();
		mc.outPutImage("carré_parfait");
		mc.decodeimage("carré_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("carrePSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("carreCC"); 
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		 * 
		 *
		 *
		mc.decodeimage("carré_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0.1, 0.25, 0, 0 , 1);
		mc.outPutImage("resultat_poussin");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("poussinCC");
		*** */
	/*
	mc.decodeimage("img-carré1.png");
	System.out.println("jaune du sticker ");
	mc.TSL(1329, 1740);
	System.out.println("jaune du canap");
	mc.TSL(2403, 1697);
	System.out.println("jaune des feuilles");
	mc.TSL(1367, 1274);
	
	mc2.decodeimage("img-carré2.png");
	System.out.println("jaune sticker");
	mc.TSL(1131, 1489);
	System.out.println("jaune feuilles");
	mc.TSL(1247, 883);
	*/
	
	/*
	mc2.decodeimage("img-carré3.png");
	System.out.println("jaune sticker");
	mc2.TSL(248, 347);
	System.out.println("jaune sticker");
	mc2.TSL(273, 355);
	System.out.println("jaune sticker");
	mc2.TSL(246, 373);
	System.out.println("jaune sticker");
	mc2.TSL(291, 334);
	System.out.println("jaune sticker");
	mc2.TSL(262, 397);
	// */
		
	// TEST GRANDEUR NATURE
	System.out.println("DEBUT TEST");
	
	mc2.decodeimage("img-carré3.png");
	ArrayList<Pixel> choisi2 = mc2.selec(0.85, 1.05, 0.05, 0.15, 0.4);
	mc2.outPutImage("img-carré3-segm");
	ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
	mc2.outPutImage("img-carré3-compConn");
	ArrayList<Point[]> signature2 = mc2.SIGNATURE(CC2);
	mc2.ChangeCouleurZone(CC2.get(0));
	mc2.ChangeCouleurZone2(CC2.get(1));
	mc2.ChangeCouleurZone2(CC2.get(2));
	mc2.ChangeCouleurZone2(CC2.get(3));
	mc2.ChangeCouleurZone2(CC2.get(4));
	mc2.ChangeCouleurZone2(CC2.get(5));
	mc2.ChangeCouleurZone2(CC2.get(6));
	mc2.ChangeCouleurZone2(CC2.get(7));
	mc2.outPutImage("zoneDontOnTesteSiCEstUnCarre");
	ArrayList<Rectangle> R = mc.zonePlante(signature2, CC2);
	mc2.MiseEnEvidenceDuCarre(R.get(0));
	mc2.outPutImage("zone_rendue");
	
	/*
	boolean carré1 = mc.IsCarre(signature2.get(0), 0.8 *10);
	System.out.println("carré1 "+carré1);
	boolean rond1 = mc.IsRond(signature2.get(0), 0.4 *10);
	System.out.println("rond1 " + rond1);
	boolean rond7 = mc.IsRond(signature2.get(6), 0.4 *10);
	// System.out.println("rond7 "+rond7);
	boolean carré2 = mc.IsCarre(signature2.get(1), 0.9*10);
	System.out.println("carré2 "+carré2);
	boolean carré3 = mc.IsCarre(signature2.get(2), 0.9*10);
	System.out.println("carré3 "+carré3);
	boolean carré4 = mc.IsCarre(signature2.get(3), 0.9*10);
	System.out.println("carré4 "+carré4);
	boolean carré5 = mc.IsCarre(signature2.get(4), 0.9*10);
	System.out.println("carré5"+carré5);
	boolean carré6 = mc.IsCarre(signature2.get(5), 0.9*10);
	System.out.println("carré6 " +carré6);
	boolean carré7 = mc.IsCarre(signature2.get(6), 0.8 *10);
	System.out.println("carré7 "+carré7);
	boolean carré8 = mc.IsCarre(signature2.get(7), 0.9*10);
	System.out.println("carré8 " +carré8);
	// */
	
	
	
	
	
	}

}
