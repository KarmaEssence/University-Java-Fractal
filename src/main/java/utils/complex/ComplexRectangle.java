package utils.complex;

import org.apache.commons.math3.complex.Complex;

public class ComplexRectangle {
    private final Complex pointA;
    private final Complex pointB;

    public ComplexRectangle(Double x1, Double y1, Double x2, Double y2){
        pointA = new Complex(x1, y1);
        pointB = new Complex(x2, y2);
    }

    public ComplexRectangle(Complex pointA, Complex pointB){
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**
     * Recupere le point A
     * @return le point A
     */
    public Complex getPointA() {
        return pointA;
    }

    /**
     * Recupere le point A
     * @return le point A
     */
    public Complex getPointB() {
        return pointB;
    }

    /**
     * Recupere la largeur du rectangle
     * @return la largeur du rectangle
     */
    public double getWidth(){
        return Math.abs(pointA.getReal() - pointB.getReal());
    }

    /**
     * Recupere la hauteur du rectangle
     * @return la hauteur du rectangle
     */
    public double getHeight(){
        return Math.abs(pointA.getImaginary() - pointB.getImaginary());
    }
}
