package launcher;

import display.GeneralView;
import fractal.Fractal;
import fractal.JuliaSet;
import fractal.MandelbrotSet;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;
import utils.other.CheckStringFormat;
import utils.other.ParseArgs;


public class Launcher{
    private GeneralView generalView;
    private String[] args;

    public String[] getArgs() {
        return args;
    }

    public GeneralView getGeneralView() {
        return generalView;
    }

    public static void executeShellOption(Launcher launcher){
        launcher.generalView.display();
        Fractal fractal = ParseArgs.makeFractal(launcher);

        assert fractal != null;
        fractal.makeFractal();
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
