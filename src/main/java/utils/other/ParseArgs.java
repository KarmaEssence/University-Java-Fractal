package utils.other;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class ParseArgs {

    public static Complex makeComplexe(String real, String imaginary){
        return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
    }

    public static ComplexRectangle makeRectangle(String pointAX, String pointAY,
                                           String pointBX, String pointBY){
        Complex pointA = makeComplexe(pointAX, pointAY);
        Complex pointB = makeComplexe(pointBX, pointBY);
        return new ComplexRectangle(pointA, pointB);
    }
}
