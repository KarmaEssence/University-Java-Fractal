package launcher;

import display.GeneralView;
import display.graphical.guihandler.GuiView;
import fractal.Fractal;
import fractal.JuliaSet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;


public class Launcher{
    private GeneralView generalView;
    private Fractal fractal;

    public void makeFractal(){

        String set = generalView.chooseSet();
        Complex c = generalView.getConstant();

        ComplexRectangle complexRectangle = generalView.chooseComplexeRectangle();
        double discretizationStape = generalView.discretizationStape();

        if(set.equals("julia")) {
            fractal = new JuliaSet(c, complexRectangle, discretizationStape);
            fractal.makeFractal();
        }
        //peut etre un else avec l'ensemble de Mandelbrot

        FractalConfig.Builder builder = new FractalConfig.Builder(fractal);
        builder.buildAndSave();
    }

    public static void executeShellOption(Launcher launcher){
        launcher.generalView.display();
        boolean wantToPlay = launcher.generalView.chooseToPlay();
        if(wantToPlay)
            launcher.makeFractal();
        System.exit(0);
    }

    public static void executeGraphicalOption(Launcher launcher){
        launcher.generalView.display();
    }

    public static void main(String[] args){

        //JuliaTest.juliaTest1(); //Pour les test
        //JuliaTest.juliaTest2();
        Launcher launcher = new Launcher();

        if(args.length == 1){
            launcher.generalView = new GeneralView(args[0]);
            if(args[0].equals("shell"))
                executeShellOption(launcher);
            else
                executeGraphicalOption(launcher);
        }else{
            launcher.generalView = new GeneralView("graphical");
            executeGraphicalOption(launcher);
        }

        System.out.println("Test");
        System.exit(0);

        /*if(args.length == 1 && args[0].equals("graphical")){
            launcher.generalView.display();
        }else{
            boolean wantToPlay = true;
            while(wantToPlay){
                launcher.generalView.display();
                wantToPlay = launcher.generalView.chooseToPlay();
                launcher.makeFractal();
                if(args.length == 1 && args[0].equals("shell"))
                    System.exit(0);
            }

        }*/




    }


    /*@Override
    public void start(Stage primaryStage){
        try {
            Label label = new Label("Hello world");
            Scene scene = new Scene(label);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
