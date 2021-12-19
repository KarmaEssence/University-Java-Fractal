package fractal;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MandelbrotSet extends Fractal{
    public MandelbrotSet(ComplexRectangle complexRectangle, Double discretizationStape) {
        super("z^2 + c", new Complex(0,0), complexRectangle, discretizationStape);
        super.setChoice = "mandelbrot";
    }

    @Override
    public void makeFractal(){
        if(function != null && complexRectangle != null){
            writeFractalOnImage();
        }
    }
}
