package utils.other;

import display.GeneralView;
import launcher.Launcher;

import java.util.Locale;

public class CheckStringFormat {

    /**
     * Verifie que l ensemble choisi correspond a un ensemble existant
     * @param set l ensemble choisi
     * @return true si l ensemble choisi correspond a un ensemble existant,
     * false sinon
     */
    private static boolean checkChoosedSet(String set){
        set = set.toLowerCase(Locale.ROOT);
        return set.equals("julia") || set.equals("mandelbrot");
    }

    /**
     * Verifie que l ensemble choisi correspond a une couleur existante
     * @param color couleur choisi
     * @return true si la couleur choisi correspond a une couleur existante,
     * false sinon
     */
    private static boolean checkChoosedColor(String color){
        color = color.toLowerCase(Locale.ROOT);
        return color.equals("cold") || color.equals("heat");
    }

    /**
     * Verifie que les arguments soient correct
     * @param launcher la classe lançant le projet
     * @return true si les arguments sont correct,
     * false sinon
     */
    public static boolean checkShellArgs(Launcher launcher){
        String[] args = launcher.getArgs();
        if((args.length != 10 && args.length != 8)  || !args[0].equals("shell")
                || !checkChoosedSet(args[1])) return false;
        if(!checkChoosedColor(args[2])){
            launcher.setGeneralView(new GeneralView("shell"));
            launcher.getGeneralView().displayError(2);
            return false;
        }
        for(int i = 3; i < args.length; i++){
            if(!checkValue(args[i]))
                return false;
        }
        return true;
    }

    /**
     * Transforme les informations en coordonnee pour la grille.
     * @param s une chaine de caractere.
     * @return retourne le premier caractere
     * transforme en entier.
     */
    public static double stringIntoValue(String s){
        double res = Double.parseDouble(s);
        System.out.println(res);
        return res;
    }

    /**
     * Verification du format de la reponse entre par le joueur.
     * @param s une chaine de caractere.
     * @return true si la chaine de caractere
     * respecte le format,false sinon.
     */
    public static boolean checkArg(String s){
        if(s.length()!=1)return false;
        return ((s.charAt(0)>=49 && s.charAt(0)<=57)
                || (s.charAt(0)>=65 && s.charAt(0)<=90)
                || (s.charAt(0)>=97 && s.charAt(0)<=122));
    }

    /**
     * Verifie que le paramètre donne
     * contienne un entier entre 0 et 9.
     * @param s une chaine de caractere.
     * @return true si la chaine de caractere
     * respecte le format,false sinon.
     */
    public static boolean checkValue(String s){
        if(s.length()==0) return false;
        if(s.contains("E-")){
            int index = s.indexOf("E");
            s = s.substring(0, index);
        }
        if(s.charAt(0) != '-' && s.contains("-")) return false;
        if(s.chars().filter(ch -> ch == '.').count() == 2) return false;
        if(s.chars().filter(ch -> ch == '-').count() == 2) return false;
        if(s.charAt(0) == '.' || s.charAt(s.length()-1) == '.') return false;

        for(int i=0;i<s.length();i++){
            if(!((s.charAt(i)>47 && s.charAt(i)<58) || s.charAt(i) == '-') && s.charAt(i) != '.'){
                return false;
            }

        }
        return true;
    }
}
