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
            double value = Double.parseDouble(discretizationStape.getText());
            int code = ParseArgs.checkRectanglePosInFunctionOfDiscretizationStape(makeRectangle(), value);
            if(checkFormat && code == 0 && !(value < 0.001) &&
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
                else if (value < 0.001)
                    errorInPage(2);
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
        else if(error == 3)
            errorMessage.setText("Please choose two opposite points for the rectangle");
        else if(error == 4)
            errorMessage.setText("To discretization stape inferior/equals of 0.1, max dimensions are 10 x 10");
        else if(error == 5)
            errorMessage.setText("To discretization stape inferior/equals of 0.01, max dimensions are 5 x 5");
        else if(error == 6)
            errorMessage.setText("To discretization stape inferior/equals of 0.01, max dimensions are 2 x 2");
        else
            errorMessage.setText("You cannot choose this!");

        errorMessage.setFill(Color.RED);
    }
}
