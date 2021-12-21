package fractal;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class MandelbrotSet extends Fractal{
    public MandelbrotSet(ComplexRectangle complexRectangle, Double discretizationStape) {
        super("z^2 + c", new Complex(0,0), complexRectangle, discretizationStape);
        super.setChoice = "mandelbrot";
    }

    public MandelbrotSet(String color, ComplexRectangle complexRectangle, Double discretizationStape) {
        super("z^2 + c", new Complex(0,0), complexRectangle, discretizationStape);
        super.setChoice = "mandelbrot";
        super.colorChoice = color;
    }

    /**
     * Construit la fractal s il y a un rectangle
     * et une fonction
     **/
    @Override
    public void makeFractal(){
        if(function != null && complexRectangle != null){
            writeFractalOnImage();
        }
    }
}
