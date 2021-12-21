package display.graphical.guihandler;

import display.graphical.guihandler.controllers.MainController;
import display.graphical.guihandler.controllers.NewFractalController;
import display.graphical.guihandler.controllers.OpenFractalController;
import fractal.Fractal;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.config.FractalConfig;

import java.io.IOException;
import java.util.*;

public class Model {
    private Fractal fractal;
    private Image wishImg;
    private FractalConfig fractalConfig;
    private final Stage currentStage;
    private final ArrayList<Scene> scenes;
    private final Map<String, Scene> listOfScene;
    private final List<Controller> controllers;

    public Model(){
        currentStage = new Stage();
        scenes = new ArrayList<>();
        listOfScene = new HashMap<>();
        controllers = new ArrayList<>();
        initController();

        currentStage.setTitle("University-Java-Fractal");
        currentStage.setResizable(false);
        currentStage.getIcons().add(Utility.getImageFromFolder("appLogo.png"));

    }

    /**
     * Ajoute les controllers a la liste de controller
     */
    private void initController(){
        try{
            FXMLLoader mainLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/main.fxml")));
            listOfScene.put("main", new Scene(mainLoader.load()));
            MainController mainController = mainLoader.getController();
            controllers.add(mainController);

            FXMLLoader newFractalLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/newFractal.fxml")));
            listOfScene.put("newFractal", new Scene(newFractalLoader.load()));
            NewFractalController newFractalController = newFractalLoader.getController();
            controllers.add(newFractalController);

            FXMLLoader openFractalLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/openFractal.fxml")));
            listOfScene.put("openFractal", new Scene(openFractalLoader.load()));
            OpenFractalController openFractalController = openFractalLoader.getController();
            controllers.add(openFractalController);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Recupere la fractale
     * @return la fractale
     */
    public Fractal getFractal() {
        return fractal;
    }

    /**
     * Etablie la fractale
     * @param fractal la fractale
     */
    public void setFractal(Fractal fractal) {
        this.fractal = fractal;
    }

    /**
     * Recupere l image
     * @return l image
     */
    public Image getWishImg() {
        return wishImg;
    }

    /**
     * Etablie l image
     * @param wishImg l image
     */
    public void setWishImg(Image wishImg) {
        this.wishImg = wishImg;
    }

    /**
     * Recupere une scene
     * @param name nom de la scene
     * @return la scene
     */
    public Scene getScene(String name){
        return listOfScene.get(name);
    }

    /**
     * Recupere une configuration
     * @return une configuration
     */
    public FractalConfig getFractalConfig() {
        return fractalConfig;
    }

    /**
     * Etablie une configuration
     * @param fractalConfig une configuration
     */
    public void setFractalConfig(FractalConfig fractalConfig) {
        this.fractalConfig = fractalConfig;
    }

    /**
     * Recupere un controller
     * @param name nom du controller
     * @return un controller
     */
    public Controller getController(String name){
        if(name.equals("main"))
            return controllers.get(0);
        if(name.equals("newFractal"))
            return controllers.get(1);
        if(name.equals("openFractal"))
            return controllers.get(2);
        return null;
    }

    /**
     * Affiche la scene courante
     */
    public void showScene(){
        currentStage.show();
    }

    /**
     * Ferme la scene courante
     */
    public void closeScene(){
        currentStage.close();
    }

    /**
     * Change la scene courante
     * @param name nom de la nouvelle scene
     */
    public void changeScene(String name){
        scenes.add(currentStage.getScene());
        getController(name).initPage(this);
        currentStage.setScene(getScene(name));
        currentStage.centerOnScreen();
        currentStage.show();
    }
}
