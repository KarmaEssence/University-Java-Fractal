package launcher;

import fractal.JuliaSet;
import utils.complex.ComplexOperation;
import utils.complex.ComplexRectangle;
import org.apache.commons.math3.complex.Complex;
import utils.config.FractalConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class JuliaTest {

    static int MAX_ITER=1000, RADIUS=2;
    public static int divergenceIndex(Complex z0, Function<Complex, Complex> f) {
        int ite = 0;
        Complex zn = ComplexOperation.makeCopieOf(z0);

        // sortie de boucle si divergence
        while(ite < MAX_ITER && (int)ComplexOperation.makeModuleOperation(zn) <= RADIUS) {
            zn = f.apply(zn);
            ite++;
        }
        return ite;
    }

    public static void juliaTest1(){
        Complex c = new Complex(-(0.7269), 0.1889);
        //Complex c = new Complex(0.3, 0.5); ok
        //Complex c = new Complex(0.285, 0.001);
        //Complex c = new Complex(0.285, 0.013); ok

        //Rectangle dans le plan complex
        Complex pointA = new Complex(-1, 1);
        Complex pointB = new Complex(1, -1);

        //Pas de discretisation
        double pas = 0.001;

        //Calcul de la longueur et de la largeur du rectangle complexe :
        double lengthComplex = Math.abs(pointA.getReal() - pointB.getReal());
        double widthComplex = Math.abs(pointA.getImaginary() - pointB.getImaginary());

        //Calcul de la longueur et de la largeur pour les dimensions de l'image :
        int length = (int)(lengthComplex / pas);
        int width = (int)(widthComplex / pas);

        System.out.println(length);
        System.out.println(width);

        //Creation de la fonction qui calcule f : f(z) = z^2 + c
        Function<Complex, Complex> f = (Complex z) -> c.add(z.multiply(z));

        //Creation de l'image :
        var img= new BufferedImage(length, width, BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){

                //calcul de l'indice de divergence
                double complexX = -1 + pas * j; // changer le -1 en fonction du point en bas à gauche du rectangle
                double complexY = 1 - pas * i; // changer le -1 en fonction du point en bas à gauche du rectangle
                Complex zk = new Complex(complexX, complexY);
                int index = divergenceIndex(zk, f);
                //System.out.println(index);

                int color;
                //divergence
                if(index < MAX_ITER){
                    color = ((index % 256) << 16) | (((index + 85) % 256) << 8) | ((index + 170) % 256);


                    //convergence
                }else{
                    int r = 64; int g = 224; int b = 208; //turquoise
                    color = (r << 16) | (g << 8) | b;
                }
                img.setRGB(j,i,color);
            }
        }

        File file = new File("./MyFile.png");
        try{
            ImageIO.write(img, "PNG", file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void juliaTest2(){
        Complex c = new Complex(-(0.7269), 0.1889);
        ComplexRectangle cr = new ComplexRectangle(-1, 1, 1, -1);
        JuliaSet juliaSet = new JuliaSet(c, cr, 0.001);
        juliaSet.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder("julia",c, cr, 0.001);
        builder.buildAndSave();
    }

}
