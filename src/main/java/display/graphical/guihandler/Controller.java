package display.graphical.guihandler;

import javafx.scene.layout.Pane;

public abstract class Controller{

    protected Pane panel;

    protected Model model;

    protected static boolean isConnected;

    public abstract void initPage(Model model);

    public abstract void errorInPage(int error);
}
