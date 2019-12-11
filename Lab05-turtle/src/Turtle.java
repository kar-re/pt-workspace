import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.window.Sprite;

public class Turtle {
	/**
	 * Attributer för klassen. SimpleWindow anger ritfönstret, X och Y är
	 * sköldpaddans nuvarande position, rot är sköldpaddans nuvarande rotation och
	 * isPenDown anger om pennan är nere eller ej. SimpleWindow w;
	 */
	SimpleWindow w;
	double X, Y, rot;
	boolean isPenDown;
	Sprite turtle;

	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x, y med pennan lyft och huvudet pekande rakt uppåt i
	 * fönstret (i negativ y-riktning).
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.w = w;
		this.X = (double) x;
		this.Y = (double) y;
		isPenDown = false;
		rot = 90;
		turtle = new Sprite("resources/turtle.png", 20, 20);
		w.getAdvancedControls().addSprite(turtle);
		turtle.moveTo(x-turtle.getWidth()/2, y-turtle.getHeight()/2);
		turtle.rotate(0);
	}

	/** Sänker pennan. */
	public void penDown() {
		isPenDown = true;

	}

	/** Lyfter pennan. */
	public void penUp() {
		isPenDown = false;
	}

	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {
		double x1, y1;
		x1 = X + (n * Math.cos(Math.toRadians(rot)));		
		y1 = Y - (n * Math.sin(Math.toRadians(rot)));
		w.moveTo(pF(X), pF(Y));
		if (isPenDown) {
			
			w.lineTo(pF(x1), pF(y1));
		} 
//		else {
//			w.moveTo(pF(x1), pF(y1));
//		}
		turtle.moveTo(pF(x1 - turtle.getWidth()/2), pF(y1 - turtle.getHeight()/2));
		turtle.rotate(0);
		X = x1;
		Y = y1;
	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		rot += beta;
		turtle.rotate(beta);
	}

	/**
	 * Går till punkten newX, newY utan att rita. Pennans läge (sänkt eller lyft)
	 * och huvudets riktning påverkas inte.
	 */
	public void jumpTo(int newX, int newY) {
		X = pF(newX);
		Y = pF(newY);
		w.moveTo(pF(X), pF(Y));

	}
	/** Hjälpmetod för att runda av doubles till ints
	 * 
	 * @param n
	 * @return
	 */
	int pF(double n) {
		return (int) Math.round(n);
	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		rot = 90;

	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return pF(X);
	}

	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return pF(Y);
	}

	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
	public int getDirection() {
		return pF(rot);
	}
}
