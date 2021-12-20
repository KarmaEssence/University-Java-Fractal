package display.graphical.guihandler.controllers;

import display.graphical.guihandler.Controller;
import display.graphical.guihandler.Model;
import fractal.Fractal;
import fractal.JuliaSet;
import fractal.MandelbrotSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.config.FileData;
import utils.config.FractalConfig;
import utils.config.ImageConfig;
import utils.other.CheckStringFormat;
import utils.other.ParseArgs;

public class NewFractalController extends Controller {

    @FXML
    private CheckBox juliaCheckbox;

    @FXML
    private CheckBox mandelbrotCheckbox;


    @FXML
    private Pane constantBlock;

    @FXML
    private Text constantSection;

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
        if(juliaCheckbox.isSelected() && checkValue(constanteX.getText())
                && checkValue(constanteY.getText()))
            return false;
        return checkValue(pointAX.getText()) && checkValue(pointAY.getText())
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
        Fractal fractal = null;

        if(juliaCheckbox.isSelected()){
            fractal = new JuliaSet(makeConstante(),
                    makeRectangle(), Double.parseDouble(discretizationStape.getText()));

            juliaCheckbox.setSelected(false);
        }else if(mandelbrotCheckbox.isSelected()){
            fractal = new MandelbrotSet(makeRectangle(),
                    Double.parseDouble(discretizationStape.getText()));
            mandelbrotCheckbox.setSelected(false);
        }

        assert fractal != null;
        fractal.saveFractalImage();
        new FractalConfig.Builder(fractal).buildAndSave();
        model.setFractal(fractal);
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
        mandelbrotCheckbox.setOnAction(event -> {
            clearFields();
            if(mandelbrotCheckbox.isSelected()){
                juliaCheckbox.setSelected(false);
                constantBlock.setVisible(false);
                constantSection.setVisible(false);
            }else{
                constantBlock.setVisible(true);
                constantSection.setVisible(true);
            }
        });

        juliaCheckbox.setOnAction(event -> {
            clearFields();
            if(juliaCheckbox.isSelected()){
                mandelbrotCheckbox.setSelected(false);
                System.out.println(constantBlock != null);
                constantBlock.setVisible(true);
                constantSection.setVisible(true);
            }
        });

        newButton.setOnAction(event -> {
            boolean checkFormat = checkIfFieldsAreGoodFormat();
            double temp = Double.parseDouble(discretizationStape.getText());
            int code = ParseArgs.checkRectanglePosInFunctionOfDiscretizationStape(makeRectangle(), temp);
            if(checkFormat && code == 0 &&
                    (juliaCheckbox.isSelected() || mandelbrotCheckbox.isSelected())){

                errorMessage.setText("");
                makeFractal();
                clearFields();
                model.changeScene("main");

            }else{
                if(!juliaCheckbox.isSelected() && !mandelbrotCheckbox.isSelected())
                    errorInPage(1);
                else if(!checkFormat)
                    errorInPage(0);
                else
                    errorInPage(code);
            }
        });

        clearFieldButton.setOnAction(event -> {
            clearFields();
        });

        backButton.setOnAction(event -> {
            clearFields();
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
