package utils.json;

import java.io.File;

public class Resources {

    /**
     * Cree le fichier
     * @param fileName nom du fichier a creer
     * @return true si le fichier a ete cree
     */
    public static boolean checkIfFileExist(String fileName)
    {
        File check = new File(fileName);
        return check.exists();
    }

    /**
     * Cree le fichier s il n existe pas
     * @param fileName nom du fichier
     */
    protected static void createIfFileNotExist(String fileName)
    {
        try{
            new File(fileName).createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * renvoie le nom du fichier terminant par une extension
     * @param fileName nom du fichier
     * @param typeOfFile extension du fichier
     * @return le nom du fichier terminant par une extension
     */
    protected static String checkExtensionOf(String fileName,String typeOfFile){
        if(!fileName.endsWith(typeOfFile))
            return fileName + typeOfFile;
        return fileName;
    }


}
