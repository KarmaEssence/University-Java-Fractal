package display.graphical;


import display.View;
import display.graphical.guihandler.GuiView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Affichage du jeu sur la fenetre genere.
 * @version 1.0
 */
public class GraphicalView extends View {

    @Override
    public void print() {
        Application.launch(GuiView.class);
    }

    @Override
    public String typeOfView() { return "Graphical"; }

    @Override
    public void printErrorMessage(String message) {
        System.out.println("");
    }
}
