package utils.config;

import display.graphical.guihandler.Model;
import javafx.scene.image.Image;
import utils.json.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class FileData {

    /**
     * Verifie que le repertoire existe
     * et le cree dans le cas contraire.
     */
    public static void directoryExist(String dirPath){
        String path = System.getProperty("user.dir");
        path+= dirPath;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    /**
     * Recupere le nom du fichier le plus recent du repertoire.
     * @param path chemin du repertoire
     * @return retourne le nom de fichier
     */
    public static String getLastImageFileName(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        int max = 0;
        for(int i = 0; i < Objects.requireNonNull(files).length; i++){
            String temp = files[i].getName().split("\\.")[0];
            int valueFile = Integer.parseInt(temp);
            if(valueFile > max)
                max = valueFile;
        }
        return String.valueOf(max);
    }

    /**
     * Donne un nouveau nom de fichier à partir du dernier
     * connu
     * @param path chemin du repertoire
     * @return un nouveau nom de fichier à partir du dernier
     * connu
     */
    public static String giveNewFilename(String path){
        return String.valueOf(Integer.parseInt(getLastImageFileName(path)) + 1);
    }

    /**
     * Recupere une image
     * @param model modele de l interface graphique
     * @param filename nom du fichier
     */
    public static void getImageFromFile(Model model, String filename){
        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") +
                    "/data/fractal_image/" + filename + ".png");
            model.setWishImg(new Image(inputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupere la configuration de l image
     * @param model modele de l interface graphique
     * @param filename nom du fichier
     */
    public static void getConfigFromFile(Model model, String filename){
        JsonReader jr = JsonReader.createReaderInstance(System.getProperty("user.dir") + "/data/fractal_config/" + filename + ".json");
        assert jr != null;
        model.setFractalConfig((FractalConfig) jr.deserialize());
    }

    /**
     * Recupere l image et sa configuration
     * @param model modele de l interface graphique
     * @param filename nom du fichier
     */
    public static void getFractalConfig(Model model, String filename){
        getImageFromFile(model, filename);
        getConfigFromFile(model, filename);
    }
}
