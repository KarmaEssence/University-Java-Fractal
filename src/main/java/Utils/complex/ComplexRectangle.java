package Utils.complex;

import org.apache.commons.math3.complex.Complex;

public class ComplexRectangle {
    private Complex pointA;
    private Complex pointB;

    public ComplexRectangle(Integer x1, Integer y1, Integer x2, Integer y2){
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
