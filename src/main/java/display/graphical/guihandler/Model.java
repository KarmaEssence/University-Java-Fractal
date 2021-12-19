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

    public Fractal getFractal() {
        return fractal;
    }

    public void setFractal(Fractal fractal) {
        this.fractal = fractal;
    }

    public Image getWishImg() {
        return wishImg;
    }

    public void setWishImg(Image wishImg) {
        this.wishImg = wishImg;
    }

    public Scene getScene(String name){
        return listOfScene.get(name);
    }

    public FractalConfig getFractalConfig() {
        return fractalConfig;
    }

    public void setFractalConfig(FractalConfig fractalConfig) {
        this.fractalConfig = fractalConfig;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void moveStageOnCenterOfPage(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
        currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
    }

    public Controller getController(String name){
        if(name.equals("main"))
            return controllers.get(0);
        if(name.equals("newFractal"))
            return controllers.get(1);
        if(name.equals("openFractal"))
            return controllers.get(2);
        return null;
    }

    public void showScene(){
        currentStage.show();
    }

    public void closeScene(){
        currentStage.close();
    }

    public void changeScene(String name){
        scenes.add(currentStage.getScene());
        getController(name).initPage(this);
        currentStage.setScene(getScene(name));
        currentStage.centerOnScreen();
        currentStage.show();
    }
}
