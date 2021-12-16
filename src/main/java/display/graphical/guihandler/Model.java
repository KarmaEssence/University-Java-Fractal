package display.graphical.guihandler;

import display.graphical.guihandler.controllers.MainController;
import display.graphical.guihandler.controllers.NewFractalController;
import fractal.Fractal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Model {
    private Fractal fractal;
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
        //currentStage.getIcons().add(Utility.getImageFromFolder("calculator_icone.png"));

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

    public Scene getScene(String name){
        return listOfScene.get(name);
    }

    public Controller getController(String name){
        if(name.equals("main"))
            return controllers.get(0);
        if(name.equals("newFractal"))
            return controllers.get(1);
        /*if(name.equals("connection"))
            return controllers.get(2);
        if(name.equals("homeConnection"))
            return controllers.get(3);
        if(name.equals("runMode"))
            return controllers.get(4);
        if(name.equals("graph"))
            return controllers.get(5);*/
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
    }

    /*public void initUser(String pseudo, String password, String mail){
        user = new User(pseudo, password, mail);
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }*/
}
