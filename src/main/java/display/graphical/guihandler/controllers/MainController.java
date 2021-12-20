package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.config.FileData;
import utils.config.FractalConfig;

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
    private ScrollPane scrollPane;

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

    private DoubleProperty zoomProperty;

    private void displayFractalConfig(){
        FractalConfig config = model.getFractalConfig();
        setChoice.setText(config.setChoice);
        constanteX.setText(String.valueOf(config.constantX));
        constanteY.setText(String.valueOf(config.constantY));
        pointAX.setText(String.valueOf(config.pointAX));
        pointAY.setText(String.valueOf(config.pointAY));
        pointBX.setText(String.valueOf(config.pointBX));
        pointBY.setText(String.valueOf(config.pointBY));
        discretizationStape.setText(String.valueOf(config.discretizationStape));

    }

    private void clearTextField(){
        setChoice.setText("No Choosed");
        constanteX.setText("Undefined");
        constanteY.setText("Undefined");
        pointAX.setText("Undefined");
        pointAY.setText("Undefined");
        pointBX.setText("Undefined");
        pointBY.setText("Undefined");
        discretizationStape.setText("Undefined");
    }

    private void setZoomProperty(){
        zoomProperty = new SimpleDoubleProperty(200);

        zoomProperty.addListener(arg0 -> {
            fractalImage.setFitWidth(zoomProperty.get() * 4);
            fractalImage.setFitHeight(zoomProperty.get() * 3);
        });

        scrollPane.addEventFilter(ScrollEvent.ANY, event -> {
            if (event.getDeltaY() > 0) {
                zoomProperty.set(zoomProperty.get() * 1.1);
            } else if (event.getDeltaY() < 0) {
                zoomProperty.set(zoomProperty.get() / 1.1);
            }
        });

        fractalImage.preserveRatioProperty().set(true);
        scrollPane.setContent(fractalImage);
    }

    @Override
    public void initPage(Model model) {
        if(Controller.model == null){
            Controller.model = model;
        }

        if(model.getFractal() != null){
            FileData.getFractalConfig(model, FileData.getLastImageFileName(System.getProperty("user.dir") +
                    "/data/fractal_image"));
            model.setFractal(null);
        }

        if(model.getWishImg() != null && model.getFractalConfig() != null){
            displayFractalConfig();
            fractalImage.setImage(model.getWishImg());
            setZoomProperty();
        }

        newMenuItem.setOnAction(event -> {
            clearTextField();
            model.changeScene("newFractal");
        });

        openFractal.setOnAction(event ->{
            model.changeScene("openFractal");
        });

        closeFractal.setOnAction(event -> {
            clearTextField();
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
