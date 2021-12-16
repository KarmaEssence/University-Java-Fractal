package display.graphical.guihandler;

import display.graphical.guihandler.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Model {
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

            /*FXMLLoader signinLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/signin.fxml")));
            listOfScene.put("signin", new Scene(signinLoader.load()));
            SignInController signinController = signinLoader.getController();
            controllers.add(signinController);

            FXMLLoader connectLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/connection.fxml")));
            listOfScene.put("connection", new Scene(connectLoader.load()));
            ConnectionController connectionController = connectLoader.getController();
            controllers.add(connectionController);

            FXMLLoader homeConnectionLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/homeConnected.fxml")));
            listOfScene.put("homeConnection", new Scene(homeConnectionLoader.load()));
            HomeConnectionController homeConnectionController = homeConnectionLoader.getController();
            controllers.add(homeConnectionController);

            FXMLLoader runModeLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/runMode.fxml")));
            listOfScene.put("runMode", new Scene(runModeLoader.load()));
            RunModeController runModeController = runModeLoader.getController();
            controllers.add(runModeController);

            FXMLLoader graphLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmldoc/graphs.fxml")));
            listOfScene.put("graph", new Scene(graphLoader.load()));
            GraphController graphController = graphLoader.getController();
            controllers.add(graphController);*/

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Scene getScene(String name){
        return listOfScene.get(name);
    }

    public Controller getController(String name){
        if(name.equals("main"))
            return controllers.get(0);
        /*if(name.equals("signin"))
            return controllers.get(1);
        if(name.equals("connection"))
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
