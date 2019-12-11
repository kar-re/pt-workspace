import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class gameLogic implements ActionListener {
	SimpleWindow w;
	Turtle t;
	TurtleRemoteControl t1, t2;
	private Random r;
	long currTime, lastTime;

	gameLogic() {
		w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		t = new Turtle(w, 300, 300);
		r = new Random();
		currTime = 0;
		lastTime = 0;
	}

	static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	void startGame() {
		t.jumpTo(0, 0);
		w.moveTo(w.getWidth() / 2, w.getHeight() / 2);
		w.writeText("3");
		w.delay(1000);
		w.clear();
		w.writeText("2");
		w.delay(1000);
		w.clear();
		w.writeText("1");
		w.delay(1000);
		w.clear();
		w.writeText("Kör!");
		w.delay(1000);
		w.clear();
		t.penDown();
		t.jumpTo((int) Math.round(r.nextDouble() * w.getWidth()), (int) Math.round(r.nextDouble() * w.getHeight()));
	}

//	public void run() {
//		startGame();
//		while (true) {
//			currTime = System.currentTimeMillis();
//			delay(50);
//			gameLoop();
//			lastTime = System.currentTimeMillis();
//
//		}
//
//	}

	void gameLoop() {
			char currKey = w.getKey();
			System.out.println(currKey);
			t.forward(10);
			if (currKey == 'd') {
				t.left(-10);
				System.out.println("Rör sig höger!");
			}
			if (currKey == 'a') {
				t.left(10);
				System.out.println("Rör sig vänster!");
			}
		// w.delay(50);
	}
	// w.delay(14);
}
