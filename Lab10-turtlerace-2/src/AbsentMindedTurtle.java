import java.util.Random;

public class AbsentMindedTurtle extends RaceTurtle {
	/*
	 * En random och en absent-faktor
	 */
	private Random rand;
	private int absent;

	/*
	 * RaceTurtle fast som är lite frånvarande.
	 */
	public AbsentMindedTurtle(RaceWindow w, int nbr) {
		/*
		 * Super för att kalla på superklassens konstruktor. rand nytt random-objekt,
		 * absent tilldelas en faktor mellan 1 och 100.
		 */
		super(w, nbr);
		rand = new Random();
		absent = rand.nextInt(99) + 1;
	}
	/*
	 * Override av raceStep-metoden 
	 * Superenkel implementation, om absent är mindre
	 * än ett slumpmässigt tal mellan 0 och 100 så får den gå ett steg, annars stå
	 * över sitt kast.
	 */

	@Override
	public void raceStep() {
		if (absent < rand.nextInt(101)) {
			super.raceStep();
		}

	}
	/* 
	 * Override av toString-metoden 
	 * Lägger till namnet, AbsentMindedTurtle plus frånvaro-procenten.
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s += " - AbsentMindedTurtle (" + absent + "% frånvarande)";
		return s;
	}
}