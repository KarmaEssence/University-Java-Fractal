package user.interaction;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;

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
			if(iterationOfReadSomething > 0)
				answer = scanner.nextLine();
			if(answer.equals("q"))System.exit(0);
			iterationOfReadSomething += 1;
		}
		return true;
	}

	@Override
	public Complex getConstant() {
		System.out.println();
		System.out.println("Making constant (c)");
		return createPoint();
	}

	private String getValueAnswer(String part){
		String answer = "nothing";
		while(!checkValue(answer)){
			System.out.println("-> Enter a " + part + " part value: ");
			answer = scanner.nextLine();
		}
		return answer;
	}

	private Complex createPoint(){
		System.out.println();
		double realPart = stringIntoValue(getValueAnswer("real"));
		double imaginaryPart = stringIntoValue(getValueAnswer("imaginary"));

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
	public Double discretizationStape() {
		System.out.println("-> Enter a discretizationStape value: ");
		if (scanner.hasNextDouble()) {
			return scanner.nextDouble();
		}
		return null;
	}

	/**
	 * Transforme les informations en coordonnee pour la grille.
	 * @param s une chaine de caractere.
	 * @return retourne le premier caractere
	 * transforme en entier.
	 */
	public int stringIntoValue(String s){
		if(!checkArg(s))return -1;
		int res;
		if((s.charAt(0)>=49 && s.charAt(0)<=57)){
			res = Integer.valueOf(s);
		}else{
			res = Integer.valueOf(s.charAt(0))%32;
		}
		return res-1;
	}

	/**
	 * Verification du format de la reponse entre par le joueur.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere
	 * respecte le format,false sinon.
	 */
	public boolean checkArg(String s){
		if(s.length()!=1)return false;
		return ((s.charAt(0)>=49 && s.charAt(0)<=57)
				|| (s.charAt(0)>=65 && s.charAt(0)<=90)
				|| (s.charAt(0)>=97 && s.charAt(0)<=122));
	}

	/**
	 * Verifie que le paramètre donne
	 * contienne un entier entre 0 et 9.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere
	 * respecte le format,false sinon.
	 */
	public boolean checkValue(String s){
		if(s.length()==0)return false;
		for(int i=0;i<s.length();i++){
			if(!((s.charAt(i)>47 && s.charAt(i)<58) || s.charAt(i) == '.')) return false;
			//if(s.charAt(i)< 48 || s.charAt(i) > 57 || s.charAt(i) != '.') return false;
		}
		return true;
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