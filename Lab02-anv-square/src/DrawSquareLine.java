import se.lth.cs.pt.square.Square;
import se.lth.cs.pt.window.SimpleWindow;

public class DrawSquareLine {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Move Square");
		Square sq = new Square(0, 0, 100);
		int oldX = 0;
		int oldY = 0;
		int steps = 10;
			while(true) {
				w.waitForMouseClick();
//				w.clear();
				int x = w.getMouseX();
				int y = w.getMouseY();
				int oldDX = 0;
				int oldDY = 0;
				
				for (int i=1; i<=steps; i++) {
					int deltaX =  (((x - oldX) / steps));
					int deltaY =  (((y - oldY) / steps));
					System.out.println("DeltaX: " + deltaX  + " DeltaY: " + deltaY + " x: " + x + " y: " + y );
					sq.move(deltaX, deltaY);
					sq.draw(w);
					w.delay(5);
				}
				oldX = x;
				oldY = y;
			}
	}
}
