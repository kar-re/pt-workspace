import java.util.Random;

public class DizzyTurtle extends RaceTurtle {
	private Random rand;
	private int dizzyness;

	public DizzyTurtle(RaceWindow w, int nbr) {
		super(w, nbr);
		rand = new Random();
		dizzyness = rand.nextInt(6) + 1;
	}

	@Override
	public void raceStep() {
		
		if (dizzyness < rand.nextInt(6)) {
			super.left(dizzyness);
		} else {
			super.left(-1 * dizzyness);
		}
		super.raceStep();
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += " - MoleTurtle (Yrsel: " + dizzyness + ")" ;
		return s;
	}
}