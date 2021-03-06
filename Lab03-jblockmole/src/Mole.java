public class Mole {
	private Graphics g = new Graphics(30, 50, 10);
	
	public static void main(String[] args) {
		System.out.println("Keep on digging!");

		Mole m = new Mole();
		
		m.drawWorld();
		m.dig();
	}
	
	/* Ritar upp världen, med jord, himmel och gräs i den ordningen. */
	public void drawWorld() {
		int w = g.getWidth();
		int h = g.getHeight();
		g.rectangle(0, 0, w, h, Colors.SOIL);
		g.rectangle(0, 0, w, h / 3, Colors.SKY);
		g.rectangle(0, (h / 3), w,  + 1, Colors.GRASS);
	}

	/*
	 * Metod som hanterar grävandet, samt hanterar om mullvaden går ovanför marken
	 * eller utanför skärmen som loopas.
	 */
	public void dig() {
		int x = g.getWidth() / 2;
		int y = g.getHeight() / 2;
		while (true) {
			
			System.out.println("X: " + x + " Y: " + y);
			g.block(x, y, Colors.MOLE);
			
			/* Inputhantering. Bör kanske omfaktoriseras in i en egen klass? */
			char key = g.waitForKey();
			g.block(x, y, Colors.TUNNEL);
			if (key == 'w') {
				y -= 1;
			} else if (key == 'a') {
				x -= 1;
			} else if (key == 's') {
				y += 1;
			} else if (key == 'd') {
				x += 1;
			}
			
			/* Kollisionshantering (nästan) */
			if (x == g.getWidth()) {
				x = 0;
			} else if (x == -1) {
				x = g.getWidth() - 1;
			}
			if (y == (g.getHeight()/3 - 1)) {
				y = g.getHeight()/3;
			} else if (y == g.getHeight()) {
				y = g.getHeight() - 1;
			}
			
		}
	}
}
