package display;

import display.graphical.GraphicalView;
import display.textual.HelpTextualView;
import display.textual.TextualView;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

import java.util.Scanner;

/**
 * Permet de faire la liaison entre
 * la vue, le model et le joueur.
 * @version 1.0
 */
public class GeneralView{
	private View view;

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

	public void display(){
		view.print();
	}

}
