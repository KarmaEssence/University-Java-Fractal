package fractal;

import utils.complex.ComplexOperation;
import utils.config.ImageConfig;
import utils.complex.ComplexRectangle;
import org.apache.commons.math3.complex.Complex;

import java.util.function.Function;

public abstract class Fractal {

    protected static final int MAX_ITER = 1000;
    protected static final int RADIUS = 2;

    protected String setChoice;
    protected Complex z;
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

    public abstract void makeFractal();

}
