package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.config.FileData;
import utils.config.FractalConfig;
import utils.json.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OpenFractalController extends Controller {

    @FXML
    private Button openButton;

    @FXML
    private TextField filename;

    @FXML
    private Text errorMessage;

    @FXML
    private Button backButton;

    /**
     * Reinitialise les champs de texte
     */
    private void clearFields(){
        filename.clear();
        errorMessage.setText("");
    }

    /**
     * Initialise cette page
     * @param model modele de l interface graphique
     */
    @Override
    public void initPage(Model model) {
        openButton.setOnAction(event -> {
            File file = new File(System.getProperty("user.dir") + "/data/fractal_image/" + filename.getText() + ".png");
            if(file.exists()){
                FileData.getFractalConfig(model, filename.getText());
                filename.clear();
                errorMessage.setText("");
                model.changeScene("main");
            }else{
                errorInPage(1);
            }
        });

        backButton.setOnAction(event -> {
            filename.clear();
            errorMessage.setText("");
            model.changeScene("main");
        });

    }

    /**
     * Affiche une erreur
     * @param error code de l erreur
     */
    @Override
    public void errorInPage(int error) {
        errorMessage.setText("Wrong filename");
        errorMessage.setFill(Color.RED);
    }
}
