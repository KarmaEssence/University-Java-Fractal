package display;

public abstract class View{

	/**
	 * Retourne le type de vue choisie.
	 * @return type de vue
	 */
	public abstract String typeOfView();

	/**
	 * Affichage
	 */
	public abstract void print();

	/**
	 * Cree le message d erreur.
	 * @param type type du message d erreur.
	 */
	public void makeAnErrorMessage(int type){
		String message = switch (type) {
			case 1 -> "Please select a set between julia and mandelbrot";
			case 2 -> "Please select a color between blue and orange";
			case 3 -> "You cannot choose an discretization stape inferior of 0.009";
			case 4 -> "You cannot choose an discretization stape superior of 0.1";
			case 5 -> "Please choose two opposite points for the rectangle";
			case 6 -> "To discretization stape inferior/equals of 0.1, max dimensions are 10 x 10";
			case 7 -> "To discretization stape inferior/equals of 0.01, max dimensions are 5 x 5";
			case 8 -> "To discretization stape inferior/equals of 0.01, max dimensions are 2 x 2";
			default -> "You cannot choose this!";
		};
		printErrorMessage(message);
	}

	/**
	 * Affiche le message d erreur.
	 * @param message message d erreur.
	 */
	public abstract void printErrorMessage(String message);
}