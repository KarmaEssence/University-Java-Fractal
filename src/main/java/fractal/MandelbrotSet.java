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

    public Function<Complex, Complex> makeFunction(Complex constant) {
        return (Complex z1) -> constant.add(z1.multiply(z1));
    }

    private int getColorOfPixel(int a, int b){

        double complexX = -1 + discretizationStape * b;
        double complexY = 1 -  discretizationStape * a;
        Complex constant = new Complex(complexX, complexY);
        Complex z = new Complex(0, 0);
        int index = divergenceIndex(constant, z, makeFunction(constant));
        int color;

        //divergence
        if(index < MAX_ITER){
            color = ((index % 256) << 16) | (((index + 85) % 256) << 8) | ((index + 170) % 256);

            //convergence
        }else{
            color = (64 << 16) | (224 << 8) | 208;
        }
        return color;
    }

    private void writeMandelbrotFractalOnImage(){
        System.out.println("Je suis ici");
        Instant start = Instant.now();
        IntStream.range(0, image.getImageLength())
                .parallel()
                .forEach(a ->
                        IntStream.range(0, image.getImageHeight())
                                .parallel()
                                .forEach(b -> image.getImage().setRGB(b,a,getColorOfPixel(a,b))));

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
        image.saveFractal();
    }

    @Override
    public void makeFractal(){
        if(function != null && complexRectangle != null){
            writeMandelbrotFractalOnImage();
        }
    }
}
