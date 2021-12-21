package display;

import display.graphical.GraphicalView;
import display.textual.HelpTextualView;
import display.textual.TextualView;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

import java.util.Scanner;


public class GeneralView{
	private final View view;

	/**
	 * Cree une instance contenant un joueur et une vue.
	 */
	public GeneralView(String str){
		if(str.equals("shell")){
			view = new TextualView();
		}else if(str.equals("graphical")){
			view = new GraphicalView();
		}else{
			view = new HelpTextualView();
		}


	}

	/**
	 * Affichage
	 */
	public void display(){
		view.print();
	}

	/**
	 * Affichage d une erreur
	 */
	public void displayError(int code){view.makeAnErrorMessage(code);}

}
