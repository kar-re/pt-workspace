
public class Question {
	String category, question;
	String[] alternatives;
	int correctAlt;
	public Question(String cat, String que, String[] alt, int calt) {
		category = cat;
		question = que;
		alternatives = alt;
		correctAlt = calt;
	}
}
