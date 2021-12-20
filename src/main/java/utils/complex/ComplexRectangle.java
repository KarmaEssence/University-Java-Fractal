package utils.complex;

import org.apache.commons.math3.complex.Complex;

public class ComplexRectangle {
    private Complex pointA;
    private Complex pointB;

    public ComplexRectangle(Integer x1, Integer y1, Integer x2, Integer y2){
        pointA = new Complex(x1, y1);
        pointB = new Complex(x2, y2);
    }

    public ComplexRectangle(Complex pointA, Complex pointB){
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Complex getPointA() {
        return pointA;
    }

    public Complex getPointB() {
        return pointB;
    }

    public double getWidth(){
        return Math.abs(pointA.getReal() - pointB.getReal());
    }

    public double getHeight(){
        return Math.abs(pointA.getImaginary() - pointB.getImaginary());
    }
}
