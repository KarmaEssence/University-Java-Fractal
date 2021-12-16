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
	public void print() {
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
	 * Affiche le message d erreur.
	 * @param title titre du message d erreur.
	 * @param message message d erreur.
	 */
	@Override
	public void printErrorMessage(String title, String message) {
		System.out.println(message);
	}
}