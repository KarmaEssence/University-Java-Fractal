package utils.json;

import utils.json.annotation.JsonSerializable;
import utils.json.annotation.JsonSerializableType;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonWriter {

    private String filePath;
    private Map<String,Object> jsonMap;
    private String lastKey;


    public JsonWriter(String filePath)
    {
        filePath = Resources.checkExtensionOf(filePath,".json");
        if(Resources.checkIfFileExist(filePath)){
            Resources.createIfFileNotExist(filePath);
        }
        this.filePath = filePath;
        jsonMap = new LinkedHashMap<>();
    }

    /**
     * @return le chemin du fichier
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * Remplie la map.
     * @param key cle.
     * @param value valeur.
     */
    public void fillJsonMap(String key,Object value)
    {
        jsonMap.put(key,value);
        lastKey = key;
    }

    /**
     * Permet de serialiser un object
     * @param object l object a serialiser
     */
    public void serializeObject(Object object){
        Field[] fields = object.getClass().getFields();
        Resources.checkIfFileExist(filePath);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("{" + Utility.makeLineBreak(1));
            savePrimaryVar(object.getClass().getName(), writer, "classType", false, 4);
            for(Field field: fields){
                if(field.getAnnotation(JsonSerializable.class) == null ||
                        field.getAnnotation(JsonSerializable.class).getType().equals(JsonSerializableType.DeserialisableOnly))
                    continue;

                if(Utility.isPrimaryVar(field.get(object))) {
                    savePrimaryVar(field.get(object), writer, field.getName(), field == fields[fields.length-1], 4);
                }else if(field.get(object) instanceof List) {
                    saveList((List<Object>) field.get(object), writer, field.getName(), field == fields[fields.length-1], 4);
                }else if(field.get(object) instanceof Map){
                    saveMap((Map<String, Object>) field.get(object), writer, field.getName(), field == fields[fields.length-1], 4);
                }else{
                    saveSpecialObject(field.get(object), writer, field.getName(), field == fields[fields.length-1], 4);
                }
            }
            writer.write("}");
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sauvegarde la map dans le fichier
     * de configuration
     */
    public void saveMap()
    {
        Resources.checkIfFileExist(filePath);
        try{
            FileWriter writer = new FileWriter(filePath);
            writer.write("{" + Utility.makeLineBreak(1));
            Set<String> keys = jsonMap.keySet();
            for (String key: keys){
                if(Utility.isPrimaryVar(jsonMap.get(key))) {
                    savePrimaryVar(writer,key, 4);
                }else if(jsonMap.get(key) instanceof List) {
                    saveList((List<Object>) jsonMap.get(key), writer, key, key == keys.toArray()[keys.size() - 1], 4);
                }else if(jsonMap.get(key) instanceof Map){
                    saveMap((Map<String, Object>) jsonMap.get(key), writer, key, key == keys.toArray()[keys.size() - 1], 4);
                }else{
                    saveSpecialObject(jsonMap.get(key), writer, key, key == keys.toArray()[keys.size() - 1], 4);
                }
            }
            writer.write("}");
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    /**
     * Sauvegarde dans le fichier une variable primaire.
     * @param writer pour ecrire le contenu.
     * @param key cle courante.
     */
    private void savePrimaryVar(FileWriter writer, String key, int space) throws IOException {
        Object obj = jsonMap.get(key);
        if (obj instanceof String || obj instanceof Character){
            obj = "\"" + obj + "\"";
        }
        String comma = (key.equals(lastKey))?"":",";
        writer.write(Utility.makeSpace(space) + "\""+ key + "\": " + obj + comma + "\n");
    }

    /**
     * Sauvegarde dans le fichier une variable primaire.
     * @param obj object a sauvegarder
     * @param writer pour ecrire le contenu
     * @param key cle courante
     * @param isLastKey si c est le dernier à sauvegarder
     * @param space le nombre d espace
     */
    private void savePrimaryVar(Object obj, FileWriter writer, String key, boolean isLastKey, int space) throws IOException {
        if (obj instanceof String || obj instanceof Character){
            obj = "\"" + obj + "\"";
        }
        String comma = (isLastKey)?"":",";
        String displayKey = (!isUselessKey(key))? "\""+ key + "\": " : "";
        writer.write(Utility.makeSpace(space) + displayKey + obj + comma + "\n");
    }

    /**
     * Sauvegarde dans le fichier une liste d object.
     * @param obj une liste d object
     * @param writer pour ecrire le contenu
     * @param key cle courante
     * @param isLastKey si c est le dernier à sauvegarder
     * @param space le nombre d espace
     */
    private void saveList(List<Object> obj, FileWriter writer, String key, boolean isLastKey,int space) throws IOException, IllegalAccessException {

        writer.write(Utility.makeSpace(space)  + "\""+ key + "\": " + "[" + Utility.makeLineBreak(1));
        savePrimaryVar(obj.getClass().getName(), writer, "classType", false, space + 4);
        Object[] objects = obj.toArray();
        for(Object object: objects){
            if(Utility.isPrimaryVar(object)) {
                savePrimaryVar(object, writer, null,object == objects[objects.length-1], space + 8);
            }else if(object instanceof List) {
                saveList((List<Object>) object, writer, key, object == objects[objects.length-1], space + 4);
            }else if(object instanceof Map){
                saveMap((Map<String, Object>) object, writer, key, object == objects[objects.length-1], space + 4);
            }else{
                saveSpecialObject(object, writer, null, object == objects[objects.length-1], space + 8);
            }
        }
        String comma = (isLastKey)?"":",";
        writer.write(Utility.makeSpace(space)  +  "]" + comma + Utility.makeLineBreak(1));
    }

    /**
     * Sauvegarde dans le fichier une map d object.
     * @param obj une map d object
     * @param writer pour ecrire le contenu
     * @param key cle courante
     * @param isLastKey si c est le dernier à sauvegarder
     * @param space le nombre d espace
     */
    private void saveMap(Map<String, Object> obj, FileWriter writer, String key, boolean isLastKey,int space) throws IOException, IllegalAccessException {
        writer.write(Utility.makeSpace(space)  + "\""+ key + "\": " + "[" + Utility.makeLineBreak(1));
        savePrimaryVar(obj.getClass().getName(), writer, "classType", false, space + 4);
        String[] objects = (String[]) obj.keySet().toArray();
        for(String object: objects){
            if(Utility.isPrimaryVar(object)) {
                savePrimaryVar(obj.get(object), writer, object, object.equals(objects[objects.length - 1]), space + 8);
            }else if(obj.get(object) instanceof List) {
                saveList((List<Object>) obj.get(object), writer, key, object.equals(objects[objects.length - 1]), space + 4);
            }else if(obj.get(object) instanceof Map){
                saveMap((Map<String, Object>) obj.get(object), writer, key, object.equals(objects[objects.length - 1]), space + 4);
            }else{
                saveSpecialObject(object, writer, object, object.equals(objects[objects.length - 1]), space + 8);
            }
        }
        String comma = (isLastKey)?"":",";
        writer.write(Utility.makeSpace(space)  +  "]" + comma + Utility.makeLineBreak(1));
    }

    /**
     * Sauvegarde dans le fichier un object special.
     * @param obj une object special
     * @param writer pour ecrire le contenu
     * @param key cle courante
     * @param isLastKey si c est le dernier à sauvegarder
     * @param space le nombre d espace
     */
    private void saveSpecialObject(Object obj, FileWriter writer, String key, boolean isLastKey,int space) throws IOException, IllegalAccessException {
        Class getClass = obj.getClass();
        Field[] classField = getClass.getDeclaredFields();
        String displayKey = (!isUselessKey(key))? "\""+ key + "\": " : "";
        writer.write(Utility.makeSpace(space)  + displayKey + "{" + Utility.makeLineBreak(1));
        savePrimaryVar(obj.getClass().getName(), writer, "classType", false, space + 4);
        for(Field field: classField){
            if(Utility.isPrimaryVar(field.get(obj))){
                savePrimaryVar(field.get(obj),writer, field.getName(), classField[classField.length-1] == field, space + 4);
            }else{
                saveSpecialObject(field.get(obj), writer, field.getName(), classField[classField.length-1] == field,space + 4);
            }
        }
        String comma = (isLastKey)?"":",";
        writer.write(Utility.makeSpace(space) + "}" + comma + Utility.makeLineBreak(1));
    }

    /**
     * Regarde si la cle est inutile
     * @param key ligne courante
     * @return true si la ligne est inutile,
     * false sinon
     */
    private boolean isUselessKey(String key){
        return key == null || key.equals("{");
    }
}
