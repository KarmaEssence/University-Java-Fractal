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
        ComplexRectangle cr = new ComplexRectangle(-1, 1, 1, -1);
        JuliaSet juliaSet = new JuliaSet(c, cr, 0.001);
        juliaSet.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder("julia",c, cr, 0.001);
        builder.buildAndSave();
    }

    /**
     * Configuration 2
     */
    private static void julia2(){
        Complex c = new Complex(-(0.8), 0.156);
        ComplexRectangle cr = new ComplexRectangle(-2, 2, 2, -2);
        JuliaSet juliaSet = new JuliaSet(c, cr, 0.001);
        juliaSet.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder("julia",c, cr, 0.001);
        builder.buildAndSave();
    }

    /**
     * Configuration 3
     */
    private static void mandelbrot1(){
        ComplexRectangle cr = new ComplexRectangle(-2, 2, 2, -2);
        MandelbrotSet mandelbrotSet = new MandelbrotSet( cr, 0.001);
        mandelbrotSet.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder(mandelbrotSet);
        builder.buildAndSave();
    }

    public static void start(){
        julia1();
        julia2();
        mandelbrot1();
    }
}
