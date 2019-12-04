import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class LongStepTurtle {
 	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle t = new Turtle(w, 300, 300);
		Random randP = new Random();
		Random randR = new Random();
		
		t.penDown();
		for (int i = 0; i < 10000000; i++) {
			t.forward(randP.nextInt(10) + 1);
			t.left(randR.nextInt(360) - 180);
			if (t.getX() > w.getWidth() || t.getX() < 0) {
				t.jumpTo(randP.nextInt(w.getWidth()), randP.nextInt(w.getHeight()));
			}
			if (t.getY() > w.getHeight() || t.getY() < 0) {
				t.jumpTo(randP.nextInt(w.getWidth()), randP.nextInt(w.getHeight()));
			}
			w.delay(1);
		}
	}
}