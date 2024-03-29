package launcher;

import fractal.JuliaSet;
import fractal.MandelbrotSet;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;

public class FractalLauncher {

    /**
     * Configuration 1
     */
    private static void julia1(){
        Complex c = new Complex(-(0.7269), 0.1889);
        ComplexRectangle cr = new ComplexRectangle(-1.0, 1.0, 1.0, -1.0);
        JuliaSet juliaSet = new JuliaSet("heat", c, cr, 0.001);
        juliaSet.makeFractal();
    }

    /**
     * Configuration 2
     */
    private static void julia2(){
        Complex c = new Complex(-(0.8), 0.156);
        ComplexRectangle cr = new ComplexRectangle(-2.0, 2.0, 2.0, -2.0);
        JuliaSet juliaSet = new JuliaSet("cold", c, cr, 0.001);
        juliaSet.makeFractal();
    }

    /**
     * Configuration 3
     */
    private static void mandelbrot1(){
        ComplexRectangle cr = new ComplexRectangle(-2.0, 2.0, 2.0, -2.0);
        MandelbrotSet mandelbrotSet = new MandelbrotSet("heat", cr, 0.001);
        mandelbrotSet.makeFractal();
    }

    public static void start(){
        julia1();
        julia2();
        mandelbrot1();
    }
}
