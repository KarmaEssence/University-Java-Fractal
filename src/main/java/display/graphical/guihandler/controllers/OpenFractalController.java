package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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


    private void getImageFromFile(){
        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") +
                    "/data/fractal_image/" + filename.getText() + ".png");
            model.setWishImg(new Image(inputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getConfigFromFile(){
        System.out.println(System.getProperty("user.dir") + "/data/fractal_config/" + filename.getText() + ".json");
        JsonReader jr = JsonReader.createReaderInstance(System.getProperty("user.dir") + "/data/fractal_config/" + filename.getText() + ".json");
        assert jr != null;
        model.setFractalConfig((FractalConfig) jr.deserialize());
    }

    @Override
    public void initPage(Model model) {
        openButton.setOnAction(event -> {
            File file = new File(System.getProperty("user.dir") + "/data/fractal_image/" + filename.getText() + ".png");
            System.out.println(System.getProperty("user.dir") + "/data/fractal_image/" + filename.getText() + ".png");
            if(file.exists()){
                getImageFromFile();
                getConfigFromFile();
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

    @Override
    public void errorInPage(int error) {
        errorMessage.setText("Wrong filename");
        errorMessage.setFill(Color.RED);
    }
}
