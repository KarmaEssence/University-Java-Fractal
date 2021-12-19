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
        TextualDisplay.printSpace(12);
        System.out.println("This application was made by KarmaEssence and Ibbo");
        TextualDisplay.printSepator();
    }

}
