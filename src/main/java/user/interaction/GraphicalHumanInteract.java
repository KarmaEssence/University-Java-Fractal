package user.interaction;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

public class GraphicalHumanInteract implements HumanInteract {
    @Override
    public boolean chooseToPlay() {
        return false;
    }

    @Override
    public Complex getConstant() {
        return null;
    }

    @Override
    public ComplexRectangle position() {
        return new ComplexRectangle(0,0,0,0);
    }

    @Override
    public Double discretizationStape() {
        return null;
    }

    @Override
    public boolean ask(String sentence, boolean endOfGame) {
        return false;
    }

    @Override
    public String askPerStep(String sentence) {
        return null;
    }

    @Override
    public void close() {

    }
}
