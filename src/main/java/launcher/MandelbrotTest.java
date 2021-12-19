package launcher;

import fractal.JuliaSet;
import fractal.MandelbrotSet;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;

public class MandelbrotTest {
    public static void mandelbrotTest2(){
        Complex c = new Complex(-(0.8), 0.156);
        ComplexRectangle cr = new ComplexRectangle(-1, 1, 1, -1);
        MandelbrotSet mandelbrotSet = new MandelbrotSet( cr, 0.001); //evitons les 4 zéros
        mandelbrotSet.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder(mandelbrotSet);
        builder.buildAndSave();
    }
}
