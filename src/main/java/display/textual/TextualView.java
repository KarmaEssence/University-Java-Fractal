package display.textual;


import display.View;

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
	 * @param message message d erreur.
	 */
	@Override
	public void printErrorMessage(String message) {
		System.out.println(message);
	}
}