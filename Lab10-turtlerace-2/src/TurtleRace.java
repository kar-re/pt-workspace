import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TurtleRace {
	public static final int TURTLE_AMOUNT = 8;

	public static void main(String[] args) {
		Random rand = new Random();
		ArrayList<RaceTurtle> rlist = new ArrayList<RaceTurtle>();
		ArrayList<RaceTurtle> flist = new ArrayList<RaceTurtle>();
		RaceWindow w = new RaceWindow();

		/*
		 * Skapar de åtta sköldpaddorna
		 */
		for (int i = 0; i < TURTLE_AMOUNT; i++) {
			RaceTurtle turtle;
			switch(rand.nextInt(3)) {
			case 0:
				turtle = new AbsentMindedTurtle(w, i);
				rlist.add(turtle);
				break;
			case 1:
				turtle = new MoleTurtle(w, i);
				rlist.add(turtle);
				break;
			case 2:
				turtle = new DizzyTurtle(w, i);
				rlist.add(turtle);
				break;
			}
		}
		for (RaceTurtle t : rlist) {
			System.out.println(t.toString());
		}
		/*
		 * While-loop för att driva rundan
		 */
		while (!hasFinished(rlist)) {
			
			//Alla sköldpaddor tar ett steg
			for (RaceTurtle t : rlist) {
				t.raceStep();
			}
			/*
			 * Iterator för att kunna modifiera listan och inte få concurrencyfel
			 * Kan ha en bias för de första sköldpaddorna eftersom de har lägre index och den går i tur och ordning. 
			 */
			Iterator<RaceTurtle> iterator = rlist.iterator();
			while (iterator.hasNext()) {
				RaceTurtle tcurr = iterator.next();
				if (crossedFinishline(tcurr, w) != null) {
					flist.add(tcurr);
					iterator.remove();
				}
			}
			//Delay för att kunna visualisera vad som sker, dock tar alla ett steg 'per frame'
			RaceWindow.delay(50);

		}
		/*
		 * Printar resultaten
		 */		
		String first = "På plats 1: " + flist.get(0).toString();
		String second = "På plats 2: " + flist.get(1).toString();
		String third = "På plats 3: " + flist.get(2).toString();
		System.out.println(first);
		System.out.println(second);
		System.out.println(third);
		
		w.moveTo(w.getWidth()/2, (w.getHeight()/2) - 160);
		w.writeText(first);
		w.moveTo(w.getWidth()/2, ((w.getHeight()/2) - 140));
		w.writeText(second);
		w.moveTo(w.getWidth()/2, ((w.getHeight()/2)- 120));
		w.writeText(third);

	}

	/*
	 * Hjälpmetod för att kolla om en viss sköldpadda kommit i mål
	 */
	static RaceTurtle crossedFinishline(RaceTurtle t, RaceWindow w) {
		if (t.getX() >= RaceWindow.X_END_POS) {
			return t;
		} else {
			return null;
		}
	}

	/*
	 * Hjälpmetod för att kolla om alla sköldpaddor är i mål
	 */	
	static boolean hasFinished(ArrayList<RaceTurtle> rlist) {
		boolean hasFinished = rlist.size() == 0 ? true : false;
		return hasFinished;
	}

}
