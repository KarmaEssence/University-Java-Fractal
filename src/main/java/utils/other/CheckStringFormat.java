package utils.other;

public class CheckStringFormat {

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
        if(s.charAt(0) != '-' && s.contains("-")) return false;

        for(int i=0;i<s.length();i++){
            if(!((s.charAt(i)>47 && s.charAt(i)<58) || s.charAt(i) == '.' || s.charAt(i) == '-'))
                return false;
        }
        return true;
    }
}
