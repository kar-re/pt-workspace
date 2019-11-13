import java.awt.Color;

import se.lth.cs.pt.square.Square;
import se.lth.cs.pt.window.SimpleWindow;

public class DrawSquareLineRGB {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(1920, 1080, "Move Square");
		Square sq = new Square(0, 0, 20);
		int oldX = 0;
		int oldY = 0;
		int steps = 100;
			while(true) {
				int x = (int) (Math.random() * w.getWidth());
				int y = (int) (Math.random() * w.getHeight());
				System.out.println("X: " + x + "Y: " + y);
				w.setLineWidth(3);
				for (int i=1; i<=steps; i++) {
					int deltaX =  (((x - oldX) / steps));
					int deltaY =  (((y - oldY) / steps));
					float curr = (((float) i/(float) steps));
					Color RGB = Color.getHSBColor(curr, 1.0f, 1.0f);
					int width = Math.abs((deltaX/5)*i);
					sq.setSide(width);
					System.out.println("W: " + width);
					w.setLineColor(RGB);
					sq.move(deltaX, deltaY);
					sq.draw(w);
					w.delay(5);
				}
				oldX = x;
				oldY = y;
			}
	}
}
