package compo_connexe;
import java.util.ArrayList;

public class CompoConnexe {

	ArrayList<ArrayList<Cross>> retour;
	Cross[][] tab;

	public CompoConnexe(Cross[][] tab) {
		this.tab = tab;
	}

	public CompoConnexe(ArrayList<Cross> list, int maxX, int maxY) {
		Cross tab[][] = new Cross[maxX][maxY];
		for (Cross cross : list) {
			tab[cross.getX()][cross.getY()] = cross;
		}
	}

	public ArrayList<ArrayList<Cross>> getCompo() {
		int maxY = tab.length;
		int maxX = tab[0].length;

		ListeEqui listeEqui = new ListeEqui();

		int maxNb = 0;
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				cross.setNb(maxNb);

				ArrayList<Cross> voisins = cross.getVoisin(tab);
				if (!voisins.isEmpty()) {
					cross.setNb(voisins.get(0).getNb());
					ArrayList<Integer> aAjouter = new ArrayList<Integer>();
					for (Cross voisin : voisins)
						aAjouter.add(voisin.getNb());
					listeEqui.add(aAjouter);
				} else {
					cross.setNb(maxNb);
					ArrayList<Integer> aAjouter = new ArrayList<Integer>();
					aAjouter.add(maxNb);
					listeEqui.add(aAjouter);
					maxNb++;
				}
			}
		}

		ArrayList<ArrayList<Cross>> retour = new ArrayList<ArrayList<Cross>>();
		int sizeRetour = listeEqui.size();
		for (int i = 0; i < sizeRetour; i++)
			retour.add(new ArrayList<Cross>());

		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				int nbListe = listeEqui.getNumeroListe(cross.getNb());
				retour.get(nbListe).add(cross);
			}
		}

		return retour;
	}
}
