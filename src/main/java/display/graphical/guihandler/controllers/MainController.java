package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainController extends Controller {

    @FXML
    private Menu fileOptions;

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem openFractal;

    @FXML
    private MenuItem closeFractal;

    @FXML
    private MenuItem quitApp;

    @FXML
    private Menu helpOptions;

    @FXML
    private Label fractalParameters;

    @FXML
    private Font fractalParametersFont;

    @FXML
    private Text setChoice;

    @FXML
    private Text constanteX;

    @FXML
    private Text constanteY;

    @FXML
    private Text pointAX;

    @FXML
    private Text pointAY;

    @FXML
    private Text pointBX;

    @FXML
    private Text pointBY;

    @FXML
    private Text discretizationStape;


    @FXML
    private ImageView fractalImage;

    @FXML
    private Label versionApp;

    @Override
    public void initPage(Model model) {
        if(Controller.model == null){
            Controller.model = model;
        }

        if(model.getWishImg() != null && model.getFractalConfig() != null){
            fractalImage.setImage(model.getWishImg());
        }

        newMenuItem.setOnAction(event -> {
            model.changeScene("newFractal");
        });

        openFractal.setOnAction(event ->{
            model.changeScene("openFractal");
        });

        closeFractal.setOnAction(event -> {
            model.setWishImg(null);
            fractalImage.setImage(null);
        });


        quitApp.setOnAction(event -> {
            model.closeScene();
        });
    }


    @Override
    public void errorInPage(int error) {

    }
}
