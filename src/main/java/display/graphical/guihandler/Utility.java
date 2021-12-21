package display.graphical.guihandler;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.*;

public class Utility {

    /**
     * Recupere une image depuis un repertoire
     * @param name nom du repertoire
     * @return une image
     */
    public static Image getImageFromFolder(String name) {
        Image image = null;
        try {
            FileInputStream fileIcon = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/icone/" + name);
            image = new Image(fileIcon);
            fileIcon.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
