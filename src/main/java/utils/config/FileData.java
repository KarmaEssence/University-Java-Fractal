package utils.config;

import java.io.File;
import java.util.Objects;

public class FileData {

    /**
     * Verifie que le repertoire existe.
     */
    public static void directoryExist(String dirPath){
        String path = System.getProperty("user.dir");
        path+= dirPath;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    public static String giveNewFilename(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        int max = 0;
        for(int i = 0; i < Objects.requireNonNull(files).length; i++){
            String temp = files[i].getName().split("\\.")[0];
            int valueFile = Integer.parseInt(temp);
            if(valueFile > max)
                max = valueFile;
        }
        return String.valueOf(max + 1);
    }
}
