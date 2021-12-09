package Utils;

import org.apache.commons.math3.complex.Complex;

public class ComplexeRectangle {
    private Complex pointA;
    private Complex pointB;

    public ComplexeRectangle(Double x1, Double y1, Double x2, Double y2){
        pointA = new Complex(x1, y1);
        pointB = new Complex(x2, y2);
    }

    public Complex getPointA() {
        return pointA;
    }

    public Complex getPointB() {
        return pointB;
    }
}
