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

    /**
     * Recupere les arguments
     * @return les arguments
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Recupere la vue generale
     * @return la vue generale
     */
    public GeneralView getGeneralView() {
        return generalView;
    }

    /**
     * Execute le programme sur le terminal
     * @param launcher la classe lançant le projet
     */
    public static void executeShellOption(Launcher launcher){
        launcher.generalView.display();
        Fractal fractal = ParseArgs.makeFractal(launcher);

        assert fractal != null;
        fractal.makeFractal();
        FractalConfig.Builder builder = new FractalConfig.Builder(fractal);
        builder.buildAndSave();
        System.exit(0);
    }

    /**
     * Execute le programme sur le terminal
     * @param launcher la classe lançant le projet
     */
    public static void executeGraphicalOption(Launcher launcher){
        launcher.generalView.display();
        System.exit(0);
    }

    /**
     * Permet de lancer le programme
     * @param args les arguments
     */
    public static void main(String[] args){
        Launcher launcher = new Launcher();
        launcher.args = args;

        if(args.length == 1 && args[0].equals("graphical")){
            launcher.generalView = new GeneralView("graphical");
            executeGraphicalOption(launcher);
        }else if(CheckStringFormat.checkShellArgs(args)) {
            launcher.generalView = new GeneralView("shell");
            executeShellOption(launcher);
        }else if(args.length == 1 && args[0].equals("test")){
            FractalLauncher.start();
        }else{
            launcher.generalView = new GeneralView("help");
            launcher.generalView.display();
        }
    }
}
