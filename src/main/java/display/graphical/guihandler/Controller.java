package display.graphical.guihandler;

import javafx.scene.layout.Pane;

public abstract class Controller{

    protected Pane panel;

    protected static Model model;

    protected static boolean isConnected;

    /**
     * Initialise une page
     * @param model modele de l interface graphique
     */
    public abstract void initPage(Model model);

    /**
     * Affiche une erreur
     * @param error code de l erreur
     */
    public abstract void errorInPage(int error);
}
