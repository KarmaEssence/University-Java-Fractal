package utils.other;

import fractal.Fractal;
import fractal.JuliaSet;
import fractal.MandelbrotSet;
import launcher.Launcher;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class ParseArgs {

    public static Complex makeComplexe(String real, String imaginary){
        return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
    }

    public static ComplexRectangle makeRectangle(String pointAX, String pointAY,
                                           String pointBX, String pointBY){
        Complex pointA = makeComplexe(pointAX, pointAY);
        Complex pointB = makeComplexe(pointBX, pointBY);
        return new ComplexRectangle(pointA, pointB);
    }

    public static void checkDiscretizationStape(Launcher launcher, double discretizationStape){
        if(discretizationStape <= 0.0001 || discretizationStape > 0.1){
            launcher.getGeneralView().displayError(1);
            System.exit(0);
        }
    }

    public static boolean pointsAreOpposite(ComplexRectangle complexRectangle){
        return complexRectangle.getWidth() != 0 && complexRectangle.getHeight() != 0;
    }

    public static boolean checkPointPosLimite(Complex point, int limite){
        return Math.abs(point.getReal()) < limite &&
                Math.abs(point.getImaginary()) < limite;
    }

    public static boolean checkRectanglePosLimite(ComplexRectangle complexRectangle, int limite){
        return checkPointPosLimite(complexRectangle.getPointA(), limite) &&
                checkPointPosLimite(complexRectangle.getPointB(), limite);
    }

    public static void checkRectanglePosInFunctionOfDiscretizationStape(Launcher launcher,
                                                                        ComplexRectangle complexRectangle,
                                                                        double discretizationStape){

        if(!pointsAreOpposite(complexRectangle)){
            launcher.getGeneralView().displayError(2);
            System.exit(0);

        }else if(discretizationStape <= 0.1 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 11)){
            launcher.getGeneralView().displayError(3);
            System.exit(0);

        }else if(discretizationStape <= 0.01 && discretizationStape > 0.001
        && !checkRectanglePosLimite(complexRectangle, 6)){
            launcher.getGeneralView().displayError(4);
            System.exit(0);

        }else if(discretizationStape <= 0.001 && discretizationStape > 0.0001
                && !checkRectanglePosLimite(complexRectangle, 3)){
            launcher.getGeneralView().displayError(5);
            System.exit(0);
        }

    }

    public static int checkRectanglePosInFunctionOfDiscretizationStape(ComplexRectangle complexRectangle,
                                                                        double discretizationStape){

        if(!pointsAreOpposite(complexRectangle)){
            return 3;

        }else if(discretizationStape <= 0.1 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 11)){
            return 4;

        }else if(discretizationStape <= 0.01 && discretizationStape > 0.001
                && !checkRectanglePosLimite(complexRectangle, 6)){
            return 5;

        }else if(discretizationStape <= 0.001 && discretizationStape > 0.0001
                && !checkRectanglePosLimite(complexRectangle, 3)){
            return 6;
        }
        return 0;
    }

    public static Fractal makeFractal(Launcher launcher){
        String[] args = launcher.getArgs();
        String set = args[1];

        if(set.equals("julia")){
            Complex constant = makeComplexe(args[2], args[3]);
            ComplexRectangle complexRectangle = makeRectangle(args[4], args[5],
                    args[6], args[7]);
            double discretizationStape = Double.parseDouble(args[8]);

            checkDiscretizationStape(launcher, discretizationStape);
            checkRectanglePosInFunctionOfDiscretizationStape(launcher, complexRectangle,
                    discretizationStape);
            return new JuliaSet(constant, complexRectangle, discretizationStape);

        }else if(set.equals("mandelbrot")){
            ComplexRectangle complexRectangle = makeRectangle(args[2], args[3],
                    args[4], args[5]);
            double discretizationStape = Double.parseDouble(args[6]);
            checkDiscretizationStape(launcher, discretizationStape);
            return new MandelbrotSet(complexRectangle, discretizationStape);
        }
        return null;
    }
}
