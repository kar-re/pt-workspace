import se.lth.cs.pt.square.Square;
import se.lth.cs.pt.window.SimpleWindow;

public class MoveSquare {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Move Square");
		Square sq = new Square(0, 0, 100);
		int oldX = 0;
		int oldY = 0;
			while(true) {
				w.waitForMouseClick();
				w.clear();
				int x = w.getMouseX();
				int y = w.getMouseY();
				sq.move((x - oldX), (y - oldY));
				sq.draw(w);
				oldX = x;
				oldY = y;
			}
	}
}
