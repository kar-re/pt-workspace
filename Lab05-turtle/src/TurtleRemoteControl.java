import se.lth.cs.pt.window.SimpleWindow;

public class TurtleRemoteControl {
	SimpleWindow w;
	Turtle t;
	TurtleRemoteControl() {
		w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		t = new Turtle(w, 300, 300);
		
	}
 	public static void main(String[] args) {
 		TurtleRemoteControl t = new TurtleRemoteControl(); 
		while(true) {
			t.gameLoop();
			//delay(20);
		}
	}

 	static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
 	void gameLoop() {
 		t.penDown();
 		char currKey = w.waitForKey();
 		//w.getKey();
//		System.out.println(currKey);
		if(currKey == 'w') {
			t.forward(10);
			System.out.println("Rör sig framåt!");
		}
		if(currKey == 'd') {
			t.left(-10);
			System.out.println("Rör sig höger!");
		}
		if(currKey == 'a') {
			t.left(10);
			System.out.println("Rör sig vänster!");
		}
		//w.delay(14);
 	}
}
