import java.awt.Color;

import se.lth.cs.pt.window.SimpleWindow;

public class LineDrawingRGB {
	public static void main(String[] args) {
		int steps = 4096*3;
		SimpleWindow w = new SimpleWindow(500, 500, "LineDrawing");
		w.moveTo(0, 0);
		int lastX = 0, lastY = 0;
		while (true) {
			w.waitForMouseClick();
			int x = w.getMouseX();
			int y = w.getMouseY();
			
			Color RGB = new Color(255,0,0);
			w.setLineWidth(5);
			double prectestX, prectestY;
			
			for (int i=1; i<=steps; i++) {
				float curr = (((float) i/(float) steps));
				RGB = Color.getHSBColor(curr, 1.0f, 1.0f);
				w.setLineColor(RGB);
				prectestX = lastX + ((x - lastX)*i/steps);
				prectestY = lastY + ((y - lastY)*i/steps);
				w.lineTo((int) prectestX, (int) prectestY);
			}
			 lastX = x;
			 lastY = y;
		}
	}

}

