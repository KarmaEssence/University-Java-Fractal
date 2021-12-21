package utils.json;

import utils.other.CheckStringFormat;

import java.util.List;
import java.util.Map;

public class Utility {

    /**
     * Fais des espaces
     * @param n nombre d espace
     * @return Le nombre d espace necessaire
     */
    protected static String makeSpace(int n){
        return " ".repeat(Math.max(0, n));
    }

    /**
     * Fais des sauts de ligne.
     * @param n nombre de saut de ligne
     * @return nombre de saut de ligne necessaire
     */
    protected static String makeLineBreak(int n){
        return "\n".repeat(Math.max(0, n));
    }

    /**
     * Verifie si l objet est un objet primaire.
     * @param obj un objet.
     * @return true si obj est une instance d un objet primaire.
     */
    protected static boolean isPrimaryVar(Object obj){
        if(obj instanceof Number ||
           obj instanceof String ||
           obj instanceof Boolean ||
           obj instanceof Character){
            return true;
        }
        return false;
    }

    /**
     * Selectionne l objet ideal
     * @param firstVal a transformer en objet
     * @return renvoie l objet ideal
     */
    protected static Object findGoodObject(String firstVal){
        if(firstVal.length() == 1 && isCharacter(firstVal.charAt(0))){
            return firstVal.charAt(0);
        }else if(isDigit(firstVal)) {
            return Integer.valueOf(firstVal);
        }else if(CheckStringFormat.checkValue(firstVal)){
            return Double.valueOf(firstVal);
        }else if(firstVal.equals("true") || firstVal.equals("false")){
            return (firstVal.equals("true"));
        }else{
            return firstVal;
        }
    }

    /**
     * Verifie que l entier est un caractere
     * @param c un entier
     * @return true si l entier est un caractere, false sinon
     */
    public static boolean isCharacter(int c){
        return (c >= 58 && c <= 126) || (c >= 33 && c <= 47);
    }

    /**
     * Verifie que le string est une valeur
     * @param s un string
     * @return true si le string est une valeur, false sinon
     */
    public static boolean isDigit(String s){
        for(int i = 0; i < s.length(); i++){
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)){
                return false;
            }
        }
        return true;
    }

    /**
     * Verifie si le caractere est une majuscule
     * @param c le caractere
     * @return true si le caractere est une majuscule, false sinon
     */
    protected static boolean firstCharacterIsUpper(char c){
        return (c >= 65 && c <= 90);
    }
}
