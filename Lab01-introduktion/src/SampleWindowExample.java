import java.awt.Color;

import se.lth.cs.pt.window.SimpleWindow;

public class SampleWindowExample {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Drawing Window");
		w.moveTo(100, 100);
		w.lineTo(150, 100);
		
		for(int i = 1; i <= 1024*1024; i++ ) {
			int randX = (int) (w.getWidth()*Math.random());
			int randY = (int) (w.getHeight()*Math.random());
			w.lineTo(randX, randY);
			w.moveTo(randX,randY);
			Color randCol = Color.getHSBColor((float) Math.random(), 1.0f, 1.0f);
			w.setLineWidth((int) (Math.random() * 20));
			w.setLineColor(randCol);
			//w.writeText("Hej världen!");
			w.delay(5);
		}
	}
}
