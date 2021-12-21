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
    private CheckBox colorBlueCheckbox;

    @FXML
    private CheckBox colorOrangeCheckbox;

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

    /**
     * Appelle la methode checkValue de
     * CheckStringFormat
     */
    private boolean checkValue(String s){
        return CheckStringFormat.checkValue(s);
    }

    /**
     * Verifie que le format est respecte
     * @return true si le format est respecte
     */
    private boolean checkIfFieldsAreGoodFormat(){
        if(juliaCheckbox.isSelected() && (constanteX.getText().isEmpty() ||
                constanteY.getText().isEmpty()))
            return false;
        if(pointAX.getText().isEmpty() || pointAY.getText().isEmpty() ||
                pointBX.getText().isEmpty() || pointBY.getText().isEmpty() ||
        discretizationStape.getText().isEmpty())
            return false;
        if(juliaCheckbox.isSelected() && !checkValue(constanteX.getText())
                && !checkValue(constanteY.getText()))
            return false;
        return checkValue(pointAX.getText()) && checkValue(pointAY.getText())
                && checkValue(pointBX.getText()) && checkValue(pointBY.getText())
                && checkValue(discretizationStape.getText());

    }

    /**
     * Appelle la methode de ParseArgs
     */
    private Complex makeConstante(){
        return ParseArgs.makeComplexe(constanteX.getText(), constanteY.getText());
    }

    /**
     * Appelle la methode de ParseArgs
     */
    private ComplexRectangle makeRectangle(){
        return ParseArgs.makeRectangle(pointAX.getText(), pointAY.getText(),
                pointBX.getText(), pointBY.getText());
    }

    /**
     * Construit la fractale et l enregiste
     */
    private void makeFractal(){
        Fractal fractal = null;
        String color = (colorBlueCheckbox.isSelected())? "cold" : "heat";

        if(juliaCheckbox.isSelected()){
            fractal = new JuliaSet(color, makeConstante(),
                    makeRectangle(), Double.parseDouble(discretizationStape.getText()));

            juliaCheckbox.setSelected(false);
        }else if(mandelbrotCheckbox.isSelected()){
            fractal = new MandelbrotSet(color, makeRectangle(),
                    Double.parseDouble(discretizationStape.getText()));
            mandelbrotCheckbox.setSelected(false);
        }

        assert fractal != null;
        fractal.saveFractalImage();
        new FractalConfig.Builder(fractal).buildAndSave();
        model.setFractal(fractal);
    }

    /**
     * Reinitialise les champs de texte
     */
    private void clearFields(){
        juliaCheckbox.setSelected(false);
        mandelbrotCheckbox.setSelected(false);
        colorBlueCheckbox.setSelected(false);
        colorOrangeCheckbox.setSelected(false);
        constanteX.clear(); constanteY.clear();
        pointAX.clear(); pointAY.clear();
        pointBX.clear(); pointBY.clear();
        discretizationStape.clear();
        errorMessage.setText("");
    }

    /**
     * Initialise cette page
     * @param model modele de l interface graphique
     */
    @Override
    public void initPage(Model model) {
        mandelbrotCheckbox.setOnAction(event -> {
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
            if(juliaCheckbox.isSelected()){
                mandelbrotCheckbox.setSelected(false);
                constantBlock.setVisible(true);
                constantSection.setVisible(true);
            }
        });

        colorBlueCheckbox.setOnAction(event -> {
            if(colorBlueCheckbox.isSelected()){
                colorOrangeCheckbox.setSelected(false);
            }
        });

        colorOrangeCheckbox.setOnAction(event -> {
            if(colorOrangeCheckbox.isSelected()){
                colorBlueCheckbox.setSelected(false);
            }
        });

        newButton.setOnAction(event -> {
            boolean checkFormat = checkIfFieldsAreGoodFormat();
            System.out.println(checkFormat);
            double value = (checkFormat)? Double.parseDouble(discretizationStape.getText()) : 0;
            int code = (checkFormat)? ParseArgs.checkRectanglePosInFunctionOfDiscretizationStape(makeRectangle(), value)
                    : 0;
            if(checkFormat && code == 0 && value > 0.0001 && value <= 0.1 &&
                    (juliaCheckbox.isSelected() || mandelbrotCheckbox.isSelected()) &&
                    (colorBlueCheckbox.isSelected() || colorOrangeCheckbox.isSelected())){

                errorMessage.setText("");
                makeFractal();
                constantBlock.setVisible(true);
                constantSection.setVisible(true);
                clearFields();
                model.changeScene("main");

            }else{
                if(!juliaCheckbox.isSelected() && !mandelbrotCheckbox.isSelected())
                    errorInPage(1);
                else if(!colorBlueCheckbox.isSelected() && !colorOrangeCheckbox.isSelected())
                    errorInPage(2);
                else if(!checkFormat)
                    errorInPage(0);
                else if (value <= 0.0001)
                    errorInPage(3);
                else if (value > 0.1)
                    errorInPage(4);
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

    /**
     * Affiche une erreur
     * @param error code de l erreur
     */
    @Override
    public void errorInPage(int error) {
        if(error == 1)
            errorMessage.setText("Please select a set between julia and mandelbrot");
        else if(error == 2)
            errorMessage.setText("Please select a color between cold and heat");
        else if(error == 3)
            errorMessage.setText("You cannot choose an discretization stape inferior of 0.009");
        else if(error == 4)
            errorMessage.setText("You cannot choose an discretization stape superior of 0.1");
        else if(error == 5)
            errorMessage.setText("Please choose two opposite points for the rectangle");
        else if(error == 6)
            errorMessage.setText("To discretization stape inferior/equals of 0.1, max dimensions are 10 x 10");
        else if(error == 7)
            errorMessage.setText("To discretization stape inferior/equals of 0.01, max dimensions are 5 x 5");
        else if(error == 8)
            errorMessage.setText("To discretization stape inferior/equals of 0.01, max dimensions are 2 x 2");
        else
            errorMessage.setText("You cannot choose this!");

        errorMessage.setFill(Color.RED);
    }
}
