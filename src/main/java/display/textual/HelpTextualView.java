package display.textual;

import display.View;

public class HelpTextualView extends View {

    /**
     * Retourne le type de vue.
     * @return type de vue.
     */
    @Override
    public String typeOfView() {
        return "HelpTextual";
    }

    /**
     * Affichage de la page d aide
     */
    @Override
    public void print() {
        HostPage.print();
        HelpPage.print();
    }

    /**
     * Affiche le message d erreur
     * @param message message d erreur.
     */
    @Override
    public void printErrorMessage(String message) {
        System.out.println("");
    }
}
