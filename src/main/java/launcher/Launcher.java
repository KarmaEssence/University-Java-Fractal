package launcher;

import display.GeneralView;
import fractal.Fractal;
import fractal.JuliaSet;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;
import utils.other.CheckStringFormat;
import utils.other.ParseArgs;


public class Launcher{
    private GeneralView generalView;
    private String[] args;

    public static void executeShellOption(Launcher launcher){
        String set = launcher.args[1];
        Complex constant = ParseArgs.makeComplexe(launcher.args[2], launcher.args[3]);
        ComplexRectangle complexRectangle = ParseArgs.makeRectangle(launcher.args[4], launcher.args[5],
                launcher.args[6], launcher.args[7]);
        double discretizationStape = Double.parseDouble(launcher.args[8]);
        System.out.println(discretizationStape);
        if(discretizationStape < 0.001){
            launcher.generalView.displayError(1);
            System.exit(0);
        }

        Fractal fractal = null;
        if(set.equals("julia")) {
            fractal = new JuliaSet(constant, complexRectangle, discretizationStape);
            fractal.makeFractal();
        }

        assert fractal != null;
        FractalConfig.Builder builder = new FractalConfig.Builder(fractal);
        builder.buildAndSave();
        System.exit(0);
    }

    public static void executeGraphicalOption(Launcher launcher){
        launcher.generalView.display();
        System.exit(0);
    }

    public static void main(String[] args){
        Launcher launcher = new Launcher();
        launcher.args = args;

        if(args.length == 1 && args[0].equals("graphical")){
            launcher.generalView = new GeneralView("graphical");
            executeGraphicalOption(launcher);
        }else if(CheckStringFormat.checkShellArgs(args)){
            launcher.generalView = new GeneralView("shell");
            executeShellOption(launcher);
        }else{
            launcher.generalView = new GeneralView("help");
            launcher.generalView.display();
        }
    }
}
