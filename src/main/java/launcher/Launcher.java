package launcher;

import display.GeneralView;
import fractal.Fractal;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class Launcher {
    private GeneralView generalView;
    private Fractal fractal;

    public void makeFractal(){
        String function;
        Complex c = generalView.getConstant();
        ComplexRectangle complexRectangle = generalView.chooseComplexeRectangle();
        Double discretizationStape = generalView.discretizationStape();

    }

    public static void main(String[] args){
        //JuliaTest.juliaTest1(); //Pour les test
        //JuliaTest.juliaTest2();

        Launcher launcher = new Launcher();

        if(args.length == 1){
            launcher.generalView = new GeneralView(args[0]);
        }else{
            launcher.generalView = new GeneralView("graphical");
        }

        boolean wantToPlay = true;
        while(wantToPlay){
            launcher.generalView.displayHostPage();
            wantToPlay = launcher.generalView.chooseToPlay();
            launcher.makeFractal();
        }


    }


}
