package utils.complex;
import org.apache.commons.math3.complex.Complex;

public class ComplexOperation {

    /**
     * Fais le calcul du module pour un complexe
     * @param c un complexe
     * @return le module du complexe choisi
     */
    public static double makeModuleOperation(Complex c){
        return Math.sqrt(Math.pow(c.getReal(), 2) + Math.pow(c.getImaginary(), 2));
    }

    /**
     * Fais une copie du complexe
     * @param c un complexe
     * @return la copie du complexe
     */
    public static Complex makeCopieOf(Complex c){
        return new Complex(c.getReal(), c.getImaginary());
    }
}
