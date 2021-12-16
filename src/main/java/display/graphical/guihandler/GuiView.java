package display.graphical.guihandler;

import javafx.application.Application;
import javafx.stage.Stage;

public class GuiView extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        model.changeScene("home");
        model.showScene();
    }
}
