import java.util.ArrayList;
import java.util.Random;
import se.lth.cs.pt.window.SimpleWindow;

public class infiniteTurtles {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle[] tArray = new Turtle[1000];
		// ArrayList<Turtle> tArray = new ArrayList<Turtle>();
		int[] posX = new int[tArray.length];
		int[] posY = new int[tArray.length];
		for (int i = 0; i < tArray.length; i++) {
			Random randP = new Random();
			tArray[i] = new Turtle(w, randP.nextInt(w.getWidth()), randP.nextInt(w.getHeight()));
		}
		while (true) {
			for (int i = 0; i < tArray.length; i++) {
				moveRandom(tArray[i], w);
				int X, Y;
				X = tArray[i].getX();
				Y = tArray[i].getY();
				posX[i] = tArray[i].getX();
				posY[i] = tArray[i].getY();
//				for (int f = 0; f < posX.length; f++) {
//					if (posX[f] == X) {
//						for (int e = 0; e < posY.length; e++) {
//							if (posY[e] == Y) {
//								tArray[f].isPenDown = false;
//							}
//						}
//					}
//				}
			}

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