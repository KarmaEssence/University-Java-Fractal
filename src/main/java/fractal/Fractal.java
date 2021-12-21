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
    protected String colorChoice;
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

    /**
     * Cree et sauvegarde la fractal
     **/
    public void saveFractalImage(){
        makeFractal();
    }

    /**
     * Recupere le nom de l ensemble choisi
     * @return le nom de l ensemble choisi
     */
    public String getSetChoice() {
        return setChoice;
    }

    /**
     * Etablie le nom de l ensemble choisi
     * @param setChoice nom de l ensemble choisi
     */
    public void setSetChoice(String setChoice) {
        this.setChoice = setChoice;
    }

    /**
     * Recupere la couleur
     * @return la couleur
     */
    public String getColorChoice() {
        return colorChoice;
    }

    /**
     * Recupere la constante
     * @return la constante
     */
    public Complex getConstant() { return constant; }

    /**
     * Recupere le rectangle
     * @return le rectangle
     */
    public ComplexRectangle getComplexRectangle() { return complexRectangle; }

    /**
     * Recupere le pas de discretisation
     * @return le pas de discretisation
     */
    public Double getDiscretizationStape() {
        return discretizationStape;
    }

    /**
     * Verifie que la fonction ait le bon format
     * @param str la fonction a verifier
     * @return true si la fonction correspond au format
     */
    public boolean fonctionStrIsGoodFormat(String str){
        return str.equals("z^2 + c");
    }

    /**
     * Transforme la fonction donnee en fonction de premiere classe
     * @param str fonction donnee
     * @return la fonction de premiere classe
     */
    public Function<Complex, Complex> makeFunction(String str){
        if (fonctionStrIsGoodFormat(str)){
            return (Complex z1) -> constant.add(z1.multiply(z1));
        }
        return null;
    }

    /**
     * Dans le cas de l'ensemble de mandelbrot cree la fonction
     * de premiere classe a partir de la constante
     * @param constant la constante
     * @return la fonction de premiere classe
     */
    public Function<Complex, Complex> makeMandelbrotFunction(Complex constant) {
        return (Complex z1) -> constant.add(z1.multiply(z1));
    }

    /**
     * Calcule l indice de divergence
     * @param constant la constante
     * @param z le complexe calcule
     * @param f la fonction donnee
     * @return l indice de divergence
     */
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

    /**
     * Recupere la couleur du pixel
     * @param a une position
     * @param b une position
     * @return la couleur correspondante
     */
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
            if(colorChoice.equals("orange")){
                color = ((index % 256) << 16) | (((index - 170) % 256) << 8) | ((index - 80) % 256);
            }else{
                color = ((index % 256) << 16) | (((index + 85) % 256) << 8) | ((index + 170) % 256);
            }

            //convergence
        }else{
            //color = (64 << 16) | (224 << 8) | 208;
            color = (10 << 16) | (120 << 8) | 2;
        }
        return color;
    }

    /**
     * Ecrit toutes les couleurs sur l image
     */
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

    /**
     * Construit la fractale
     */
    public abstract void makeFractal();

}
