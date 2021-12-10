package display;

import display.graphical.GraphicalView;
import display.textual.TextualView;

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
		}else{
			view = new GraphicalView();
		}
	}

}
