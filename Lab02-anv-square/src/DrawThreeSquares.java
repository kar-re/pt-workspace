import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class DrawThreeSquares {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
//		Square sq = null;
//		sq.draw(w);

		Square square = new Square(250, 250, 100);
//		sq.draw(null);

		square.draw(w);
		w.delay(500);

		square.move(30, 30);
		square.draw(w);
		w.delay(500);

		square.move(20, -60);
		square.draw(w);

	}
}
