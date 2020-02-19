import java.util.Random;

public class DizzyTurtle extends RaceTurtle {
	/* 
	 * En random och en dizzyness-faktor 
	 */
	private Random rand;
	private int dizzyness;

	/* 
	 * RaceTurtle fast som är lite snurrig. 
	 */
	public DizzyTurtle(RaceWindow w, int nbr) {
		/* 
		 * Super för att kalla på superklassens konstruktor. 
		 * rand nytt random-objekt, dizzyness tilldelas en faktor mellan 1 och 5.
		 * */
		super(w, nbr);
		rand = new Random();
		dizzyness = rand.nextInt(5) + 1;
	}

	/* 
	 * Override av raceStep-metoden 
	 * Superenkel implementation, om ett slumpmässigt tal är mindre än dizzyness-faktorn så 
	 * roterar den 1 grad åt vänster eller höger, även detta slumpmässigt. 
	 */
	@Override
	public void raceStep() {
		
		if (rand.nextInt(5) < dizzyness) {
			if (rand.nextBoolean()) {
				super.left(-1);
			} else {
				super.left(1);				
			}
		} 
		super.raceStep();
	}
	
	
	@Override
	public String toString() {
		String s = super.toString();
		s += " - DizzyTurtle (Yrsel: " + dizzyness + ")" ;
		return s;
	}
}