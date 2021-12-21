package fractal;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class MandelbrotSet extends Fractal{
    public MandelbrotSet(ComplexRectangle complexRectangle, Double discretizationStape) {
        super("z^2 + c", new Complex(0,0), complexRectangle, discretizationStape);
        super.setChoice = "mandelbrot";
    }

    /**
     * Construit la fractal s'il y a un rectangle,
     * une constante et une fonction
     **/
    @Override
    public void makeFractal(){
        if(function != null && complexRectangle != null){
            writeFractalOnImage();
        }
    }
}
