package Fractal;

import Utils.ComplexOperation;
import Utils.ComplexeImg;
import Utils.ComplexeRectangle;
import org.apache.commons.math3.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public abstract class Fractal {

    protected static final int MAX_ITER = 1000;
    protected static final int RADIUS = 2;

    protected Complex z;
    protected Complex constant;
    protected ComplexeRectangle complexeRectangle;
    //private Integer discretizationStape;
    protected Integer divergeIndex;
    protected ComplexeImg image;
    protected Function<Complex, Complex> function;
    //private BufferedImage image;

    public Fractal(String function, Complex constant, ComplexeRectangle complexeRectangle, Integer discretizationStape){
        this.constant = constant;
        this.complexeRectangle = complexeRectangle;
        divergeIndex = -1;
        image = new ComplexeImg(complexeRectangle, discretizationStape);
        makeFunction(function);

    }

    public boolean fonctionStrIsGoodFormat(String str){
        return str.equals("z^2 + c");
    }

    public Function<Complex, Complex> makeFunction(String str){
        if (fonctionStrIsGoodFormat(str)){
            return (Complex z1) -> constant.add(z1.multiply(z1));
        }
        return null;
    }

    protected int divergenceIndex(Function<Complex, Complex> f) {
        if(z == null) return -1;

        int iteration = 0;
        Complex zn = ComplexOperation.makeCopieOf(z);

        // sortie de boucle si divergence
        while(iteration < MAX_ITER && (int)ComplexOperation.makeModuleOperation(zn) <= RADIUS) {
            zn = f.apply(zn);
            iteration++;
        }
        return iteration;
    }

}
