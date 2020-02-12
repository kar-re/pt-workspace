import java.util.Random;

public class RaceTurtle extends Turtle {
	private int nbr; 
	/**
	* Skapar en sköldpadda som ska springa i fönstret w och som har start-
	* nummer nbr. Sköldpaddan startar med pennan nere och nosen vänd åt höger.
	* Startnumret blir plus justerat efter hur de skapas, vektorer startar med
	* index 0.
	*/
	public RaceTurtle(RaceWindow w, int nbr) {
		//Superkonstruktor med en y-offset pga sprite-storleken
		super(w, RaceWindow.getStartXPos(nbr), RaceWindow.getStartYPos(nbr) + 20);
		this.nbr = nbr + 1;
		this.left(270);
		this.penDown();
	};
	/**
	* Låter sköldpaddan gå framåt ett steg. Stegets längd ges av ett
	* slumptal (heltal) mellan 1 och 6.
	*/
	public void raceStep() {
//		int f = (int) (Math.round(6 * Math.random()));
		Random rand = new Random();
		int f = rand.nextInt(6) + 1;
		
		this.forward(f);
	}
	/**
	* Returnerar en läsbar representation av denna RaceTurtle,
	* på formen "Nummer x" där x är sköldpaddans startnummer.
	*/
	public String toString() {
		String s = "Nummer " + nbr;
		return s; 
	}
}
