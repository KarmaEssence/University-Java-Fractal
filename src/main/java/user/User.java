package user;

import org.apache.commons.math3.complex.Complex;
import user.interaction.GraphicalHumanInteract;
import user.interaction.HumanInteract;
import user.interaction.TextualHumanInteract;
import utils.complex.ComplexRectangle;

public class User {
    private final HumanInteract humanInteract;

    public User(boolean isShellInteract){
        humanInteract = (isShellInteract)? new TextualHumanInteract(): new GraphicalHumanInteract();
    }

    public boolean chooseToPlay(){
        return humanInteract.chooseToPlay();
    }

    public ComplexRectangle chooseComplexeRectangle(){
        return humanInteract.position();
    }

    public Double discretizationStape() {
        return humanInteract.discretizationStape();
    }

    public Complex getConstant() {
        return humanInteract.getConstant();
    }

    public String chooseSet() {
        return humanInteract.chooseSet();
    }

}
