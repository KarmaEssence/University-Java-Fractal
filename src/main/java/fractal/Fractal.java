package fractal;

import utils.complex.ComplexOperation;
import utils.config.ImageConfig;
import utils.complex.ComplexRectangle;
import org.apache.commons.math3.complex.Complex;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.stream.IntStream;

public abstract class Fractal {

    protected static final int MAX_ITER = 1000;
    protected static final int RADIUS = 2;

    protected String setChoice;
    protected Complex constant;
    protected ComplexRectangle complexRectangle;
    protected Double discretizationStape;
    protected ImageConfig image;
    protected Function<Complex, Complex> function;

    public Fractal(String function, Complex constant, ComplexRectangle complexRectangle, Double discretizationStape){
        this.discretizationStape = discretizationStape;
        this.constant = constant;
        this.complexRectangle = complexRectangle;
        image = new ImageConfig(complexRectangle, discretizationStape);
        this.function = makeFunction(function);

    }

    public void saveFractalImage(){
        makeFractal();
    }

    public String getSetChoice() {
        return setChoice;
    }

    public void setSetChoice(String setChoice) {
        this.setChoice = setChoice;
    }

    public Complex getConstant() { return constant; }

    public ComplexRectangle getComplexRectangle() { return complexRectangle; }

    public Double getDiscretizationStape() {
        return discretizationStape;
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

    public Function<Complex, Complex> makeMandelbrotFunction(Complex constant) {
        return (Complex z1) -> constant.add(z1.multiply(z1));
    }

    protected int divergenceIndex(Complex constant, Complex z, Function<Complex, Complex> f) {
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

    private int getColorOfPixel(int a, int b){

        Complex pointA = complexRectangle.getPointA();
        double complexX = pointA.getReal() + discretizationStape * b;
        double complexY = pointA.getImaginary() -  discretizationStape * a;
        int index;
        Complex z;

        if(setChoice.equals("mandelbrot")){
            Complex constant = new Complex(complexX, complexY);
            z = new Complex(0, 0);
            index = divergenceIndex(constant, z, makeMandelbrotFunction(constant));
        }else{
            z = new Complex(complexX, complexY);
            index = divergenceIndex(constant, z , function);
        }

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

    protected void writeFractalOnImage(){
        Instant start = Instant.now();
        IntStream.range(0, image.getImageLength())
                .parallel()
                .forEach(a ->
                        IntStream.range(0, image.getImageHeight())
                                .parallel()
                                .forEach(b -> image.getImage().setRGB(b,a,getColorOfPixel(a, b))));

        Instant end = Instant.now();
        System.out.println("Time to write information pixel per pixel : "
                + Duration.between(start, end).getSeconds() + " seconds");
        image.saveFractal();
    }

    public abstract void makeFractal();

}
