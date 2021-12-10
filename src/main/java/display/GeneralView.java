package display;

import display.graphical.GraphicalView;
import display.textual.TextualView;
import org.apache.commons.math3.complex.Complex;
import user.User;
import utils.complex.ComplexRectangle;

import java.util.Scanner;

/**
 * Permet de faire la liaison entre
 * la vue, le model et le joueur.
 * @version 1.0
 */
public class GeneralView{
	private View view;
	private User user;

	/**
	 * Cree une instance contenant un joueur et une vue.
	 */
	public GeneralView(String str){
		if(str.equals("shell")){
			view = new TextualView();
			user = new User(true);
		}else{
			view = new GraphicalView();
			user = new User(false);
		}


	}

	public boolean chooseToPlay(){
		return user.chooseToPlay();
	}

	public void displayHostPage(){
		view.printHostPage();
	}

	public Complex getConstant() {
		return user.getConstant();
	}

	public ComplexRectangle chooseComplexeRectangle(){
		return user.chooseComplexeRectangle();
	}

	public Double discretizationStape() {
		return user.discretizationStape();
	}

}
