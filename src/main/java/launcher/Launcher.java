package launcher;

import display.GeneralView;

public class Launcher {
    private GeneralView generalView;



    public static void main(String[] args){
        //JuliaTest.juliaTest1(); //Pour les test
        //JuliaTest.juliaTest2();

        Launcher launcher = new Launcher();

        if(args.length == 1){
            launcher.generalView = new GeneralView(args[0]);
        }else{
            launcher.generalView = new GeneralView("graphical");
        }
    }


}
