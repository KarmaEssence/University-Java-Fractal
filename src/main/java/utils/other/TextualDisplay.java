package utils.other;

public class TextualDisplay {

    /**
     * Produit des espaces vides.
     * @param n le nombre d espace a effectuer.
     */
    public static void printSpace(int n){
        for(int i=0;i<n;i++)System.out.print(" ");
    }

    /**
     * Affiche une ligne de separation.
     */
    public static void printSepator(){
        for(int i=0;i<79;i++)System.out.print("-");
        System.out.println();
    }
}
