package display.textual;

import utils.other.TextualDisplay;

/**
 * Page d accueil.
 * @version 1.0
 */
public class HostPage {

    /**
     * Affiche la page d accueil.
     */
    public static void print(){
        TextualDisplay.printSepator();
        TextualDisplay.printSpace(24);
        System.out.println("Welcome to the Fractal project");
        System.out.println();
        TextualDisplay.printSpace(18);
        System.out.println("This application was made by Léo and Kévin");
        TextualDisplay.printSepator();
    }

}
