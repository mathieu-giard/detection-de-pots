package compo_connexe;

import java.util.ArrayList;

public interface Cross {
	// interface de pixel

	public int getX();

	public int getY();

	/*
	 * les voisuins devriont etre initialises ET etre de la meme categorie
	 */
	public ArrayList<Cross> getVoisin(Cross[][] tab);

	public int getNb();

	public void setNb(int nb);

	public boolean getSelectionne();

	public void setSelectionne(boolean selectionne);
}
