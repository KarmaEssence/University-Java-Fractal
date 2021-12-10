package display.textual;


import display.View;

/**
 * Affichage du jeu sur le terminal.
 * @version 1.0
 */
public class TextualView extends View {

	/**
	 * Affiche la page d accueil.
	 */
	@Override
	public void printHostPage() {
		HostPage.print();
	}

	/**
	 * Retourne le type de vue.
	 * @return type de vue.
	 */
	@Override
	public String typeOfView(){
		return "Textual";
	}

	/**
	 * Met a jour l affichage pour correspondre a celui
	 * du plateau courant.
	 */
	@Override
	public void print(){}

	/**
	 * Fais un resume du niveau, en fonction du boolean
	 * affiche different type de resume.
	 * @param bool pour choisir le type de description.
	 */
	@Override
	public void summarize(boolean bool){}

	/**
	 * Affiche le message d erreur.
	 * @param title titre du message d erreur.
	 * @param message message d erreur.
	 */
	@Override
	public void printErrorMessage(String title, String message) {
		System.out.println(message);
	}
}