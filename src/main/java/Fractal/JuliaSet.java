package Fractal;

import Utils.ComplexeImg;
import Utils.ComplexeRectangle;
import org.apache.commons.math3.complex.Complex;

import java.util.function.Function;

public class JuliaSet extends Fractal{

    public JuliaSet(String function, Complex constant, ComplexeRectangle complexeRectangle, Integer discretizationStape){
        super(function, constant, complexeRectangle, discretizationStape);
    }

    public void writeJuliaFractalOnImage(){
        for(int i = 0; i < image.getImageLength(); i++){
            for(int j = 0; j < image.getImageHeight(); j++){

                //calcul de l'indice de divergence
                double complexX = -1 + image.getDiscretizationStape() * j; // changer le -1 en fonction du point en bas à gauche du rectangle
                double complexY = 1 -  image.getDiscretizationStape() * i; // changer le -1 en fonction du point en bas à gauche du rectangle
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
        }
    }

    public void makeJuliaFractal(){
        if(function != null && z != null && constant != null && complexeRectangle != null){
            writeJuliaFractalOnImage();
        }
    }
}
