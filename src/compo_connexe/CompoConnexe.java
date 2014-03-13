package compo_connexe;

import java.util.ArrayList;

import pactutils.Pixel;

public class CompoConnexe {

	ArrayList<ArrayList<Cross>> retour;
	Cross[][] tab;

	public CompoConnexe(Cross[][] tab) {
		this.tab = tab;
	}

	public CompoConnexe(ArrayList<? extends Cross> list, int maxX, int maxY) {
		tab = new Cross[maxX][maxY];
		for (Cross cross : list) {
			tab[cross.getX()][cross.getY()] = cross;
		}
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				if (cross == null) {
					cross = new Pixel(x, y, 0, 0, 0);
					cross.setNb(-2);
					tab[x][y] = cross;
				}
			}
		}
	}

	public ArrayList<ArrayList<Pixel>> getCompo() {
		int maxY = tab[0].length;
		int maxX = tab.length;

		ListeEqui listeEqui = new ListeEqui();

		int maxNb = 0;
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				if (cross.getNb() >= 0) {
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
		}

		ArrayList<ArrayList<Pixel>> retour = new ArrayList<ArrayList<Pixel>>();
		int sizeRetour = listeEqui.size();
		for (int i = 0; i < sizeRetour; i++)
			retour.add(new ArrayList<Pixel>());

		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				if (cross.getNb() >= 0) {
					int nbListe = listeEqui.getNumeroListe(cross.getNb());
					retour.get(nbListe).add((Pixel) cross);
				}
			}
		}

		return retour;
	}
}
