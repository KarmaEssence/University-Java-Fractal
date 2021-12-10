package utils.complex;
import org.apache.commons.math3.complex.Complex;

public class ComplexOperation {

    public static double makeModuleOperation(Complex c){
        return Math.sqrt(Math.pow(c.getReal(), 2) + Math.pow(c.getImaginary(), 2));
    }

    public static Complex makeCopieOf(Complex c){
        return new Complex(c.getReal(), c.getImaginary());
    }
}
