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
    private ImageView fractalImage;

    @FXML
    private Label versionApp;

    @Override
    public void initPage(Model model) {
        if(Controller.model == null){
            Controller.model = model;
        }

        newMenuItem.setOnAction(event -> {
            model.changeScene("newFractal");
        });

        openFractal.setOnAction(event ->{
            try {
                System.out.println("Test");
                System.out.println(System.getProperty("user.dir") + "/data/fractal_image/2.png");
                FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir") + "/data/fractal_image/1.png");
                fractalImage.setImage(new Image(inputstream));
                System.out.println(fractalImage.getFitHeight());
                System.out.println(fractalImage.getFitWidth());

                //Setting the position of the image
                /*fractalImage.setX(50);
                fractalImage.setY(25);*/

                //setting the fit height and width of the image view
                /*fractalImage.setFitHeight(455);
                fractalImage.setFitWidth(500);*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        closeFractal.setOnAction(event -> {
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
