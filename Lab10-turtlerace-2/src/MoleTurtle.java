import java.util.Random;

public class MoleTurtle extends RaceTurtle {
	/* 
	 * Ett random-objekt
	 */
	private Random rand;

	public MoleTurtle(RaceWindow w, int nbr) {
		/* 
		 * Som RaceTurtle fast lite frånvarande i tanken.
		 * Super för att kalla på superklassens konstruktor
		 * rand för att instantiera nytt randomobjekt
		 */
		super(w, nbr);
		rand = new Random();
	}

	/*
	 * Override av raceStep-metoden 
	 * Ny double mellan 0 och 1, om den är mindre än 0.5 så 
	 * ska den ta upp pennan, annars ta ned och gå ett steg
	 */
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
	/*
	 * Override av toString-metoden 
	 * Lägger till MoleTurtle.
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s += " - MoleTurtle";
		return s;
	}
}