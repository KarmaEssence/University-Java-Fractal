package display;

/**
 * Correspond au type d interface
 * choisie par l utilisateur.
 */
public abstract class View{

	/**
	 * Retourne le type de vue choisie.
	 * @return type de vue
	 */
	public abstract String typeOfView();

	/**
	 * Met a jour l affichage pour correspondre a celui
	 * du plateau courant.
	 */
	public abstract void print();

	/**
	 * Cree le message d erreur.
	 * @param type type du message d erreur.
	 */
	public void makeAnErrorMessage(int type){
		String title = "";
		String message = "";
		switch (type){
			default:
				title ="Change your value";
				message = "You cannot choose this!";
		}
		printErrorMessage(title,message);
	}

	/**
	 * Affiche le message d erreur.
	 * @param title titre du message d erreur.
	 * @param message message d erreur.
	 */
	public abstract void printErrorMessage(String title,String message);
}