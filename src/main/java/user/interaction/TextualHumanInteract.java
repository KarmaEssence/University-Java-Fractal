package user.interaction;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.other.CheckStringFormat;

import java.util.Locale;
import java.util.Scanner;

/**
 * Represente les choix de l utilisateur
 * sur l interface textuelle.
 * @version 1.0
 */
public class TextualHumanInteract implements HumanInteract {

	private Scanner scanner;
	private int iterationOfReadSomething;

	/**
	 * Cree un objet permettant
	 * les choix du joueur sur
	 * l interface textuelle.
	 */
	public TextualHumanInteract(){
		scanner = new Scanner(System.in);
		iterationOfReadSomething = 0;
	}

	/**
	 * Demande au joueur s il veut jouer.
	 * @return true s il veut joueur,
	 * false sinon.
	 */
	@Override
	public boolean chooseToPlay() {
		String answer ="nothing";
		while(!answer.equals("c")){
			System.out.println();
			System.out.println("Do you want continue or quit ?(c/q)");
			answer = scanner.nextLine();
			if(answer.equals("q"))System.exit(0);
			iterationOfReadSomething += 1;
		}
		return true;
	}

	private boolean setIsChoosed(String answer){
		answer = answer.toLowerCase(Locale.ROOT);
		return answer.equals("julia");
	}

	@Override
	public String chooseSet() {
		String answer = "nothing";
		while(!setIsChoosed(answer)){
			System.out.println("-> Enter a set for fractal (julia): ");
			answer = scanner.nextLine();
		}
		return answer;
	}

	@Override
	public Complex getConstant() {
		System.out.println();
		System.out.println("Making constant (c)");
		return createPoint();
	}

	private String getValueAnswer(String part){
		String answer = "nothing";
		while(!CheckStringFormat.checkValue(answer)){
			System.out.println("-> Enter a " + part + " part value: ");
			answer = scanner.nextLine();
		}
		return answer;
	}

	private Complex createPoint(){
		System.out.println();
		double realPart = CheckStringFormat.stringIntoValue(getValueAnswer("real"));
		double imaginaryPart = CheckStringFormat.stringIntoValue(getValueAnswer("imaginary"));

		System.out.println();
		return new Complex(realPart, imaginaryPart);
	}
	/**
	 * Position de l element.
	 * @return la position de l element.
	 */
	@Override
	public ComplexRectangle position(){
		System.out.println("Making point A");
		Complex pointA = createPoint();

		System.out.println("Making point B");
		Complex pointB = createPoint();
		return new ComplexRectangle((int)pointA.getReal(), (int)pointA.getImaginary(),
				(int)pointB.getReal(), (int)pointB.getImaginary());
	}

	@Override
	public double discretizationStape() {
		String answer = "nothing";
		while(!CheckStringFormat.checkValue(answer)) {
			System.out.println("-> Enter a discretizationStape value: ");
			answer = scanner.nextLine();
		}
		return CheckStringFormat.stringIntoValue(answer);
	}

	/**
	 * Verifie que la chaine de caractere.
	 * contienne le type de reponse demande.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere contient
	 * le type de reponse demande,false sinon.
	 */
	private boolean containsAnswers(String s){
		return s.equals("y") || s.equals("n");
	}

	/**
	 * Demande au joueur s il veut jouer.
	 * @param sentence question pose
	 * @return true si la reponse est favorable,false sinon.
	 */
	private boolean askToPlay(String sentence){
		String res = "";
		boolean b = false;
		while(!b){
			System.out.println();
			System.out.println(sentence);
			res = scanner.nextLine();
			if(containsAnswers(res))b=true;
		}
		return res.equals("y");
	}

	/**
	 * Pose une question a l utilisateur.
	 * @param sentence une question.
	 * @param endOfGame regarde si ce sont les
	 *                  dernieres questions.
	 * @return true si la reponse est favorable.
	 */
	@Override
	public boolean ask(String sentence,boolean endOfGame){
		return askToPlay(sentence);
	}

	/**
	 * Pose une question a chaque etape.
	 * @param sentence question.
	 * @return l une des trois options.
	 */
	@Override
	public String askPerStep(String sentence){
		String res = "";
		boolean b = false;
		while(!b){
			System.out.println();
			System.out.println(sentence);
			res = scanner.nextLine();
			if(res.equals("cont") || res.equals("quit") || res.equals("last"))b=true;
		}
		System.out.println();
		return res;
	}

	/**
	 * Verifie que le nom d utilisateur est correct.
	 * @param username nom donne par l utilisateur.
	 * @return true si le format est le bon,false sinon.
	 */
	private static boolean verifyUserNameFormat(String username){
		if(username == null || username.length() == 0)return false;
		for(int i=0;i<username.length();i++){
			char tmp = username.charAt(i);
			if(tmp<48 || (tmp>57 && tmp<65) || (tmp>90 && tmp<97) || tmp>122)return false;
		}
		return true;
	}

	/**
	 * Ferme le scanner une fois que la partie est fini.
	 */
	@Override
	public void close(){
		scanner.close();
	}
}