package fractal;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;


public class JuliaSet extends Fractal{

    public JuliaSet(Complex constant, ComplexRectangle complexRectangle, Double discretizationStape){
        super("z^2 + c", constant, complexRectangle, discretizationStape);
        super.setChoice = "julia";

    }

    public JuliaSet(String color, Complex constant, ComplexRectangle complexRectangle, Double discretizationStape){
        super("z^2 + c", constant, complexRectangle, discretizationStape);
        super.setChoice = "julia";
        super.colorChoice = color;

    }

    /**
     * Construit la fractal s il y a un rectangle,
     * une constante et une fonction
     **/
    @Override
    public void makeFractal(){
        if(function != null && constant != null && complexRectangle != null){
            writeFractalOnImage();
        }
    }
}
