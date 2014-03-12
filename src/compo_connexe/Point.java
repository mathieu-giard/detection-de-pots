package compo_connexe;
import java.util.ArrayList;

public class Point implements Cross {
	int x, y;
	int nb = -1;
	int nbGr = -1;

	public Point(int x, int y, int nbGr) {
		this.x = x;
		this.y = y;
		this.nbGr = nbGr;
	}

	public Point(int nbGr) {
		this.nbGr = nbGr;
	}

	@Override
	public ArrayList<Cross> getVoisin(Cross[][] tab) {
		ArrayList<Cross> retour = new ArrayList<Cross>();
		if (x == 0 && y == 0)
			;
		else if (x == 0) {
			if (tab[x][y - 1].getNbGr() == this.nbGr)
				retour.add(tab[x][y - 1]);
		} else if (y == 0) {
			if (tab[x - 1][y].getNbGr() == this.nbGr)
				retour.add(tab[x - 1][y]);
		} else {
			if (tab[x][y - 1].getNbGr() == this.nbGr)
				retour.add(tab[x][y - 1]);
			if (tab[x - 1][y].getNbGr() == this.nbGr)
				retour.add(tab[x - 1][y]);
		}

		return retour;
	}

	/************** getters *************/

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNbGr() {
		return nbGr;
	}
}
