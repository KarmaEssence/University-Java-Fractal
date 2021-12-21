package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;
import utils.config.FileData;
import utils.config.FractalConfig;

public class MainController extends Controller {

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
    private Text setChoice;

    @FXML
    private Text colorChoice;

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

    private static final double INITIAL_FIT_WIDTH = 880;
    private static final double INITIAL_FIT_HEIGHT = 660.0;

    private DoubleProperty zoomProperty;

    /**
     * Affiche la fractal
     */
    private void displayFractalConfig(){
        FractalConfig config = model.getFractalConfig();
        setChoice.setText(config.setChoice);
        colorChoice.setText(config.colorChoice);
        constanteX.setText(String.valueOf(config.constantX));
        constanteY.setText(String.valueOf(config.constantY));
        pointAX.setText(String.valueOf(config.pointAX));
        pointAY.setText(String.valueOf(config.pointAY));
        pointBX.setText(String.valueOf(config.pointBX));
        pointBY.setText(String.valueOf(config.pointBY));
        discretizationStape.setText(String.valueOf(config.discretizationStape));

    }

    /**
     * Reinitialise les champs de texte
     */
    private void clearTextField(){
        setChoice.setText("No Choosed");
        colorChoice.setText("No Choosed");
        constanteX.setText("Undefined");
        constanteY.setText("Undefined");
        pointAX.setText("Undefined");
        pointAY.setText("Undefined");
        pointBX.setText("Undefined");
        pointBY.setText("Undefined");
        discretizationStape.setText("Undefined");
    }

    /**
     * Permet de borner l utilisation du zoom
     * @return true si le zoom peut s effectuer,
     * false sinon
     */
    private boolean checkZoomPropertyCondition(){
        return zoomProperty.get() * 4 >= INITIAL_FIT_WIDTH &&
                zoomProperty.get() * 3 >= INITIAL_FIT_HEIGHT &&
                zoomProperty.get() * 4 < 100000 && zoomProperty.get() * 3 < 100000;
    }

    /**
     * Met en place le zoom
     */
    private void setZoomProperty(){
        fractalImage.setFitWidth(INITIAL_FIT_WIDTH);
        fractalImage.setFitHeight(INITIAL_FIT_HEIGHT);
        zoomProperty = new SimpleDoubleProperty(200);

        zoomProperty.addListener(arg0 -> {
            if(checkZoomPropertyCondition()){
                fractalImage.setFitWidth(zoomProperty.get() * 4);
                fractalImage.setFitHeight(zoomProperty.get() * 3);
            }
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

    /**
     * Initialise cette page
     * @param model modele de l interface graphique
     */
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

    /**
     * Affiche une erreur
     * @param error code de l erreur
     */
    @Override
    public void errorInPage(int error) {}
}
