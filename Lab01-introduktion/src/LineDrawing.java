import se.lth.cs.pt.window.SimpleWindow;

public class LineDrawing {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "LineDrawing");
		w.moveTo(0, 0);
		boolean firstTime = true;
		
		while (true) {
			w.waitForMouseClick();
			int x = w.getMouseX();
			int y = w.getMouseY();
			
			if (firstTime) {
				w.moveTo(x, y);
				firstTime = !firstTime;
			} else {
				w.setLineWidth(5);
				w.lineTo(x, y);
			}
		}
	}
}
