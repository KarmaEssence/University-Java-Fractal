package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import fractal.Fractal;
import fractal.JuliaSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class NewFractalController extends Controller {

    @FXML
    private CheckBox juliaCheckbox;

    @FXML
    private TextField constanteX;

    @FXML
    private TextField constanteY;

    @FXML
    private TextField pointAX;

    @FXML
    private TextField pointAY;

    @FXML
    private TextField pointBX;

    @FXML
    private TextField pointBY;

    @FXML
    private TextField discretizationStape;

    @FXML
    private Button newButton;

    @FXML
    private Button clearFieldButton;

    @FXML
    private static Text errorMessage;

    //todo: Reprendre les fonctions de la classe TextualHumanInteract
    private boolean checkIfFieldsAreGoodFormat(){
        return true;
    }

    private Complex makeComplexe(String real, String imaginary){
        return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
    }

    private Complex makeConstante(){
        return makeComplexe(constanteX.getText(), constanteY.getText());
    }

    private ComplexRectangle makeRectangle(){
        Complex pointA = makeComplexe(pointAX.getText(), pointAY.getText());
        Complex pointB = makeComplexe(pointBX.getText(), pointBY.getText());
        return new ComplexRectangle(pointA, pointB);
    }

    private void makeFractal(){
        Fractal fractal = null;
        if(juliaCheckbox.isSelected()){

            fractal = new JuliaSet(makeConstante(),
                    makeRectangle(), Double.parseDouble(discretizationStape.getText()));
        }
        model.setFractal(fractal);
    }

    @Override
    public void initPage(Model model) {
        newButton.setOnAction(event -> {
            if(checkIfFieldsAreGoodFormat()){
                makeFractal();
                errorMessage.setText("");
                model.changeScene("main");
                model.showScene();
            }else{
                errorInPage(0);
            }
        });
    }

    @Override
    public void errorInPage(int error) {
        errorMessage.setText("Wrong content in one of all field");
        model.changeScene("newFractal");
        model.showScene();

    }
}
