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
		mc.outPutImage("carr�_et_rond_parfait");
		mc.decodeimage("carr�_et_rond_parfait");
		
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
		mc.outPutImage("carr�_et_rond_parfait");
		mc.decodeimage("carr�_et_rond_parfait");
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
		// ESt rond � l'air de fonctionner � confirmer!*/
		
		
		
		
		/*
		 * ne marche pas c'est un myst�re CC ne revoit plus rien !!!!
		 * 
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarre();
		mc.outPutImage("carr�_et_rond_parfait");
		mc.decodeimage("carr�_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		*/
		
		//initialisation
		mc.decodeimage("pagevierge.png");
		mc.CreationDeCarreEtRond();
		mc.outPutImage("carr�_et_rond_parfait");
		mc.decodeimage("carr�_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("rondPSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("rondCC");
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		mc.setSignCarre(signature.get(0));
		// on a initialis� l'attribu signCarr� qui va servir de r�f�rence
		
		
		/*
		mc2.decodeimage("pagevierge.png");
		mc2.CreationDeCarreEtRond();
		mc2.outPutImage("carr�_et_rond_parfait");
		mc2.decodeimage("carr�_et_rond_parfait");
		ArrayList<Pixel> choisi2 = mc2.selec(0, 360, 0, 0, 100);
		ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
		ArrayList<Point[]> signature2 = mc2.SIGNATURE(CC2);
		boolean rond = mc.IsCarre(signature2.get(1),0.9);
		System.out.println(rond);
		boolean carre = mc.IsCarre(signature2.get(0), 0.9);
		System.out.println(carre);
		// Marche avec une image parfaite */
		
		
		/*// test avec des image de synth�se non parfaite
		System.out.println("BEBUT");
		
		mc2.decodeimage("rondPaint.png");
		mc2.TSL(186	, 199);
		ArrayList<Pixel> choisi2 = mc2.selec(0.10, 0.2, 0.5, 0.1, 1.1);
		mc2.outPutImage("carr�_segmentation");
		ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
		mc2.outPutImage("carr�_compConn");
		ArrayList<Point[]> signature2 = mc2.SIGNATURE(CC2);
		mc2.ChangeCouleurZone(CC2.get(0));
		mc2.outPutImage("zoneDontOnTesteSiCEstUnCarre");
		boolean carr�1 = mc.IsCarre(signature2.get(0), 0.8 *10);
		System.out.println(carr�1);
		boolean rond1 = mc.IsRond(signature2.get(0), 0.4 *10);
		System.out.println(rond1);
		/*
		boolean carr�2 = mc.IsCarre(signature2.get(1), 0.9);
		System.out.println(carr�2);
		/*
		boolean carr�3 = mc.IsCarre(signature.get(2), 0.9);
		System.out.println(carr�3);
		boolean carr�4 = mc.IsCarre(signature.get(3), 0.9);
		System.out.println(carr�4);
		boolean carr�5 = mc.IsCarre(signature.get(4), 0.9);
		System.out.println(carr�5);
		boolean carr�6 = mc.IsCarre(signature.get(5), 0.9);
		System.out.println(carr�6);
		
		
		
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
		mc.outPutImage("carr�_parfait");
		mc.decodeimage("carr�_parfait");
		ArrayList<Pixel> choisi = mc.selec(0, 360, 0, 0, 100);
		mc.outPutImage("carrePSegm");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("carreCC"); 
		ArrayList<Point[]> signature = mc.SIGNATURE(CC);
		 * 
		 *
		 *
		mc.decodeimage("carr�_et_rond_parfait");
		ArrayList<Pixel> choisi = mc.selec(0.1, 0.25, 0, 0 , 1);
		mc.outPutImage("resultat_poussin");
		ArrayList<ArrayList<Pixel>> CC=mc.ComposantesConnexes(choisi);
		mc.outPutImage("poussinCC");
		*** */
	/*
	mc.decodeimage("img-carr�1.png");
	System.out.println("jaune du sticker ");
	mc.TSL(1329, 1740);
	System.out.println("jaune du canap");
	mc.TSL(2403, 1697);
	System.out.println("jaune des feuilles");
	mc.TSL(1367, 1274);
	
	mc2.decodeimage("img-carr�2.png");
	System.out.println("jaune sticker");
	mc.TSL(1131, 1489);
	System.out.println("jaune feuilles");
	mc.TSL(1247, 883);
	*/
	
	/*
	mc2.decodeimage("img-carr�3.png");
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
	
	mc2.decodeimage("img-carr�3.png");
	ArrayList<Pixel> choisi2 = mc2.selec(0.85, 1.05, 0.05, 0.15, 0.4);
	mc2.outPutImage("img-carr�3-segm");
	ArrayList<ArrayList<Pixel>> CC2=mc2.ComposantesConnexes(choisi2);
	mc2.outPutImage("img-carr�3-compConn");
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
	boolean carr�1 = mc.IsCarre(signature2.get(0), 0.8 *10);
	System.out.println("carr�1 "+carr�1);
	boolean rond1 = mc.IsRond(signature2.get(0), 0.4 *10);
	System.out.println("rond1 " + rond1);
	boolean rond7 = mc.IsRond(signature2.get(6), 0.4 *10);
	// System.out.println("rond7 "+rond7);
	boolean carr�2 = mc.IsCarre(signature2.get(1), 0.9*10);
	System.out.println("carr�2 "+carr�2);
	boolean carr�3 = mc.IsCarre(signature2.get(2), 0.9*10);
	System.out.println("carr�3 "+carr�3);
	boolean carr�4 = mc.IsCarre(signature2.get(3), 0.9*10);
	System.out.println("carr�4 "+carr�4);
	boolean carr�5 = mc.IsCarre(signature2.get(4), 0.9*10);
	System.out.println("carr�5"+carr�5);
	boolean carr�6 = mc.IsCarre(signature2.get(5), 0.9*10);
	System.out.println("carr�6 " +carr�6);
	boolean carr�7 = mc.IsCarre(signature2.get(6), 0.8 *10);
	System.out.println("carr�7 "+carr�7);
	boolean carr�8 = mc.IsCarre(signature2.get(7), 0.9*10);
	System.out.println("carr�8 " +carr�8);
	// */
	
	
	
	
	
	}

}
