package launcher;

import display.GeneralView;
import fractal.Fractal;
import fractal.JuliaSet;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

import java.io.File;

public class Launcher {
    private GeneralView generalView;
    private Fractal fractal;

    public void makeFractal(){

        String set = generalView.chooseSet();
        Complex c = generalView.getConstant();

        /*System.out.println("real partt : " + c.getReal());
        System.out.println("imaginary partt : " + c.getImaginary());*/

        ComplexRectangle complexRectangle = generalView.chooseComplexeRectangle();
        double discretizationStape = generalView.discretizationStape();

        //System.out.println("pas : " + discretizationStape);

        if(set.equals("julia")) {
            fractal = new JuliaSet("z^2 + c", c, complexRectangle, discretizationStape);
            fractal.makeFractal();
        }
        //peut etre un else avec l'ensemble de Mandelbrot


    }

    public static void main(String[] args){
        //JuliaTest.juliaTest1(); //Pour les test
        JuliaTest.juliaTest2();

        Launcher launcher = new Launcher();

        /*if(args.length == 1){
            launcher.generalView = new GeneralView(args[0]);
        }else{
            launcher.generalView = new GeneralView("graphical");
        }

        boolean wantToPlay = true;
        while(wantToPlay){
            launcher.generalView.displayHostPage();
            wantToPlay = launcher.generalView.chooseToPlay();
            launcher.makeFractal();
            if(args.length == 1 && args[0].equals("shell"))
                System.exit(0);
        }*/


    }


}
