import java.util.Random;
import se.lth.cs.pt.window.SimpleWindow;

public class TwoTurtlesStep {
 	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle t1 = new Turtle(w, 150, 150);
		Turtle t2 = new Turtle(w, 500, 500);
		int dist = 100;
		while (dist >=50) {
			dist = (int) Math.sqrt(Math.pow((t2.getY() - t1.getY()), 2) + Math.pow((t2.getX() - t1.getX()), 2));
			System.out.println(dist);
			moveRandom(t1, w);
			moveRandom(t2, w);
			w.delay(10);
			}
		}
 	static void badTurtle(Turtle t, SimpleWindow w) {
 		Random rand = new Random();
 		t.jumpTo(rand.nextInt(w.getWidth()), rand.nextInt(w.getHeight()));
	}
 	static void moveRandom(Turtle t, SimpleWindow w) {
 		Random randP1 = new Random();
 		Random randR1 = new Random();
 		t.penDown();
		t.forward(randP1.nextInt(10) + 1);
		t.left(randR1.nextInt(360) - 180);
		if (t.getX() > w.getWidth() || t.getX() < 0) {
			badTurtle(t, w);
		}
		if (t.getY() > w.getHeight() || t.getY() < 0) {
			badTurtle(t, w);
		}
 	}
}