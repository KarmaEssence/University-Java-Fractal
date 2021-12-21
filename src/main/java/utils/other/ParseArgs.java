package utils.other;

import fractal.Fractal;
import fractal.JuliaSet;
import fractal.MandelbrotSet;
import launcher.Launcher;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class ParseArgs {

    /**
     * Fabrique un complexe
     * @param real partie reel
     * @param imaginary partie imaginaire
     * @return un nombre complexe
     */
    public static Complex makeComplexe(String real, String imaginary){
        return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
    }

    /**
     * Fabrique un rectangle
     * @param pointAX partie reel du point A
     * @param pointAY partie imaginaire du point A
     * @param pointBX partie reel du point B
     * @param pointBY partie imaginaire du point B
     * @return un rectangle
     */
    public static ComplexRectangle makeRectangle(String pointAX, String pointAY,
                                           String pointBX, String pointBY){
        Complex pointA = makeComplexe(pointAX, pointAY);
        Complex pointB = makeComplexe(pointBX, pointBY);
        return new ComplexRectangle(pointA, pointB);
    }

    /**
     * Verifie que le pas de discretisation soit correct
     * @param launcher la classe lançant le projet
     * @param discretizationStape pas de discretisation
     */
    public static void checkDiscretizationStape(Launcher launcher, double discretizationStape){
        if(discretizationStape <= 0.0001){
            launcher.getGeneralView().displayError(2);
            System.exit(0);
        }
        if(discretizationStape > 0.1){
            launcher.getGeneralView().displayError(3);
            System.exit(0);
        }
    }

    /**
     * Verifie que les points du rectangles soient opposes
     * @param complexRectangle un rectangle
     * @return true si les points du rectangles sont opposes,
     * false sinon
     */
    public static boolean pointsAreOpposite(ComplexRectangle complexRectangle){
        return complexRectangle.getWidth() != 0 && complexRectangle.getHeight() != 0
                && complexRectangle.getWidth() == complexRectangle.getHeight();
    }

    /**
     * Verifie que le point ne depasse pas la limite
     * @param point un complexe
     * @param limite la limite
     * @return true le point ne depasse pas la limite,
     * false sinon
     */
    public static boolean checkPointPosLimite(Complex point, int limite){
        return Math.abs(point.getReal()) < limite &&
                Math.abs(point.getImaginary()) < limite;
    }

    /**
     * Verifie que les points ne depassent pas la limite
     * @param complexRectangle un rectangle
     * @param limite la limite
     * @return true les points ne depassent pas la limite,
     * false sinon
     */
    public static boolean checkRectanglePosLimite(ComplexRectangle complexRectangle, int limite){
        return checkPointPosLimite(complexRectangle.getPointA(), limite) &&
                checkPointPosLimite(complexRectangle.getPointB(), limite);
    }

    /**
     * Verifie que le rectangle respecte les dimensions
     * @param launcher la classe lançant le projet
     * @param complexRectangle un rectangle
     * @param discretizationStape pas de discretisation
     */
    public static void checkRectanglePosInFunctionOfDiscretizationStape(Launcher launcher,
                                                                        ComplexRectangle complexRectangle,
                                                                        double discretizationStape){
        if(!pointsAreOpposite(complexRectangle)){
            launcher.getGeneralView().displayError(5);
            System.exit(0);

        }else if(discretizationStape <= 0.1 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 11)){
            launcher.getGeneralView().displayError(6);
            System.exit(0);

        }else if(discretizationStape <= 0.01 && discretizationStape > 0.001
        && !checkRectanglePosLimite(complexRectangle, 6)){
            launcher.getGeneralView().displayError(7);
            System.exit(0);

        }else if(discretizationStape <= 0.001 && discretizationStape > 0.0001
                && !checkRectanglePosLimite(complexRectangle, 3)){
            launcher.getGeneralView().displayError(8);
            System.exit(0);
        }

    }

    /**
     * Verifie que le rectangle respecte les dimensions
     * @param complexRectangle un rectangle
     * @param discretizationStape pas de discretisation
     * @return un signal de retour
     */
    public static int checkRectanglePosInFunctionOfDiscretizationStape(ComplexRectangle complexRectangle,
                                                                        double discretizationStape){

        if(!pointsAreOpposite(complexRectangle)){
            return 5;

        }else if(discretizationStape <= 0.1 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 11)){
            return 6;

        }else if(discretizationStape <= 0.01 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 6)){
            return 7;

        }else if(discretizationStape <= 0.001 && discretizationStape > 0.0001
                && !checkRectanglePosLimite(complexRectangle, 3)){
            return 8;
        }
        return 0;
    }

    /**
     * Construit une fractal
     * @param launcher la classe lançant le projet
     * @return une fractal
     */
    public static Fractal makeFractal(Launcher launcher){
        String[] args = launcher.getArgs();
        String set = args[1];
        String color = args[2];

        if(set.equals("julia")){
            Complex constant = makeComplexe(args[3], args[4]);
            ComplexRectangle complexRectangle = makeRectangle(args[5], args[6],
                    args[7], args[8]);
            double discretizationStape = Double.parseDouble(args[9]);

            checkDiscretizationStape(launcher, discretizationStape);
            checkRectanglePosInFunctionOfDiscretizationStape(launcher, complexRectangle,
                    discretizationStape);
            return new JuliaSet(color, constant, complexRectangle, discretizationStape);

        }else if(set.equals("mandelbrot")){
            ComplexRectangle complexRectangle = makeRectangle(args[3], args[4],
                    args[5], args[6]);
            double discretizationStape = Double.parseDouble(args[7]);
            checkDiscretizationStape(launcher, discretizationStape);
            return new MandelbrotSet(color, complexRectangle, discretizationStape);
        }else{
            launcher.getGeneralView().displayError(1);
            System.exit(0);
        }
        return null;
    }
}
