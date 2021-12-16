package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

public class MainController extends Controller {

    @FXML
    private Menu fileOptions;

    @FXML
    private MenuItem newMenuItem;


    @FXML
    private Menu helpOptions;

    @FXML
    private Label fractalParameters;

    @FXML
    private Font fractalParametersFont;

    @FXML
    private Label versionApp;

    @Override
    public void initPage(Model model) {
        newMenuItem.setOnAction(event -> {
            model.changeScene("newFractal");
            model.showScene();
        });
    }

    @Override
    public void errorInPage(int error) {

    }
}
