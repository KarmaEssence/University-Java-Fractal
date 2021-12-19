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
import utils.other.ParseArgs;

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
        System.out.println(s + " " + CheckStringFormat.checkValue(s));
        return CheckStringFormat.checkValue(s);
    }

    //todo: Reprendre les fonctions de la classe TextualHumanInteract
    private boolean checkIfFieldsAreGoodFormat(){
        return checkValue(constanteX.getText()) && checkValue(constanteY.getText())
                && checkValue(pointAX.getText()) && checkValue(pointAY.getText())
                && checkValue(pointBX.getText()) && checkValue(pointBY.getText())
                && checkValue(discretizationStape.getText());

    }

    private Complex makeConstante(){
        return ParseArgs.makeComplexe(constanteX.getText(), constanteY.getText());
    }

    private ComplexRectangle makeRectangle(){
        return ParseArgs.makeRectangle(pointAX.getText(), pointAY.getText(),
                pointBX.getText(), pointBY.getText());
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
        errorMessage.setText("");
    }

    @Override
    public void initPage(Model model) {

        newButton.setOnAction(event -> {
            System.out.println(checkIfFieldsAreGoodFormat());
            if(checkIfFieldsAreGoodFormat() && juliaCheckbox.isSelected()
            && !(Double.parseDouble(discretizationStape.getText()) < 0.001)){
                errorMessage.setText("");
                makeFractal();
                clearFields();
                model.changeScene("main");
            }else{
                if(!juliaCheckbox.isSelected())
                    errorInPage(1);
                else if (Double.parseDouble(discretizationStape.getText()) < 0.001)
                    errorInPage(2);
                else
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
        if(error == 1)
            errorMessage.setText("Please select a set");
        else if(error == 2)
            errorMessage.setText("You cannot choose an discretization stape inferior of 0.0009");
        else
            errorMessage.setText("Wrong content in one of all field");
        errorMessage.setFill(Color.RED);
    }
}
