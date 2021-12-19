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
		String message = "";
		switch (type){
			case 1 :
				message = "You cannot choose an discretization stape inferior of 0.0009";
				break;
			default:
				message = "You cannot choose this!";
		}
		printErrorMessage(message);
	}

	/**
	 * Affiche le message d erreur.
	 * @param message message d erreur.
	 */
	public abstract void printErrorMessage(String message);
}