package display.graphical;


import display.View;
import display.graphical.guihandler.GuiView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicalView extends View {

    /**
     * Affichage de la page d aide
     */
    @Override
    public void print() {
        Application.launch(GuiView.class);
    }

    /**
     * Retourne le type de vue.
     * @return type de vue.
     */
    @Override
    public String typeOfView() { return "Graphical"; }

    /**
     * Affiche le message d erreur
     * @param message message d erreur.
     */
    @Override
    public void printErrorMessage(String message) {
        System.out.println("");
    }
}
