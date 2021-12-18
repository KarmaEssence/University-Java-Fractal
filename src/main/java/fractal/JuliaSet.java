package fractal;

import utils.complex.ComplexRectangle;
import org.apache.commons.math3.complex.Complex;

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
        z = new Complex(complexX, complexY);
        int index = divergenceIndex(function);
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
        /*for(int i = 0; i < image.getImageLength(); i++){
            for(int j = 0; j < image.getImageHeight(); j++){

                //calcul de l'indice de divergence
                double complexX = -1 + discretizationStape * j; // changer le -1 en fonction du point en bas à gauche du rectangle
                double complexY = 1 -  discretizationStape * i; // changer le -1 en fonction du point en bas à gauche du rectangle
                z = new Complex(complexX, complexY);
                int index = divergenceIndex(function);

                int color;
                //divergence
                if(index < MAX_ITER){
                    color = ((index % 256) << 16) | (((index + 85) % 256) << 8) | ((index + 170) % 256);

                //convergence
                }else{
                    int r = 64; int g = 224; int b = 208; //turquoise
                    color = (r << 16) | (g << 8) | b;
                }
                image.getImage().setRGB(j,i,color);
            }
        }*/

        /*IntStream.range(0, image.getImageLength())
                .parallel()
                .forEach(a ->
                        IntStream.range(0, image.getImageHeight())
                        .parallel()
                                .forEach(b -> {
                                    double complexX = -1 + discretizationStape * b;
                                    double complexY = 1 -  discretizationStape * a;
                                    z = new Complex(complexX, complexY);
                                    int index = divergenceIndex(function);
                                    int color;

                                    //divergence
                                    if(index < MAX_ITER){
                                        color = ((index % 256) << 16) | (((index + 85) % 256) << 8) | ((index + 170) % 256);

                                        //convergence
                                    }else{
                                        color = (64 << 16) | (224 << 8) | 208;
                                    }
                                    image.getImage().setRGB(b,a,color);
                                }));*/

        /*long startTime = System.nanoTime();

        int[][] results = IntStream.range(0, image.getImageLength())
                .parallel()
                .mapToObj(a ->
                        IntStream.range(0, image.getImageHeight())
                                .parallel()
                                .map(b -> getColorOfPixel(a, b))
                                .toArray())
                .toArray(int[][]::new);

        long endTime = System.nanoTime();
        System.out.println("Temps : " + (endTime - startTime) / 1000000);

        for(int i = 0; i < results.length; i++){
            for(int j = 0; j < results[i].length; j++){
                image.getImage().setRGB(i,j,results[i][j]);
            }
        }*/

        IntStream.range(0, image.getImageLength())
                .parallel()
                .forEach(a ->
                        IntStream.range(0, image.getImageHeight())
                        .parallel()
                                .forEach(b -> image.getImage().setRGB(b,a,getColorOfPixel(a, b))));

        image.saveFractal();
    }

    @Override
    public void makeFractal(){
        if(function != null && constant != null && complexRectangle != null){
            writeJuliaFractalOnImage();
        }
    }
}
