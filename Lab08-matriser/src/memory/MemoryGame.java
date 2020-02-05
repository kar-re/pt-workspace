package memory;

public class MemoryGame {
	public static void main(String[] args) {
		
		/*
		 * Variabler och objekt som behöver instansieras
		 */
		String[] frontFileNames = { "can.jpg", "flopsy_mopsy_cottontail.jpg", "friends.jpg", "mother_ladybird.jpg",
				"mr_mcgregor.jpg", "mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };
		MemoryBoard board = new MemoryBoard(4, "back.jpg", frontFileNames);
		MemoryWindow w = new MemoryWindow(board);
		w.drawBoard();
		int tries = 0;
		
		/*
		 * While-loop för att driva spelet, slutar när hasWon ger true
		 */
		while (!board.hasWon()) {
			/*
			 * Första draget, detta bör kunna faktoriseras till en egen metod, för att inte
			 * behöva duplikera koden. While loop för att förhindra ett drag där man klickar på samma kort. 
			 */
			int x2 = 0, y2 = 0;
			w.waitForMouseClick();
			int x1 = w.getMouseRow();
			int y1 = w.getMouseCol();
		
			while (board.frontUp(x1, y1)) {
				w.waitForMouseClick();
				x1 = w.getMouseRow();
				y1 = w.getMouseCol();
			}
			board.turnCard(x1, y1);
			w.drawBoard();

			/*
			 * Andra draget, while loop för att inte kunna klicka på samma kort. 
			 */
			
			w.waitForMouseClick();
			x2 = w.getMouseRow();
			y2 = w.getMouseCol();
			
			
			while (board.frontUp(x2, y2)) {
				w.waitForMouseClick();
				x2 = w.getMouseRow();
				y2 = w.getMouseCol();
			}
			
			
			board.turnCard(x2, y2);
			w.drawBoard();
			w.delay(800);

			/*
			 * Om det är inte är samma kort skall de vändas.
			 */
			if (!board.same(x1, y1, x2, y2)) {
				board.turnCard(x1, y1);
				board.turnCard(x2, y2);
			}
			w.drawBoard();
			
			/*
			 * Två drag läggs till per gång, för high-score
			 */
			tries += 2;
		}
		
		/*
		 * Vinstskärm, height och width för enkel positionering
		 */
		int height = w.getHeight() / 2;
		int width = w.getWidth() / 2;
		w.moveTo(width - 20, height - 5);
		w.writeText("Du har vunnit!!! :D");
		w.moveTo(width - 20, height + 10);
		w.writeText("Antal försök: " + tries);

		
	}
}
