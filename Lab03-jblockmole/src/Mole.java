public class Mole {
	private Graphics g = new Graphics(30, 50, 10);
	
	public static void main(String[] args) {
		Mole m = new Mole();
		m.drawWorld();
//		g.block(3, 3, Colors.MOLE);
		System.out.println("Keep on digging!");
		
	}
	
	public void drawWorld() {
		int w = g.getWidth();
		int h = g.getHeight();
		g.rectangle(0, 0, w, h, Colors.SOIL);
		g.rectangle(0, 0, w, h / 3, Colors.SKY);
		g.rectangle(0, (h / 3), w,  + 1, Colors.GRASS);
	}
	public void dig() {
		int x = g.getWidth() / 2;
		int y = g.getHeight() / 2;
		while (true) {
			g.block(x, y, Colors.MOLE);
			char key = g.waitForKey();
			if (key == 'w') {
				
			} else if (key == 'a') {
				
			} else if (key == 's') {
				
			} else if (key == 'd') {
				
			}
		}
	}
}
