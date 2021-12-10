package user.interaction;

import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

/**
 * Represente les choix de l utilisateur.
 * @version 1.0
 */
public interface HumanInteract {

	/**
	 * Demande au joueur s il veut jouer.
	 * @return true s il veut joueur,
	 * false sinon.
	 */
	boolean chooseToPlay();

	Complex getConstant();

	/**
	 * Position de l element.
	 * @return la position de l element.
	 */
	ComplexRectangle position();

	Double discretizationStape();

	/**
	 * Pose une question a l utilisateur.
	 * @param sentence une question.
	 * @param endOfGame regarde si ce sont les
	 *                  dernieres questions.
	 * @return true si la reponse est favorable.
	 */
	boolean ask(String sentence,boolean endOfGame);

	/**
	 * Pose une question a chaque etape du niveau.
	 * @param sentence la question.
	 * @return l une des trois options.
	 */
	String askPerStep(String sentence);

	/**
	 * Termine les choix de l utilisateur.
	 */
	void close();
}