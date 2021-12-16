package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    @Override
    public void initPage(Model model) {
        openButton.setOnAction(event -> {
            File file = new File(System.getProperty("user.dir") + "/data/fractal_image/" + filename.getText() + ".png");
            System.out.println(System.getProperty("user.dir") + "/data/fractal_image/" + filename.getText() + ".png");
            if(file.exists()){
                try {
                    FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir") +
                            "/data/fractal_image/" + filename.getText() + ".png");
                    model.setWishImg(new Image(inputstream));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                filename.clear();
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
