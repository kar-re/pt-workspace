import java.util.Random;

public class AbsentMindedTurtle extends RaceTurtle {
	private Random rand;
	private int absent;

	public AbsentMindedTurtle(RaceWindow w, int nbr) {
		super(w, nbr);
		rand = new Random();
		absent = rand.nextInt(101) + 1;
	}

	@Override
	public void raceStep() {

		if (absent < rand.nextInt(101)) {
			super.raceStep();
		}

	}

	@Override
	public String toString() {
		String s = super.toString();
		s += " - AbsentMindedTurtle (" + absent + "% frånvarande)";
		return s;
	}
}