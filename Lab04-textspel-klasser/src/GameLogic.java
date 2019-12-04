import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
	private int score1, score2, currQst;
	private String name1, name2, currPlayer;
	private Renderer renderBoi;
	private Scanner scan;
	private ArrayList<Question> questions;
	private int nbrQuestions;
	
	GameLogic () {
		score1 = 0;
		score2 = 0;
		currQst = 0;
		name1 = null;
		name2 = null;
		renderBoi = new Renderer(80, 24, score1, score2);
		scan = new Scanner(System.in);
		questions = new ArrayList<Question>();
		Questions populList = new Questions();
		populList.initQuestions(questions);
	}
	
	
	void startGame() {
		renderBoi.welcomeScreen();
		delay(500);
		
		name1 = scan.next();
		name2 = scan.next();
		renderBoi.clearScreen();
		
		String output = String.format("De två deltagarna är %S och %S!",  name1, name2);
		renderBoi.centerTextMid(output, terminalColors.BACK_RED);
		delay(1000);
		
		renderBoi.clearScreen();
		renderBoi.centerTextMid("Hur många frågor vill ni spela?", terminalColors.BACK_BLUE);
		nbrQuestions = scan.nextInt();
//		if (scan.hasNextInt()) {
//			checkAmount();			
//		}
		
		String output2 = String.format("Bra! Då börjar vi med första frågan som går till %S!", name1);
		currPlayer = name1;
		renderBoi.centerTextMid(output2, terminalColors.BACK_BLUE);
		delay(2000);
		Question();
	}
	
	void checkAmount() {
		if (nbrQuestions > questions.size()) {
			renderBoi.clearScreen();
			renderBoi.centerTextMid("Det finns inte så många frågor i databasen!, prova igen!", terminalColors.TEXT_WHITE);
			nbrQuestions = scan.nextInt();
			checkAmount();
		}
	}
	
	void Question() {
		currQst += 1;
		int rndQ = (int) (Math.random() * questions.size());
		questions.get(rndQ);
		renderBoi.presentFourQuestions(
				questions.get(rndQ).question, 
				questions.get(rndQ).alternatives[0],
				questions.get(rndQ).alternatives[1],
				questions.get(rndQ).alternatives[2],
				questions.get(rndQ).alternatives[3]);
		
		int resp = scan.nextInt();
		
		if(resp == questions.get(rndQ).correctAlt) {
			renderBoi.clearScreen();
			renderBoi.centerTextMid("Bra! Plus ett poäng.", terminalColors.BACK_GREEN + terminalColors.TEXT_WHITE);
			if (currQst % 2 == 0) {
				score2 += 1;				
			} else {
				score1 += 1;
			}
			delay(2000);
			questions.remove(rndQ);
			altPlayer();
			next();
		} else {
			renderBoi.clearScreen();
			renderBoi.centerTextMid("Nästa gång!", terminalColors.BACK_RED + terminalColors.TEXT_WHITE);
			delay(2000);
			questions.remove(rndQ);
			altPlayer();
			next();
		}
		
		
	}
	
	void altPlayer() {
		if (currPlayer == name1) {
			currPlayer = name2;
		} else {
			currPlayer = name1;
		}
	}
	
	void next() {
		if (currQst <= nbrQuestions) {
			renderBoi.centerTextMid("Dags för nästa fråga som går till " + currPlayer, terminalColors.BACK_BLUE + terminalColors.TEXT_WHITE);
			delay(2000);
			Question();
		}
		else {
			String winner;
			if (score1 > score2) {
				winner = name1;
				renderBoi.endScreen(winner);
				if(scan.next() != " ") {
					startGame();
				}
			} else if (score2 > score1) {
				winner = name2;
				renderBoi.endScreen(winner);
				if(scan.next() != " ") {
					startGame();
				}
			} else {
				renderBoi.centerTextMid("Utslagsfråga!", terminalColors.BACK_BLUE + terminalColors.TEXT_WHITE);
				delay(2000);
				Question();
			}
				
		}
	}
	
	static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	static void printLnDelay(String str) {
		delay(2000);
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
			delay(20);
			if (i > str.length() - 5)
				delay(500);
		}
		System.out.println("");
	}
}
