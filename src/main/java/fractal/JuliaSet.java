package fractal;

import utils.complex.ComplexRectangle;
import org.apache.commons.math3.complex.Complex;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JuliaSet extends Fractal{

    public JuliaSet(Complex constant, ComplexRectangle complexRectangle, Double discretizationStape){
        super("z^2 + c", constant, complexRectangle, discretizationStape);
        super.setChoice = "julia";

    }

    private int getColorOfPixel(int a, int b){

        double complexX = -1 + discretizationStape * b;
        double complexY = 1 -  discretizationStape * a;
        Complex z = new Complex(complexX, complexY);
        int index = divergenceIndex(z , function);
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

    private void writeJuliaFractalOnImage(){
        Instant start = Instant.now();
        IntStream.range(0, image.getImageLength())
                .parallel()
                .forEach(a ->
                        IntStream.range(0, image.getImageHeight())
                        .parallel()
                                .forEach(b -> image.getImage().setRGB(b,a,getColorOfPixel(a, b))));

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
        image.saveFractal();
    }

    @Override
    public void makeFractal(){
        if(function != null && constant != null && complexRectangle != null){
            writeJuliaFractalOnImage();
        }
    }
}
