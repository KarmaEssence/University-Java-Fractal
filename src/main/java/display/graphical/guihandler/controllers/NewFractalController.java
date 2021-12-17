package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import fractal.Fractal;
import fractal.JuliaSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FractalConfig;
import utils.config.ImageConfig;
import utils.other.CheckStringFormat;

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
    private Text errorMessage;

    @FXML
    private Button backButton;

    private boolean checkValue(String s){
        return CheckStringFormat.checkValue(s);
    }

    //todo: Reprendre les fonctions de la classe TextualHumanInteract
    private boolean checkIfFieldsAreGoodFormat(){
        return checkValue(constanteX.getText()) && checkValue(constanteY.getText())
                && checkValue(pointAX.getText()) && checkValue(pointAY.getText())
                && checkValue(pointBX.getText()) && checkValue(pointBY.getText())
                && checkValue(discretizationStape.getText());

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
        Fractal fractal;

        if(juliaCheckbox.isSelected()){

            fractal = new JuliaSet(makeConstante(),
                    makeRectangle(), Double.parseDouble(discretizationStape.getText()));
            fractal.saveFractalImage();
            new FractalConfig.Builder(fractal).buildAndSave();
            model.setFractal(fractal);
            juliaCheckbox.setSelected(false);
        }

    }

    private void clearFields(){
        juliaCheckbox.setSelected(false);
        constanteX.clear(); constanteY.clear();
        pointAX.clear(); pointAY.clear();
        pointBX.clear(); pointBY.clear();
        discretizationStape.clear();
    }

    @Override
    public void initPage(Model model) {

        newButton.setOnAction(event -> {
            if(checkIfFieldsAreGoodFormat() && juliaCheckbox.isSelected()){
                errorMessage.setText("");
                makeFractal();
                model.changeScene("main");
                clearFields();
            }else{
                errorInPage(0);
            }
        });

        clearFieldButton.setOnAction(event -> {
            clearFields();
        });

        backButton.setOnAction(event -> {
            model.changeScene("main");
        });
    }

    @Override
    public void errorInPage(int error) {
        errorMessage.setText("Wrong content in one of all field");
        errorMessage.setFill(Color.RED);
    }
}
