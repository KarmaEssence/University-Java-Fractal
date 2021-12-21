package display.textual;

import utils.other.TextualDisplay;

public class HelpPage {

    /**
     * Affiche la page d aide.
     */
    public static void print(){
        System.out.println("");
        TextualDisplay.printSepator();
        TextualDisplay.printSpace(24);
        System.out.println("Help page :");
        System.out.println();
        System.out.println("./gradlew run --args='shell <julia> <color> <constantRe> <constantIm> <pointARe> <pointAIm>");
        TextualDisplay.printSpace(2);
        System.out.println(" <pointBRe> <pointBIm> <discretizationStape>'");
        System.out.println();
        System.out.println("./gradlew run --args='shell <mandelbrot> <color> <pointARe> <pointAIm>");
        TextualDisplay.printSpace(2);
        System.out.println(" <pointBRe> <pointBIm> <discretizationStape>'");
        System.out.println();
        System.out.println("./gradlew run --args='graphical'");
        System.out.println();
        System.out.println("./gradlew run --args='test'");
        TextualDisplay.printSepator();
    }
}
