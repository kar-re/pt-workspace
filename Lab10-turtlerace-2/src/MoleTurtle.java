import java.util.Random;

public class MoleTurtle extends RaceTurtle {
	private Random rand;

	public MoleTurtle(RaceWindow w, int nbr) {
		super(w, nbr);
		rand = new Random();
	}

	@Override
	public void raceStep() {
		Double prob = rand.nextDouble();
		if (prob < 0.5) {
			this.penUp();
		} else {
			this.penDown();
		}
		super.raceStep();
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += " - MoleTurtle";
		return s;
	}
}